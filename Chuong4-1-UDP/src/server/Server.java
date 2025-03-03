package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * Server UDP Java
 * Đọc số thành chữ
 * ntphuc
 */
public class Server {
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
					Process process = new Process(socket, incoming);
					process.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}

class Process extends Thread {
	DatagramSocket socket;
	DatagramPacket incoming;
	DatagramPacket response;
	String msgRq;
	String msgRp;
	String[] so = { "", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín" };

	public Process(DatagramSocket socket, DatagramPacket incoming) {
		this.socket = socket;
		this.incoming = incoming;
	}

	public void run() {
		try {
			msgRq = new String(incoming.getData(), 0, incoming.getLength());
			msgRp = readNumber(msgRq);
			byte[] output = msgRp.getBytes("UTF-8");
			response = new DatagramPacket(output, output.length, incoming.getAddress(), incoming.getPort());
			socket.send(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean checkNumber(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String readNumber(String str) {
		if (checkNumber(str)) {
			long number = Long.parseLong(str);
			if (number == 0)
				return "không";
			return readNumber(number);
		} else {
			return "Không phải số nguyên!";
		}
	}

	public int countNumber(long n) {
		int dem = 0;
		while (n >= 10) {
			n /= 10;
			dem++;
		}
		return dem + 1;
	}

	public String readNumber(long n) {
		int count = countNumber(n);
		if (count == 1) {
			return so[(int) (n % 10)];
		}
		if (count == 2) {
			if (n < 20)
				if ((n % 10) == 0)
					return "mười";
				else
					return "mười " + so[(int) (n % 10)];
			else {
				if (n % 10 == 0) {
					return so[(int) (n / 10 % 10)] + " mươi";
				} else {
					return so[(int) (n / 10 % 10)] + " mươi " + so[(int) (n % 10)];
				}
			}
		}
		if (count == 3) {
			if (n % 100 < 10 && n % 100 > 0) {
				return so[(int) (n / 100 % 10)] + " trăm linh " + so[(int) (n % 10)];
			} else if (n % 100 == 0) {
				return so[(int) (n / 100 % 10)] + " trăm";
			} else {
				return so[(int) (n / 100 % 10)] + " trăm " + readNumber(n % 100);
			}
		}
		if (count > 3 && count <= 6) {
			if (n % 1000 == 0)
				return readNumber(n / 1000) + " nghìn";
			if ((n % 1000) / 100 == 0)
				if (n % 1000 <= 10) {
					return readNumber(n / 1000) + " nghìn không trăm linh " + readNumber(n % 1000);
				} else {
					return readNumber(n / 1000) + " nghìn không trăm " + readNumber(n % 1000);
				}
			else
				return readNumber(n / 1000) + " nghìn " + readNumber(n % 1000);
		}
		if (count > 6 && count <= 9) {
			if (n % 1000000 == 0)
				return readNumber(n / 1000000) + " triệu";
			else
				return readNumber(n / 1000000) + " triệu " + readNumber(n % 1000000);
		}
		if (count > 9) {
			if (n % 1000000000 < 100 && n % 1000000000 > 0) {
				return readNumber(n / 1000000000) + " tỷ lẻ " + readNumber(n % 1000000000);
			} else {
				return readNumber(n / 1000000000) + " tỷ " + readNumber(n % 1000000000);
			}
		}
		return "";
	}
}
