package com.hs5233.computercontroler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private PrintWriter out = null;

    private static final String HOST = "192.168.10.195";
    private static final int PORT = 5233;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
    }
    private void initControl() {
        Button btn_last = (Button) findViewById(R.id.button2);
        Button btn_pause = (Button) findViewById(R.id.button);
        Button btn_next = (Button) findViewById(R.id.button5);
        Button btn_up = (Button) findViewById(R.id.button3);
        Button btn_down = (Button) findViewById(R.id.button4);
        Button btn_shutdown = (Button) findViewById(R.id.button6);
        Button btn_exit = (Button) findViewById(R.id.button7);

        btn_last.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    @Override
                    public void run() {
                        // 执行完毕后给handler发送一个空消息
                        try {
                            // 实例化Socket
                            Socket socket = new Socket(HOST, PORT);
                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                    socket.getOutputStream())), true);
                            if (socket.isConnected()) {
                                if (!socket.isOutputShutdown()) {
                                    out.println("last");
                                    out.close();
                                }
                            }
                            // 获得输入流
//                            BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(socket.getInputStream()));
//                            line = br.readLine();
//                            br.close();
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    @Override
                    public void run() {
                        // 执行完毕后给handler发送一个空消息
                        try {
                            // 实例化Socket
                            Socket socket = new Socket(HOST, PORT);
                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                    socket.getOutputStream())), true);
                            if (socket.isConnected()) {
                                if (!socket.isOutputShutdown()) {
                                    out.println("next");
                                    out.close();
                                }
                            }
                            // 获得输入流
//                            BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(socket.getInputStream()));
//                            line = br.readLine();
//                            br.close();
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    @Override
                    public void run() {
                        // 执行完毕后给handler发送一个空消息
                        try {
                            // 实例化Socket
                            Socket socket = new Socket(HOST, PORT);
                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                    socket.getOutputStream())), true);
                            if (socket.isConnected()) {
                                if (!socket.isOutputShutdown()) {
                                    out.println("up");
                                    out.close();
                                }
                            }
                            // 获得输入流
//                            BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(socket.getInputStream()));
//                            line = br.readLine();
//                            br.close();
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    @Override
                    public void run() {
                        // 执行完毕后给handler发送一个空消息
                        try {
                            // 实例化Socket
                            Socket socket = new Socket(HOST, PORT);
                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                    socket.getOutputStream())), true);
                            if (socket.isConnected()) {
                                if (!socket.isOutputShutdown()) {
                                    out.println("down");
                                    out.close();
                                }
                            }
                            // 获得输入流
//                            BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(socket.getInputStream()));
//                            line = br.readLine();
//                            br.close();
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_shutdown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("确定关机？").
                        setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread() {
                                    @Override
                                    public void run() {
                                        // 执行完毕后给handler发送一个空消息
                                        try {
                                            // 实例化Socket
                                            Socket socket = new Socket(HOST, PORT);
                                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                                    socket.getOutputStream())), true);
                                            if (socket.isConnected()) {
                                                if (!socket.isOutputShutdown()) {
                                                    out.println("shutdown");
                                                    out.close();
                                                }
                                            }
                                        } catch (UnknownHostException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        })
                        .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
                // TODO Auto-generated method stub
            }
        });

        btn_exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("确定退出软件？").
                        setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                new Thread() {
                                    @Override
                                    public void run() {
                                        // 执行完毕后给handler发送一个空消息
                                        try {
                                            // 实例化Socket
                                            Socket socket = new Socket(HOST, PORT);
                                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                                    socket.getOutputStream())), true);
                                            if (socket.isConnected()) {
                                                if (!socket.isOutputShutdown()) {
                                                    out.println("exit");
                                                    out.close();
                                                }
                                            }
                                        } catch (UnknownHostException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        })
                        .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
            }
        });

        btn_pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    @Override
                    public void run() {
                        // 执行完毕后给handler发送一个空消息
                        try {
                            // 实例化Socket
                            Socket socket = new Socket(HOST, PORT);
                            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                    socket.getOutputStream())), true);
                            if (socket.isConnected()) {
                                if (!socket.isOutputShutdown()) {
                                    out.println("pause");
                                    out.close();
                                }
                            }
                            // 获得输入流
//                            BufferedReader br = new BufferedReader(
//                                    new InputStreamReader(socket.getInputStream()));
//                            line = br.readLine();
//                            br.close();
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
