package server1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculateServer1 extends Remote{
	int add(int a, int b) throws RemoteException;
	int sub(int a, int b) throws RemoteException;
}
