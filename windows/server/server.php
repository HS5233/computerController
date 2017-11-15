<?php
	// Set time limit to indefinite execution 
	set_time_limit (0); 
	ignore_user_abort(true);
	date_default_timezone_set('PRC');
	error_reporting(0);
	
	// Set the ip and port we will listen on 
	$address = '192.168.10.195'; 
	$port = 5233; 
	$max_clients = 5233; 
	unlink('socketMessage.php');
	$file = fopen('socketMessage.php','a+');
	
	// Array that will hold client information 
	$client = Array(); 
	 
	// Create a TCP Stream socket 
	$sock = socket_create(AF_INET, SOCK_STREAM, 0); 
	// Bind the socket to an address/port 
	socket_bind($sock, $address, $port) or die('Could not bind to address'); 
	// Start listening for connections 
	socket_listen($sock); 
	
	fwrite($file,'start|'.date('Y-m-d H:i:s')."\r\n");
	
	// Loop continuously 
	while (true) { 
		// Setup clients listen socket for reading 
		$read[0] = $sock; 
		for ($i = 0; $i < $max_clients; $i++) { 
			if (isset($client[$i]['sock'])){
				$read[$i + 1] = $client[$i]['sock']; 
			}
		} 
		// Set up a blocking call to socket_select() 
		if (socket_select($read, $write = NULL, $except = NULL, $tv_sec = 5) < 1) continue; 
		/* if a new connection is being made add it to the client array */ 
		if (in_array($sock, $read)) { 
			for ($i = 0; $i < $max_clients; $i++) { 
				if (empty($client[$i]['sock'])) { 
					$client[$i]['sock'] = socket_accept($sock); 
					fwrite($file,'new client|'.$client[$i]['sock'].'|'.date('Y-m-d H:i:s')."\r\n");
					break; 
				}
			} 
		}
		// If a client is trying to write - handle it now 
		for ($i = 0; $i < $max_clients; $i++) { // for each client 
			if (isset($client[$i]['sock'])) {
				if (in_array($client[$i]['sock'], $read)){ 				
					$input = socket_read($client[$i]['sock'], 1024); 
					$input = substr($input,0,strlen($input)-1);
					if ($input == null) { 
						fwrite($file,'client disconnecting|'.$client[$i]['sock'].'|'.date('Y-m-d H:i:s')."\r\n");
						// Zero length string meaning disconnected
						socket_close($client[$i]['sock']); 						
						unset($client[$i]); 
					} else { 
						// send it to the other clients 
						// $input = 'hello world';
						for ($j = 0; $j < count($client); $j++) { 
							if($i==$j)continue;
								fwrite($file,$input.'|'.$client[$i]['sock'].'|'.date('Y-m-d H:i:s')."\r\n");
								socket_write($client[$j]['sock'], $input, strlen($input)); 
						}
					}
				}
			} 
		} 
	} // end while 
	// Close the master sockets 
	socket_close($sock); 
?>