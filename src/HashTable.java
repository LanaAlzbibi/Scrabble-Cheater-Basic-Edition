import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {

	private final int hashTableSize = 1500;
	LinkedList<String>[] hashT;

	Dictionary[] hashTable;

	HashTable() {
		hashTable = new Dictionary[hashTableSize];
		for (int i = 0; i < hashTableSize; i++)
			hashTable[i] = null;
	}
	

	public String get(int hash) {
		if (hashTable[hash] == null)
			return null;
		else {
			Dictionary dic = hashTable[hash];
			while (dic != null && dic.getHash() != hash)
				dic = dic.getNext();

			if (dic == null)
				return null;
			else
				return dic.getValue();
		}
	}
	public 	LinkedList<String> getIndex(int index){
		return hashT[index];
	}

	public void add(int hash, String word) {

		if (hashTable[hash] == null)
			hashTable[hash] = new Dictionary(hash, word);
		else {
			Dictionary dic = hashTable[hash];
			while (dic.getNext() != null && dic.getHash() == hash) {
				dic = dic.getNext();
			}
			dic.setNext(new Dictionary(hash, word));
		}
		
	}
	

	public void remove(int hash) {
		if (hashTable[hash] != null) {
			Dictionary prevValue = null;
			Dictionary value = hashTable[hash];
			while (value.getNext() != null && value.getHash() != hash) {
				prevValue = value;
				value = value.getNext();
			}
			if (value.getHash() == hash) {
				if (prevValue == null)
					hashTable[hash] = value.getNext();
				else
					prevValue.setNext(value.getNext());
			}
		}
	}


}