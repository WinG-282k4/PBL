package Model;

public class Disk {
	    String name;
	    String lastValue;

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
