package server2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShapeServer2 extends UnicastRemoteObject implements IShapeServer2{
	public ShapeServer2() throws RemoteException {
		super();
	}
	@Override
	public float dientichTamGiac(int a, int b, int c) throws RemoteException {
		if (a >= b + c || b >= a + c || c >= a + b)
	        return 0;
		float p = (float) (a + b + c) / 2;
		return (float) Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}
	@Override
	public int dientichHCN(int a, int b) throws RemoteException {
		return a * b;
	}
	@Override
	public int dientichHV(int a) throws RemoteException {
		return a * a;
	}
}
