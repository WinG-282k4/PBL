package Model;

import java.util.List;

import Models.ZabbixAPI.DiskInfo;

public class devide {
	private String hostID;
	private String hostName;
	private String nameDevide;
	private String CPU;
	private String bitReceive;
	private String bitSend;
	private String RAM;
	private String RAM_used;
	private String RAM_util;
	private String time_Hardware;
	private String time_Netword;
	private List<DiskInfo> list;
	public devide(String hostID, String hostName, String nameDevide, String cPU, String bitReceive, String bitSend,
			String rAM, String rAM_used, String rAM_util, String time_Hardware, String time_Netword, List<DiskInfo> list) {
		super();
		this.hostID = hostID;
		this.hostName = hostName;
		this.nameDevide = nameDevide;
		CPU = cPU;
		this.bitReceive = bitReceive;
		this.bitSend = bitSend;
		RAM = rAM;
		RAM_used = rAM_used;
		RAM_util = rAM_util;
		this.time_Hardware = time_Hardware;
		this.time_Netword = time_Netword;
		this.list = list;
	}
	public String getHostID() {
		return hostID;
	}
	public void setHostID(String hostID) {
		this.hostID = hostID;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getNameDevide() {
		return nameDevide;
	}
	public void setNameDevide(String nameDevide) {
		this.nameDevide = nameDevide;
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
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
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
	public String getTime_Hardware() {
		return time_Hardware;
	}
	public void setTime_Hardware(String time_Hardware) {
		this.time_Hardware = time_Hardware;
	}
	public String getTime_Netword() {
		return time_Netword;
	}
	public void setTime_Netword(String time_Netword) {
		this.time_Netword = time_Netword;
	}
	public List<DiskInfo> getList() {
		return list;
	}
	public void setList(List<DiskInfo> list) {
		this.list = list;
	}
	
}
