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
		
		while(true) {
			printMenu();
			String msg = sc.nextLine();
			if(msg.equals("exit")) {
				socket.close();
				break;
			}
			String cover = cover(msg);
			byte[] data = cover.getBytes();
			request = new DatagramPacket(data, data.length, serverAddr, portServer);
			try {
				socket.send(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printMenu() {
		String menu = "Nhập phép toán - nhập exit để thoát: \"";
		System.out.println(menu);
	}
	
	public String cover(String str) {
		String result = " ";
		try {
			if(str.contains("+")) {
				String[] arr = str.split("\\+");
				result = "+ " + arr[0].trim() + " " + arr[1].trim();
			}
			else if(str.contains("-")) {
				String[] arr = str.split("\\-");
				result = "- " + arr[0].trim() + " " + arr[1].trim();
			}
			else if(str.contains("*")) {
				String[] arr = str.split("\\*");
				result = "* " + arr[0].trim() + " " + arr[1].trim();
			}
			else if(str.contains("/")) {
				String[] arr = str.split("\\/");
				result = "/ " + arr[0].trim() + " " + arr[1].trim();
			}else { 
				result = str;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			result = "Khong dung dinh dang";
		}
		return result;
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
		data = new byte[6000];
		incoming = new DatagramPacket(data, data.length);
	}

	public void run() {
		while(true) {
			try {
				socket.receive(incoming);
				String msg = new String(incoming.getData(), 0, incoming.getLength());
				System.out.println(msg);
			} catch (IOException e) {
				break;
			}
		}
	}

}
