package Model;

import java.util.List;

public class Host_Group {
	String name;
	String id;
	List<Host> hosts;
	
	public String id() {
		return id;
	}
	public String name() {
		return name;
	}
	
	public List<Host> hosts(){
		return hosts;
	}
	
	public Host_Group(String hgid, String hgname, List<Host> lhost) {
		id = hgid;
		name = hgname;
		hosts = lhost;
	}
}
