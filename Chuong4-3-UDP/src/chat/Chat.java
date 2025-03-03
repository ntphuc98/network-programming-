package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Chat {
	private int port;
	private int portConect;
	private DatagramSocket socket;
	private byte[] buffer = new byte[60000];
	private DatagramPacket incoming;
	private DatagramPacket outsending;
	private InetAddress addrConect;
	private String name;
	private Scanner sc;

	public Chat(int port, int portConect) {
		this.port = port;
		this.portConect = portConect;
		sc = new Scanner(System.in);
		try {
			addrConect = InetAddress.getByName("localhost");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("Socket Errors");
		}
	}

	public void start() {
		System.out.println("Nhập tên của bạn: ");
		name = sc.nextLine();
		
		// thread Receive
		Receive receiveServer = new Receive();
		receiveServer.start();

		// thread send
		Send sendServer = new Send();
		sendServer.start();

		System.out.println("Chat is running...");
	}

	public class Receive extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					incoming = new DatagramPacket(buffer, buffer.length);
					// Chờ nhận gói tin gởi đến
					socket.receive(incoming);
					String msg = new String(incoming.getData(), 0, incoming.getLength());
					System.out.println(msg);
				} catch (IOException e) {
					break;
				}
			}
		}
	}

	public class Send extends Thread {
		@Override
		public void run() {
			while (true) {
				String input = sc.nextLine();
				if(input.equals("exit")) {
					socket.close();
					break;
				}
				String msgSend = name + ": " + input;
				outsending = new DatagramPacket(msgSend.getBytes(), msgSend.length(), addrConect, portConect);
				try {
					socket.send(outsending);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
