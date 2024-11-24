package Model;

import java.util.List;

public class Device {
	String Name;
	String hostid;
	String hostIP;
	Item SNMP;
	Item CPU;
	Item bitReceive;
	Item bitSend;
	Item RAM_total;
	Item RAM_used;
	Item RAM_util;
	Item Time_hardware;
	Item Time_network;
	List<Disk> ListDisk;
	
	public Device( String name, String id, String IP, Item SNMP_avai, Item CPUul, Item receive, Item send, Item R_t, Item R_us, Item R_ut, Item hard, Item net, List<Disk> LD ) {
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
		System.out.print("SNMP: " +SNMP.getId() + "\t" + SNMP.getValue() + "\n");
		System.out.print("CPU: " + CPU.getId() + "\t" +  CPU.getValue() + "\n");
		System.out.print("Bit_receive: " + bitReceive.getId() + "\t"+ bitReceive.getValue() + "\n");
		System.out.print("Bit_send: " + bitSend.getId() + "\t" + bitSend.getValue() + "\n");
		System.out.print("Ram - total: " + RAM_total.getId() + "\t" + RAM_total.getValue() + "\n");
		System.out.print("Ram - used : " + RAM_used.getId() + "\t" + RAM_used.getValue() + "\n");
		System.out.print("Ram - util: " + RAM_util.getId() + "\t" + RAM_util.getValue() + "\n");
		System.out.print("Time-hardware: " + Time_hardware.getId() + "\t" + Time_hardware.getValue() + "\n");
		System.out.print("Time-network: " + Time_network.getId() + "\t" + Time_network.getValue() + "\n");
		
		for(Disk D:ListDisk) {
			System.out.print(D.name + ":\t" + D.lastValue + "\n");
		}
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public Item getSNMP() {
		return SNMP;
	}

	public void setSNMP(Item sNMP) {
		SNMP = sNMP;
	}

	public Item getCPU() {
		return CPU;
	}

	public void setCPU(Item cPU) {
		CPU = cPU;
	}

	public Item getBitReceive() {
		return bitReceive;
	}

	public void setBitReceive(Item bitReceive) {
		this.bitReceive = bitReceive;
	}

	public Item getBitSend() {
		return bitSend;
	}

	public void setBitSend(Item bitSend) {
		this.bitSend = bitSend;
	}

	public Item getRAM_total() {
		return RAM_total;
	}

	public void setRAM_total(Item rAM_total) {
		RAM_total = rAM_total;
	}

	public Item getRAM_used() {
		return RAM_used;
	}

	public void setRAM_used(Item rAM_used) {
		RAM_used = rAM_used;
	}

	public Item getRAM_util() {
		return RAM_util;
	}

	public void setRAM_util(Item rAM_util) {
		RAM_util = rAM_util;
	}

	public Item getTime_hardware() {
		return Time_hardware;
	}

	public void setTime_hardware(Item time_hardware) {
		Time_hardware = time_hardware;
	}

	public Item getTime_network() {
		return Time_network;
	}

	public void setTime_network(Item time_network) {
		Time_network = time_network;
	}

	public List<Disk> getListDisk() {
		return ListDisk;
	}

	public void setListDisk(List<Disk> listDisk) {
		ListDisk = listDisk;
	}
}
