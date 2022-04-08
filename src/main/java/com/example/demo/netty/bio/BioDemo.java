package com.example.demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BioDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5005);
        Socket socket = serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String request, response;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
        }
        response = processRequest(request);
        out.println(response);
    }

    private static String processRequest(String request) {
        return request;
    }

}
