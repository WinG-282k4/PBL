package Model;

import java.util.List;

public class Device {
	private String Name;
	private String hostid;
	private String hostIP;
	private String SNMP;
	private String CPU;
	private String bitReceive;
	private String bitSend;
	private String RAM_total;
	private String RAM_used;
	private String RAM_util;
	private String Time_hardware;
	private String Time_network;
	private List<Disk> ListDisk;
	
	

	
	
	
	public Device(String name, String hostid, String hostIP, String sNMP, String cPU, String bitReceive, String bitSend,
			String rAM_total, String rAM_used, String rAM_util, String time_hardware, String time_network,
			List<Disk> listDisk) {
		super();
		Name = name;
		this.hostid = hostid;
		this.hostIP = hostIP;
		SNMP = sNMP;
		CPU = cPU;
		this.bitReceive = bitReceive;
		this.bitSend = bitSend;
		RAM_total = rAM_total;
		RAM_used = rAM_used;
		RAM_util = rAM_util;
		Time_hardware = time_hardware;
		Time_network = time_network;
		ListDisk = listDisk;
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



	public String getSNMP() {
		return SNMP;
	}



	public void setSNMP(String sNMP) {
		SNMP = sNMP;
	}



	public String getCPU() {
		return CPU;
	}



	public void setCPU(String cPU) {
		CPU = cPU;
	}



	public String getBitReceive() {
		return bitReceive;
	}



	public void setBitReceive(String bitReceive) {
		this.bitReceive = bitReceive;
	}



	public String getBitSend() {
		return bitSend;
	}



	public void setBitSend(String bitSend) {
		this.bitSend = bitSend;
	}



	public String getRAM_total() {
		return RAM_total;
	}



	public void setRAM_total(String rAM_total) {
		RAM_total = rAM_total;
	}



	public String getRAM_used() {
		return RAM_used;
	}



	public void setRAM_used(String rAM_used) {
		RAM_used = rAM_used;
	}



	public String getRAM_util() {
		return RAM_util;
	}



	public void setRAM_util(String rAM_util) {
		RAM_util = rAM_util;
	}



	public String getTime_hardware() {
		return Time_hardware;
	}

	

	public void setTime_hardware(String time_hardware) {
		Time_hardware = time_hardware;
	}



	public String getTime_network() {
		return Time_network;
	}



	public void setTime_network(String time_network) {
		Time_network = time_network;
	}



	public List<Disk> getListDisk() {
		return this.ListDisk;
	}



	public void setListDisk(List<Disk> listDisk) {
		ListDisk = listDisk;
	}

	
	
}
