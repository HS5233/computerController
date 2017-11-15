#include <ostream>
#include <winsock2.h>
#include <windows.h>
#pragma comment (lib,"ws2_32.lib")
using namespace std;

int main(){
//	system("wscript last.vbs");
	WORD wVersionRequested;
	WSADATA wsaData;
	int err,i;
	wVersionRequested = MAKEWORD(1,1);
	err = WSAStartup( wVersionRequested, &wsaData );
	if( err != 0 ){
		return 0;
	}
	if ( LOBYTE( wsaData.wVersion ) != 1 ||
		HIBYTE( wsaData.wVersion ) != 1 ) {
		WSACleanup( );
		return 0;
	}
	SOCKET sockClient = socket(AF_INET,SOCK_STREAM,0);
	SOCKADDR_IN addrSrv;
	addrSrv.sin_addr.S_un.S_addr = inet_addr("192.168.10.195");
	addrSrv.sin_family = AF_INET;
	addrSrv.sin_port = htons(5233);
	connect(sockClient,(SOCKADDR*)&addrSrv,sizeof(SOCKADDR));
	char recvBuf[50]={'\0'};
	HWND hwnd=GetForegroundWindow();
	ShowWindow(hwnd,SW_HIDE);
	while(true){
		recv(sockClient,recvBuf,50,0);
		if(recvBuf[0]!='\0'){
			if(!strcmp(recvBuf,"last")){
				keybd_event(VK_MENU, MapVirtualKey(VK_MENU, 0), WM_KEYDOWN, 0);
				keybd_event(VK_LEFT, MapVirtualKey(VK_LEFT, 0), WM_KEYDOWN, 0);
			    Sleep(500);
				keybd_event(VK_MENU, MapVirtualKey(VK_MENU, 0), KEYEVENTF_KEYUP, 0);
				keybd_event(VK_LEFT, MapVirtualKey(VK_LEFT, 0), KEYEVENTF_KEYUP, 0);
			}else if(!strcmp(recvBuf,"next")){
				keybd_event(VK_MENU, MapVirtualKey(VK_MENU, 0), WM_KEYDOWN, 0);
				keybd_event(VK_RIGHT, MapVirtualKey(VK_RIGHT, 0), WM_KEYDOWN, 0);
			    Sleep(500);
				keybd_event(VK_MENU, MapVirtualKey(VK_MENU, 0), KEYEVENTF_KEYUP, 0);
				keybd_event(VK_RIGHT, MapVirtualKey(VK_RIGHT, 0), KEYEVENTF_KEYUP, 0);
			}else if(!strcmp(recvBuf,"pause")){
				keybd_event(VK_MENU, MapVirtualKey(VK_MENU, 0), WM_KEYDOWN, 0);
				keybd_event(VK_F5, MapVirtualKey(VK_F5, 0), WM_KEYDOWN, 0);
			    Sleep(500);
				keybd_event(VK_MENU, MapVirtualKey(VK_MENU, 0), KEYEVENTF_KEYUP, 0);
				keybd_event(VK_F5, MapVirtualKey(VK_F5, 0), KEYEVENTF_KEYUP, 0);
			}else if(!strcmp(recvBuf,"up")){
				keybd_event(VK_VOLUME_UP,MapVirtualKey(VK_VOLUME_UP,0),KEYEVENTF_EXTENDEDKEY,0); 
			}else if(!strcmp(recvBuf,"down")){
				keybd_event(VK_VOLUME_DOWN,MapVirtualKey(VK_VOLUME_DOWN,0),KEYEVENTF_EXTENDEDKEY,0); 
			}else if(!strcmp(recvBuf,"shutdownn")){
				system("shutdown -s -t 0");
				break;
			}else if(!strcmp(recvBuf,"exit")){
				break;
			}
		}
		for(i=0;i<50;i++)recvBuf[i] = '\0';
	}
	closesocket(sockClient);
	WSACleanup();
	return 0;
}