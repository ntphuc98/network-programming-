package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * Server UDP Java
 * Tính toán
 * ntphuc
 */
public class TTServer {
	public static void main(String[] args) {
		DatagramSocket socket;
		byte data[] = new byte[6000];
		DatagramPacket incoming = new DatagramPacket(data, data.length);

		try {
			socket = new DatagramSocket(8000);
			System.out.println("Server đang hoạt động...");
			while (true) {
				try {
					socket.receive(incoming);
					TTProcess process = new TTProcess();
					process.run(socket, incoming);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}

class TTProcess {
	public void run(DatagramSocket socket, DatagramPacket incoming) {
		try {
			String msgRq = new String(incoming.getData(), 0, incoming.getLength());
			String msgRp = calculateTwoNumbers(msgRq);
			DatagramPacket response = new DatagramPacket(msgRp.getBytes(), msgRp.length(), incoming.getAddress(),
					incoming.getPort());
			socket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String calculateTwoNumbers(String str) {
		str = str.trim();
		if (str.length() == 0) {
			return "Vui long nhap!";
		}
		String[] input = str.split(" ");
		if (input.length != 3) {
			return "Chuoi khong hop le!";
		}
		char p = input[0].charAt(0);
		int a, b;
		try {
			a = Integer.parseInt(input[1]);
			b = Integer.parseInt(input[2]);
		} catch (NumberFormatException e) {
			return "Chuoi khong hop le!";
		}
		switch (p) {
		case '+':
			return Integer.toString((a + b));
		case '-':
			return Integer.toString((a - b));
		case '*':
			return Integer.toString((a * b));
		case '/':
			float rs = (float) a / (float) b;
			return Float.toString(rs);
		default:
			return "Cac toan tu hop le: '+', '-', '*', '/'.";
		}
	}
}
