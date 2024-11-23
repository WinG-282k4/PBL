package Model;

public class Item {
	String id;
	String value;
	
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
