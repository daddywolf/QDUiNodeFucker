package action;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Network {
	public static boolean isReachIp(String ip) {
		boolean isReach = false;
		try {
			InetAddress address = InetAddress.getByName(ip);// ping this IP
			if (address.isReachable(5000)) {
				isReach = true;
				System.out.println("true");
			} else {
				isReach = false;
				System.out.println("false");
			}
		} catch (Exception e) {
			System.out.println("exception");
			System.out.println(e.toString());
		}
		return isReach;
	}

}
