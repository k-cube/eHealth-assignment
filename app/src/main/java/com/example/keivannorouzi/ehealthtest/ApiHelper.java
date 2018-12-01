package com.example.keivannorouzi.ehealthtest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ApiHelper {


    public static boolean isInternetAvailable() {
        try {
            int timeoutMs = 1000;
            Socket sock = new Socket();
            SocketAddress socketaddress = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(socketaddress, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }
}
