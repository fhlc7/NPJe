package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PgarIPTeste {
	public static void main(String[] args) {
		
		try {
			System.out.println(InetAddress.getByName("DESKTOP-55DUIF2").getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		
	}
	
}
