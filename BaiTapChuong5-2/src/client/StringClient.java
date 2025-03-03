package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import server1.IStringServer1;
import server2.IStringServer2;

public class StringClient {

	public static void main(String[] args) {
		Registry rgsty1;
		Registry rgsty2;
		Scanner sc = new Scanner(System.in);
		try {
			rgsty1 = LocateRegistry.getRegistry(8000);
			IStringServer1 str = (IStringServer1) rgsty1.lookup("rmi://localhost/string");
			rgsty2 = LocateRegistry.getRegistry(8080);
			IStringServer2 str2 = (IStringServer2) rgsty2.lookup("rmi://localhost/string");
			
			String input;
			System.out.println("Nhập vào chuỗi: ");
			input = sc.nextLine();
			
			System.out.println("Chuỗi viết hoa: " + str.to_upper(input));
			System.out.println("Chuỗi viết thường: " + str.to_lower(input));
			System.out.println("Chuỗi viết hoa mỗi từ: " + str2.to_title(input));
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
