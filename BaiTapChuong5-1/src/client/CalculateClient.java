package client;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import calculate.ICalculate;
import shape.IShape;

public class CalculateClient {

	public static void main(String[] args) {
		Registry rgsty;
		Scanner sc = new Scanner(System.in);
		try {
			rgsty = LocateRegistry.getRegistry(8000);
			ICalculate calculate = (ICalculate) rgsty.lookup("rmi://localhost/calculate");
			int a = 0;
			int b = 0;
			System.out.println("Nhập hai số:");
			System.out.println("Nhập a: ");
			a = sc.nextInt();
			System.out.println("Nhập b: ");
			b = sc.nextInt();
			
			System.out.println("Tổng a + b = " + calculate.add(a, b));
			System.out.println("Hiệu a - b = " + calculate.sub(a, b));
			System.out.println("Tích a * b = " + calculate.multiply(a, b));
			System.out.println("Thương a / b = " + calculate.divide(a, b));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
