import java.util.ArrayList;

public class Dictionary {
	private int hash;
	private String value;
	private Dictionary next;

	Dictionary(int hash, String value) {
		this.hash = hash;
		this.value = value;
		this.next = null;
	}

	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}

	public int getHash() {
		return hash;
	}

	public Dictionary getNext() {
		return next;
	}

	public void setNext(Dictionary next) {
		this.next = next;
	}

	public String toStrings() {
		String result = value + " ";
		if (next != null) {
			result += next.toStrings();
		}
		return result;
	}
}
