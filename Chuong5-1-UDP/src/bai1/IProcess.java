package bai1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProcess extends Remote{
	int add(int a, int b) throws RemoteException;
	int sub(int a, int b) throws RemoteException;
	String to_lower(String str) throws RemoteException;
	String to_upper(String str) throws RemoteException;
	String to_title(String str) throws RemoteException;
}
