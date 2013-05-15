package TCPIP_SOCKET;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.util.P;

public class InerAddressExample
{
	public static void main(String[] args)
	{
		try
		{
			Enumeration<NetworkInterface> interfaceList = NetworkInterface
					.getNetworkInterfaces();

			if (interfaceList == null)
			{
				P.print("--No interfaces found--");
			}
			else
			{
				while (interfaceList.hasMoreElements())
				{
					NetworkInterface iface = interfaceList.nextElement();
					P.print("Interface " + iface.getName());
					Enumeration<InetAddress> addrList = iface
							.getInetAddresses();
					printAddress(addrList);
				}
			}
		}
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{

			for (String host : args)
			{
				P.print(host);
				InetAddress[] addressList = InetAddress.getAllByName(host);
				printHostAddress(addressList);
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}

	private static void printHostAddress(InetAddress[] addressList)
	{
		for (InetAddress address : addressList)
		{
			P.print(address.getHostName() + "/" + address.getHostAddress());
		}
	}

	private static void printAddress(Enumeration<InetAddress> addrList)
	{
		while (addrList.hasMoreElements())
		{
			InetAddress address = addrList.nextElement();
			P.print("\tAddress "
					+ (address instanceof Inet4Address ? "v4"
							: (address instanceof Inet4Address ? "v6" : "(?)")));
			P.print(address.getHostAddress());
		}
	}
}
