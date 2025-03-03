package shape;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IShape extends Remote {

	int chuviTamGiac(int a, int b, int c) throws RemoteException;

	int chuviHCN(int a, int b) throws RemoteException;

	int chuviHV(int a) throws RemoteException;

	float dientichTamGiac(int a, int b, int c) throws RemoteException;

	int dientichHCN(int a, int b) throws RemoteException;

	int dientichHV(int a) throws RemoteException;
}
