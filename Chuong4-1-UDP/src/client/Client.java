package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private DatagramSocket socket;
	private int portServer = 8000;
	private DatagramPacket request;
	private Scanner sc;
	private InetAddress serverAddr;

	public Client() {
		try {
			socket = new DatagramSocket();
			sc = new Scanner(System.in);
			serverAddr = InetAddress.getLocalHost();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Receive receive = new Receive(socket);
		receive.start();
		printMenu();
		while(true) {
			String msg = sc.nextLine();
			if(msg.equals("exit")) {
				socket.close();
				break;
			}
			request = new DatagramPacket(msg.getBytes(), msg.length(), serverAddr, portServer);
			try {
				socket.send(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printMenu() {
		String menu = "Nhập vào một số - nhập exit để kết thúc";
		System.out.println(menu);
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.run();
	}
}

class Receive extends Thread {
	private DatagramSocket socket;
	private byte[] data;
	private DatagramPacket incoming;

	public Receive(DatagramSocket socket) {
		this.socket = socket;
		data = new byte[60000];
		incoming = new DatagramPacket(data, data.length);
	}

	public void run() {
		while(true) {
			try {
				socket.receive(incoming);
				String msg = new String(incoming.getData(), 0, incoming.getLength(), "UTF-8");
				System.out.println(msg);
			} catch (IOException e) {
				break;
			}
		}

	}

}