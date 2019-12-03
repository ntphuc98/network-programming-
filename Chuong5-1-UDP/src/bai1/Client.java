package bai1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Registry rgsty = LocateRegistry.getRegistry(8000);
			IProcess process = (IProcess) rgsty.lookup("rmi://localhost/process");
			int a = 0;
			int b = 0;
			System.out.println("Nhập hai số:");
			System.out.println("Nhập a: ");
			a = sc.nextInt();
			System.out.println("Nhập b: ");
			b = sc.nextInt();
			
			System.out.println("Tổng a + b = " + process.add(a, b));
			System.out.println("Hiệu a - b = " + process.sub(a, b));
			sc.nextLine();
			
			String input;
			System.out.println("Nhập vào chuỗi: ");
			input = sc.nextLine();
			
			System.out.println("Chuỗi viết hoa: " + process.to_upper(input));
			System.out.println("Chuỗi viết thường: " + process.to_lower(input));
			System.out.println("Chuỗi viết hoa mỗi từ: " + process.to_title(input));
		} catch (RemoteException| NotBoundException e) {
			e.printStackTrace();
		}
	}

}
