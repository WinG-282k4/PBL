package Model;

public class Item {
	String id;
	String name;
	String value;
	
	public Item(String itid, String itname, String itvalue) {
		id = itid;
		name = itname;
		value = itvalue;
	}
	
	public Item(String tid, String itvalue) {
		id = tid;
		value = itvalue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public void display() {
		System.out.print("ID: " + id +"\n");
		System.out.print("Value: " + value + "\n");
	}
}
