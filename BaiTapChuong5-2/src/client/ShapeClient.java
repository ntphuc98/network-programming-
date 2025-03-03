package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import server1.IShapeServer1;
import server2.IShapeServer2;

public class ShapeClient {

	public static void main(String[] args) {
		Registry rgsty1;
		Registry rgsty2;
		Scanner sc = new Scanner(System.in);
		try {
			rgsty1 = LocateRegistry.getRegistry(8000);
			IShapeServer1 shape = (IShapeServer1) rgsty1.lookup("rmi://localhost/shape");
			rgsty2 = LocateRegistry.getRegistry(8080);
			IShapeServer2 shape2 = (IShapeServer2) rgsty2.lookup("rmi://localhost/shape");
			
			int a, b, c;
			System.out.println("Nhập 3 cạnh tam giác");
			System.out.println("Nhập cạnh a: ");
			a = sc.nextInt();
			System.out.println("Nhập cạnh b: ");
			b = sc.nextInt();
			System.out.println("Nhập cạnh c: ");
			c = sc.nextInt();
			System.out.println("Chu vi hình tam giác: " + shape.chuviTamGiac(a, b, c));
			System.out.println("Diện tích hình tam giác: " + shape2.dientichTamGiac(a, b, c));
			System.out.println("----------------------------------------");
			
			int d, r;
			System.out.println("Nhập 2 cạnh hình chữ nhật");
			System.out.println("Nhập cạnh dài: ");
			d = sc.nextInt();
			System.out.println("Nhập cạnh rộng: ");
			r = sc.nextInt();
			System.out.println("Chu vi hình chữ nhật: " + shape.chuviHCN(d, r));
			System.out.println("Diện tích hình chữ nhật: " + shape2.dientichHCN(d, r));
			System.out.println("----------------------------------------");
			
			int v;
			System.out.println("Nhập cạnh hình vuông: ");
			v = sc.nextInt();
			System.out.println("Chu vi hình vuông: " + shape.chuviHV(v));
			System.out.println("Diện tích hình vuông: " + shape2.dientichHV(v));
			System.out.println("----------------------------------------");
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
