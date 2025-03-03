package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import shape.IShape;

public class ShapeClient {

	public static void main(String[] args) {
		Registry rgsty;
		Scanner sc = new Scanner(System.in);
		try {
			rgsty = LocateRegistry.getRegistry(8000);
			IShape shape = (IShape) rgsty.lookup("rmi://localhost/shape");
			
			int a, b, c;
			System.out.println("Nhập 3 cạnh tam giác");
			System.out.println("Nhập cạnh a: ");
			a = sc.nextInt();
			System.out.println("Nhập cạnh b: ");
			b = sc.nextInt();
			System.out.println("Nhập cạnh c: ");
			c = sc.nextInt();
			System.out.println("Chu vi hình tam giác: " + shape.chuviTamGiac(a, b, c));
			System.out.println("Diện tích hình tam giác: " + shape.dientichTamGiac(a, b, c));
			System.out.println("----------------------------------------");
			
			int d, r;
			System.out.println("Nhập 2 cạnh hình chữ nhật");
			System.out.println("Nhập cạnh dài: ");
			d = sc.nextInt();
			System.out.println("Nhập cạnh rộng: ");
			r = sc.nextInt();
			System.out.println("Chu vi hình chữ nhật: " + shape.chuviHCN(d, r));
			System.out.println("Diện tích hình chữ nhật: " + shape.dientichHCN(d, r));
			System.out.println("----------------------------------------");
			
			int v;
			System.out.println("Nhập cạnh hình vuông: ");
			v = sc.nextInt();
			System.out.println("Chu vi hình vuông: " + shape.chuviHV(v));
			System.out.println("Diện tích hình vuông: " + shape.dientichHV(v));
			System.out.println("----------------------------------------");
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
