　　根目录下有Android和Windows两个目录，Android为手机客户端源码，使用Android Studio编译，Windows目录下有computer controler（单词拼写错误，忽略这个不重要的细节吧……），music和server目录，computer controler是socket服务端，可以在内网直接和手机客户端通信，music是socket客户端，server是php编写的socket服务端，music+sever+Android构成可通过外网控制电脑的系统，Android不分内网和外网，更换监听的IP或者域名就能实现功能。

　　内网控制思路：在电脑上写个简单的socket服务器，再在手机端写个客户端，通过发送模拟键值的方式实现手机对电脑的控制。

　　外网控制思路：由于不能直接让电脑和手机通信，于是需要借助一个第三方服务端，手机端向第三方服务端发出控制指令，服务端向电脑推送指令，实现控制。