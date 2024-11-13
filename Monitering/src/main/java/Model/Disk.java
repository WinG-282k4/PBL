package Model;

//Class để lưu thông tin ổ đĩa
public class Disk {
	    public String name;
	    public String lastValue;

	    public Disk(String name, String lastValue) {
	        this.name = name;
	        this.lastValue = lastValue;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastValue() {
			return lastValue;
		}

		public void setLastValue(String lastValue) {
			this.lastValue = lastValue;
		}
	
}
