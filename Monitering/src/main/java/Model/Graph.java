package Model;

public class Graph {

	String label;
	String value;
	
	public Graph(String tlabel, String tvalue) {

		label = tlabel;
		value = tvalue;
	}
	public Graph () {
		
	}
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
