package Model;

public class Host {
	String hostName;
	String hostid;
	String hostIP;
	String Groupid;
	String Groupname;
	String SNMP;
	String SNMP_community;
	String SNMP_version;
	String Description;
	
	public String id() {
		return hostid;
	}
	
	public String groupid() {
		return Groupid;
	}
	
	public String name() {
		return hostName;
	}
	
	public String IP() {
		return hostIP;
	}
	
	public String version() {
		return SNMP_version;
	}
	
	public String community() {
		return SNMP_community;
	}
	
	public String descript() {
		return Description;
	}
	
	public Host(String name, String id, String Group, String gname, String IP, String status, String community, String version, String Decript) {
		// TODO Auto-generated constructor stub
		hostName = name;
		hostid = id;
		Groupid = Group;
		Groupname = gname;
		hostIP = IP;
		SNMP = status;
		SNMP_community = community;
		SNMP_version = version;
		Description = Decript;
	}
	
	public void Display() {
		
		System.out.print("host name: " + hostName + "\n");
		System.out.print("ID: " + hostid + "\n");
		System.out.print("Group: " + Groupname + "\n" );
		System.out.print("Groupid: " + Groupid + "\n");
		System.out.print("IP: " + hostIP + "\n");
		System.out.print("SNMP: " + SNMP + "\n");
		System.out.print("Community: " + SNMP_community + "\n");
		System.out.print("SNMP version: " + SNMP_version + "\n");
		System.out.print("Decription: " + Description + "\n");

	}
}
