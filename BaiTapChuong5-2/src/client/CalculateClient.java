package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import server1.ICalculateServer1;
import server2.ICalculateServer2;

public class CalculateClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Registry rgsty1 = LocateRegistry.getRegistry(8000);
			Registry rgsty2 = LocateRegistry.getRegistry(8080);
			ICalculateServer1 calculate1 = (ICalculateServer1) rgsty1.lookup("rmi://localhost/calculate");
			ICalculateServer2 calculate2 = (ICalculateServer2) rgsty2.lookup("rmi://localhost/calculate");

			int a = 0;
			int b = 0;
			System.out.println("Nhập hai số:");
			System.out.println("Nhập a: ");
			a = sc.nextInt();
			System.out.println("Nhập b: ");
			b = sc.nextInt();

			System.out.println("Tổng a + b = " + calculate1.add(a, b));
			System.out.println("Hiệu a - b = " + calculate1.sub(a, b));
			System.out.println("Tích a * b = " + calculate2.multiply(a, b));
			System.out.println("Thương a / b = " + calculate2.divide(a, b));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
