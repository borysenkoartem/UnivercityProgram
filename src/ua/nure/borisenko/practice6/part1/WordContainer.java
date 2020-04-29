package ua.nure.borisenko.practice6.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class WordContainer {
	private ArrayList<Word> wordCont;

	public WordContainer() {
        wordCont = new ArrayList<>();
	}

	public void addWord(String w) {
	    boolean flag = true;
	    for(Word w1:wordCont){
	        if (w.equals(w1.getWord())){
	            w1.riseFrequent();
	            flag = false;
            }
        }
        if (flag){
            wordCont.add(makeWord(w));
        }
	}

	private static Word makeWord(String str) {
		return new Word(str);
	}

	private void show() {
		for (Word w : wordCont) {
			System.out.println(w.getWord() + " : " + w.getFreq());
		}
	}

	private void sort() {
		Comparator<Word> comp = (Word word1, Word word2) -> {
			int dif = word2.getFreq() - word1.getFreq();
			if (dif == 0) {
				return word1.getWord().compareTo(word2.getWord());
			}
			return dif;
		};
        wordCont.sort(comp);
	}

	public static void main(String[] args) {
		WordContainer wc = new WordContainer();
		boolean run = true;
		Scanner sc = new Scanner(System.in);

		while (run && sc.hasNextLine()) {
			String str = sc.nextLine();
			String[] array = str.split("\\s+");
			for (String str1 : array) {
				if ("stop".equalsIgnoreCase(str1)) {
					run = false;
					break;
				}
				wc.addWord(str1);
			}
		}
		sc.close();
        wc.sort();
        wc.show();


	}

}