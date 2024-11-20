package Model;

import java.util.List;

public class Device {
	String Name;
	String hostid;
	String hostIP;
	String SNMP;
	String CPU;
	String bitReceive;
	String bitSend;
	String RAM_total;
	String RAM_used;
	String RAM_util;
	String Time_hardware;
	String Time_network;
	List<Disk> ListDisk;
	
	public Device( String name, String id, String IP, String SNMP_avai, String CPUul, String receive, String send, String R_t, String R_us, String R_ut, String hard, String net, List<Disk> LD ) {
		Name = name;
		hostid = id;
		hostIP = IP ;
		SNMP = SNMP_avai;
		CPU = CPUul;
		bitReceive = receive;
		bitSend = send;
		RAM_total = R_t;
		RAM_used = R_us;
		RAM_util = R_ut;
		Time_hardware = hard;
		Time_network = net;
		ListDisk = LD;
	}
	
	public Device() {
		
	}
	
	public void Display() {
		
		System.out.print("Name: " + Name + "\n");
		System.out.print("ID: " + hostid + "\n");
		System.out.print("IP: " + hostIP + "\n");
		System.out.print("SNMP: " + SNMP + "\n");
		System.out.print("CPU: " + CPU + "\n");
		System.out.print("Bit_receive: " + bitReceive + "\n");
		System.out.print("Bit_send: " + bitSend + "\n");
		System.out.print("Ram - total: " + RAM_total + "\n");
		System.out.print("Ram - used : " + RAM_used + "\n");
		System.out.print("Ram - util: " + RAM_util + "\n");
		System.out.print("Time-hardware: " + Time_hardware + "\n");
		System.out.print("Time-network: " + Time_network + "\n");
		
		for(Disk D:ListDisk) {
			System.out.print(D.name + ":\t" + D.lastValue + "\n");
		}
	}
}
