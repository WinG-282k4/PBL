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
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
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

	public String getGroupid() {
		return Groupid;
	}

	public void setGroupid(String groupid) {
		Groupid = groupid;
	}

	public String getGroupname() {
		return Groupname;
	}

	public void setGroupname(String groupname) {
		Groupname = groupname;
	}

	public String getSNMP() {
		return SNMP;
	}

	public void setSNMP(String sNMP) {
		SNMP = sNMP;
	}

	public String getSNMP_community() {
		return SNMP_community;
	}

	public void setSNMP_community(String sNMP_community) {
		SNMP_community = sNMP_community;
	}

	public String getSNMP_version() {
		return SNMP_version;
	}

	public void setSNMP_version(String sNMP_version) {
		SNMP_version = sNMP_version;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
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
	
	public Host(String name, String id) {
		hostName = name;
		hostid =id;
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
