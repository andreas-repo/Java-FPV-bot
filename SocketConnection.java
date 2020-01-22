package sample;

import java.io.*;
import java.net.Socket;

public class SocketConnection {
    public String ip;
    public int port = 80;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public SocketConnection() {}

    public void forward_on() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?forward=on";
        this.sendMessage(socket, messageOn);
    }

    public void leftOn() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?left=on";
        this.sendMessage(socket, messageOn);
    }

    public void leftOff() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOff = "GET /?left=off";
        this.sendMessage(socket, messageOff);
    }

    public void rightOn() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?right=on";
        this.sendMessage(socket, messageOn);
    }

    public void rightOff() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOff = "GET /?right=off";
        this.sendMessage(socket, messageOff);
    }

    public void reverseOn() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?return=on";
        this.sendMessage(socket, messageOn);
    }

    public void reverseOff() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOff = "GET /?return=off";
        this.sendMessage(socket, messageOff);
    }

    public void turnLeft() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?turnLeft";
        this.sendMessage(socket, messageOn);
    }

    public void turnRight() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?turnRight";
        this.sendMessage(socket, messageOn);
    }

    public void turnOff() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOff = "GET /?turnOff";
        this.sendMessage(socket, messageOff);
    }

    public void turbo_on() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        String messageOn = "GET /?turbo=on";
        this.sendMessage(socket, messageOn);
    }

    public String servo_left() throws IOException {
        java.net.Socket socket = new java.net.Socket(this.ip, this.port);
        String messageOn = "GET /?servo=left";
        this.sendMessage(socket, messageOn);
        String receiveMessage = this.readMessage(socket);
        receiveMessage = receiveMessage.substring(0, receiveMessage.indexOf(" "));
        return receiveMessage;
    }

    public String servo_middle() throws IOException {
        java.net.Socket socket = new java.net.Socket(this.ip, this.port);
        String messageOn = "GET /?servo=middle";
        this.sendMessage(socket, messageOn);
        String receiveMessage = this.readMessage(socket);
        receiveMessage = receiveMessage.substring(0, receiveMessage.indexOf(" "));
        return receiveMessage;
    }

    public String servo_right() throws IOException {
        java.net.Socket socket = new java.net.Socket(this.ip, this.port);
        String messageOn = "GET /?servo=right";
        this.sendMessage(socket, messageOn);
        String receiveMessage = this.readMessage(socket);
        receiveMessage = receiveMessage.substring(0, receiveMessage.indexOf(" "));
        return receiveMessage;
    }

    public void sendMessage(Socket socket, String message) throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        printWriter.print(message);
        printWriter.flush();
    }

    String readMessage(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        char[] buffer = new char[200];
        int amountChars = bufferedReader.read(buffer, 0, 200);
        String message = new String(buffer, 0, amountChars);
        return message;
    }
}
