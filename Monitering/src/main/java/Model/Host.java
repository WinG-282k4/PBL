package Model;

public class Host {
	String hostName;
	String hostid;
	String hostIP;
	String SNMP;
	String SNMP_community;
	String SNMP_version;
	
	public Host(String name, String id, String IP, String status, String community, String version) {
		// TODO Auto-generated constructor stub
		hostName = name;
		hostid = id;
		hostIP = IP;
		SNMP = status;
		SNMP_community = community;
		SNMP_version = version;
	}
	
	public void Display() {
		
		System.out.print("host name: " + hostName + "\n");
		System.out.print("ID: " + hostid + "\n");
		System.out.print("IP: " + hostIP + "\n");
		System.out.print("SNMP: " + SNMP + "\n");
		System.out.print("Community: " + SNMP_community + "\n");
		System.out.print("SNMP version: " + SNMP_version + "\n");

	}
}
