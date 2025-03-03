package server2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringServer2 extends UnicastRemoteObject implements IStringServer2{
	public StringServer2() throws RemoteException {
		super();
	}
	@Override
	public String to_title(String str) throws RemoteException {
		char s[] = str.toCharArray();
		
		for (int i = 0; i < s.length; i++) {
			if (s[i] >= 'A' && s[i] <= 'Z')
				s[i] += 32;
			if ( i == 0 || s[i - 1] == ' ') {
				if (s[i] >= 'a' && s[i] <= 'z')
					s[i] = (char) (s[i] - 32);
			}
		}
		return new String(s);
	}
}
