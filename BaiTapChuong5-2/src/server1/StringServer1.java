package server1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringServer1 extends UnicastRemoteObject implements IStringServer1 {
	public StringServer1() throws RemoteException {
		super();
	}
	@Override
	public String to_lower(String str) throws RemoteException {
		char s[] = str.toCharArray();
		for (int i = 0; i < s.length; i++) {
			if (s[i] >= 65 && s[i] <= 90) {
				s[i] = (char) (s[i] + 32);
			}
		}
		return new String(s);
	}
	@Override
	public String to_upper(String str) throws RemoteException {
		char s[] = str.toCharArray();
		for (int i = 0; i < s.length; i++) {
			if (s[i] >= 'a' && s[i] <= 'z') {
				s[i] = (char) (s[i] - 32);
			}
		}
		return new String(s);
	}
}
