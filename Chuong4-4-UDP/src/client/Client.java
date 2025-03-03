package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static int serverPort;
	public static DatagramSocket ds;

	public static void printMenu() {
		String menu = "-------------------MENU----------------\n" 
				+ "Nhập '1' Đăng nhập.\n" 
				+ "Nhập '2' Đăng ký.\n"
				+ "Nhập 'q' thoát.\n" 
				+ "----------------------------------------";
		System.out.println(menu);
	}

	public static void main(String[] args) {
		try {
			ds = new DatagramSocket();
			DatagramPacket response;
			InetAddress server = InetAddress.getByName("localhost");
			Client.serverPort = 8000;
			Client.printMenu();
			Receive receive = new Receive();
			receive.start();
			
			while (true) {
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String theString = br.readLine();

				if (theString.equals("q")) {
					ds.close();
					System.out.println("Kết thúc!");
					break;
				}
				byte[] data = theString.getBytes();
				response = new DatagramPacket(data, data.length, server, serverPort);
				ds.send(response);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

class Receive extends Thread {
	private DatagramPacket incoming;

	public Receive() {
		byte[] buffer = new byte[6000];
		incoming = new DatagramPacket(buffer, buffer.length);
	}

	public void run() {
		while (true) {
			try {
				Client.ds.receive(incoming);
				String msg = new String(incoming.getData(), 0, incoming.getLength());
				System.out.println(msg);
			} catch (IOException e) {
				break;
			}
		}
	}
}
