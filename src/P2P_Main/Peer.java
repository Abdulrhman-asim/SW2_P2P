package P2P_Main;

import java.io.*;
import java.net.*;
import java.util.*;

public class Peer {

	private MulticastSocket socket;
	private InetAddress address;
	private String myip;
	private String username;
	private HashMap<String, String> users;

	public Peer() throws UnknownHostException {

		myip = InetAddress.getLocalHost().getHostAddress();
		username = "Guest";
		users = new HashMap<String, String>();
	}

	public Peer(String name) throws UnknownHostException {
		this();
		username = name;

	}

	public String receive() throws IOException {

		if (socket.isClosed()) {
			return "Socket is closed";
		}
		DatagramPacket packet;
		byte[] buf = new byte[256];
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());

		InetAddress rawsenderadd = packet.getAddress();
		String senderAdd = rawsenderadd.toString();
		senderAdd = senderAdd.substring(1, senderAdd.length());
		if (received.charAt(0) == '0') {
			if (!(senderAdd.equals(myip))) {
				System.out.println(received.substring(1, received.length()));
			}
			return received.substring(1, received.length());
		}

		else if (received.charAt(0) == '1') {
			for (int i = 0; i < received.length(); i++) {
				if (received.charAt(i) == '~') {
					if (!(senderAdd.equals(myip))) {
						System.out.println(received.substring(1, i) + " Has Joined the group");
						users.put(senderAdd, received.substring(1, i));
					}
					return received.substring(1, i) + " Has Joined the group";
				}
			}

		}

		else if (received.charAt(0) == '2') {
			for (int i = 0; i < received.length(); i++) {
				if (received.charAt(i) == '~') {
					if (!(senderAdd.equals(myip))) {
						System.out.println(received.substring(1, i) + " Has left the group");
						users.remove(senderAdd);
					}
					return received.substring(1, i) + " Has left the group";
				}

			}

		}

		return "~";

	}

	public String PeersAvail() {

		return " ";
	}

	public void leave() throws IOException {

		byte[] buff = new byte[256];
		String dString = myip;
		dString = "2" + username + "~" + dString;
		buff = dString.getBytes();

		DatagramPacket packet2 = new DatagramPacket(buff, buff.length, address, 4446);
		socket.send(packet2);
		socket.leaveGroup(address);
		socket.close();
	}

	public void join() throws IOException {
		socket = new MulticastSocket(4446);
		address = InetAddress.getByName("225.0.0.1");
		socket.joinGroup(address);

		byte[] buff = new byte[256];
		String dString = myip;
		dString = "1" + username + "~" + dString;
		buff = dString.getBytes();

		DatagramPacket packet2 = new DatagramPacket(buff, buff.length, address, 4446);
		socket.send(packet2);

	}

	public void send() throws IOException {

		if (socket.isClosed()) {
			return;
		}

		Scanner cin = new Scanner(System.in);
		byte[] buff = new byte[256];
		String dString = cin.nextLine();
		dString = "0" + username + ": " + dString;
		buff = dString.getBytes();

		DatagramPacket packet2 = new DatagramPacket(buff, buff.length, address, 4446);
		socket.send(packet2);
		cin.close();

	}

	public String getUsername() {
		return username;
	}

	public void sendGenerated(String text) throws IOException {

		if (socket.isClosed()) {
			return;
		}
		byte[] buff = new byte[256];

		
			String dString = text;
			dString = "0" + username + ": " + dString;
			buff = dString.getBytes();

		
		DatagramPacket packet2 = new DatagramPacket(buff, buff.length, address, 4446);
		socket.send(packet2);

	}
	public void notify(String text) throws IOException {

		if (socket.isClosed()) {
			return;
		}
		byte[] buff = new byte[256];

		
			String dString = text;
			dString = "0" + dString;
			buff = dString.getBytes();

		
		DatagramPacket packet2 = new DatagramPacket(buff, buff.length, address, 4446);
		socket.send(packet2);

	}

}