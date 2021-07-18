import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ScrabbleCheater {
	// source:https://github.com/powerlanguage/word-lists/blob/master/common-7-letter-words.txt
//	static String FILE_LOCATION = "/Users/Anni/Downloads/common-7-letter-words.txt";
	static String FILE_LOCATION = "C:\\Users\\lana\\Downloads\\common-7-letter-words.txt";
	ArrayList<String> fileList;

	final int hashTableSize = 1000;
	LinkedList<String>[] hashTable;
	static int maxCollisionCounter = 0;
	static int maxCollisionPosition = 0;

	public ScrabbleCheater() {
		hashTable = new LinkedList[hashTableSize];
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new LinkedList<String>();
		}
	}

	public static void main(String[] args) throws IOException {
		ScrabbleCheater main = new ScrabbleCheater();

		// reads file in general
		main.readFile();
		System.out.println("Maximum collision: " + maxCollisionCounter);
		System.out.println("Maximum collision position is " + maxCollisionPosition + ".");

		// check input in file
		HashTable hashT = new HashTable();
		main.readFileWords(FILE_LOCATION);

		for (String word : main.fileList) {
			hashT.add(main.generateHashCode(word), word);
		}

		// display input of "" and use method lookUp to check input
//		System.out.println(main.toString(main.lookUp("plpkmoj")));
		System.out.println(main.printResult(main.lookUp("plpkmoj")));
		System.out.println(main.getPermutation("awysrel"));


	}

	public void readFile() throws FileNotFoundException {
		File file = new File(FILE_LOCATION);
		String currentInput = "";
		BufferedReader bf = null;
		int count = 0;
		bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		try {
			currentInput = bf.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (currentInput != null) {
			try {
				currentInput = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (currentInput != null) {
				String n = normalize(currentInput);
				int hash = generateHashCode(n);
				hashTable(currentInput, hash);
			}
			count++;
		}

		try {
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The file consists of " + count + " words.");

	}

	private String normalize(String word) {
		char[] originalArray = word.toLowerCase().toCharArray();
		Arrays.sort(originalArray);
		String sorted = new String(originalArray);
		return sorted;
	}

	private void hashTable(String word, int hash) {
		if (hash > 0 && hash < hashTable.length) {
			hashTable[hash].add(word);
			int collisionC = hashTable[hash].size();
			if (collisionC > maxCollisionCounter) {
				maxCollisionCounter = collisionC;
				maxCollisionPosition = hash;
			}
		}
	}

	// read words in file
	private void readFileWords(String file) throws IOException {
		FileReader fr = new FileReader(FILE_LOCATION);
		BufferedReader br = new BufferedReader(fr);
		fileList = new ArrayList<String>();

		String words;
		while ((words = br.readLine()) != null) {
			fileList.add(words);
		}

		br.close();

	}

	// Source: https://www.baeldung.com/java-hashcode
	public int generateHashCode(String word) {
		int hash = 7;
		// makes sure that it can also read words written in lower case
		char[] letters = word.toLowerCase().toCharArray();
		Arrays.sort(letters);
		for (int i = 0; i < word.length(); i++) {
			hash = hash * 31 + letters[i];
		}
		hash = (hash < 0) ? hash * -1 : hash;
		return hash % 123 + 1;

	}

	public LinkedList<String> get(int index){
		return hashTable[index];
	}
	public String toString(String[] words){
		return words.toString();

	}




	private String[] lookUp(String wordLookup)
	{
		wordLookup = normalize(wordLookup);
        int value = generateHashCode(wordLookup);

		String[] words;

		LinkedList<String> words_list = get(value);
		if (words_list == null)
		{
			System.out.println("we could not find any word.");
			return null;
		}
		else
		{
			int size = words_list.size();
			words = new String[size];

			for(int i = 0; i < size; i++)
				words[i] = words_list.get(i);
		}

		return words;
	}

	private boolean printResult(String[] words) {
		for (String s : words) {
			System.out.println(s);
		}
		return true;
	}



public ArrayList<String > getPermutation(String input){
	ArrayList<String >outPut=new ArrayList<>();
	String[] sorted = lookUp(input);
       for (String s: sorted) {
		if (normalize(input).equals(normalize(s))){

				outPut.add(s);
			}
	}
	return outPut;
}




}