package server2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculateServer2 extends UnicastRemoteObject implements ICalculateServer2 {
	public CalculateServer2() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int multiply(int a, int b) throws RemoteException {
		return a * b;
	}
	@Override
	public float divide(int a, int b) throws RemoteException {
		return b == 0 ? 0 : (float) a / (float) b;
	}
}
