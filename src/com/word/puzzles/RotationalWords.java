package com.word.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RotationalWords {

//	public static final int DESIRED_LENGTH = 4;
	public static final int MIN_LENGTH = 3;
	
	public static void main(String[] args) {
		
		List<String> words = read("raw/webster_words.txt");
		List<String> words2 = copy(words);
		
		HashMap<Character, Character> characterMap = createCharacterMap();
		
		List<String> rotationalWords = new ArrayList<String>();
		
		Collections.shuffle(words);
		Collections.shuffle(words);
		Collections.shuffle(words);
		Collections.shuffle(words);
		Collections.shuffle(words2);
		Collections.shuffle(words2);
		Collections.shuffle(words2);
		Collections.shuffle(words2);
		
		
		for(String word : words) {
			
			if(word.length() < MIN_LENGTH) {
				continue;
			}
			
			for(String secondWord : words2) {
				
				if(secondWord.length() < MIN_LENGTH) {
					continue;
				}
				
				if(secondWord.equals(word)) {
					continue;
				}
				
				if(word.length() + secondWord.length() < 6) {
					continue;
				}
				
				String wordCandidate = word.toLowerCase() + secondWord.toLowerCase();
				if(isWordRotational(wordCandidate, characterMap)) {
					System.out.println(word.toLowerCase() + " " + secondWord.toLowerCase());
					rotationalWords.add(wordCandidate);
				}
				
				String wordCandidate2 = word.toLowerCase() + "s" + secondWord.toLowerCase();
				if(isWordRotational(wordCandidate2, characterMap)) {
					System.out.println(word.toLowerCase() + "s " + secondWord.toLowerCase());
					rotationalWords.add(wordCandidate2);
				}
				
				String wordCandidate3 = word.toLowerCase() + secondWord.toLowerCase() + "s";
				if(isWordRotational(wordCandidate3, characterMap)) {
					System.out.println(word.toLowerCase() + " " + secondWord.toLowerCase() + "s");
					rotationalWords.add(wordCandidate3);
				}
			}
		}
		int count = 1;
		for(String word : rotationalWords) {
			System.out.println(count++ + "\t" + word);
		}
		

	}
	
	public static boolean isWordRotational(String word, HashMap<Character, Character> characterMap) {
		boolean isWordRotational = true;
		for(int i=0;i<word.length()/2;i++) {
			char a = word.charAt(i);
			char z = word.charAt(word.length()-1-i);
			if(characterMap.get(a) == null || z != characterMap.get(a)) {
				isWordRotational = false;
				break;
			}
		}
		return isWordRotational;
	}
	
	public static HashMap<Character, Character> createCharacterMap(){
		HashMap<Character, Character> characterMap = new HashMap<Character, Character>();
		characterMap.put('a', 'e');
		characterMap.put('b', 'q');
		characterMap.put('d', 'p');
		characterMap.put('e', 'a');
		characterMap.put('l', 'l');
		characterMap.put('m', 'w');
		characterMap.put('n', 'u');
		characterMap.put('o', 'o');
		characterMap.put('p', 'd');
		characterMap.put('q', 'b');
		characterMap.put('s', 's');
		characterMap.put('u', 'n');
		characterMap.put('w', 'm');
		characterMap.put('x', 'x');
		characterMap.put('z', 'z');
		return characterMap;
	}
	
	public static List<String> copy(List<String> data){
		List<String> copy = new ArrayList<String>();
		for(String item : data) {
			copy.add(item);
		}
		return copy;
	}
	public static List<String> read(String filename){
		
		List<String> data = new ArrayList<String>();
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File(filename)));
			
			String line;
			
			while( (line = buffer.readLine()) != null ) {
				data.add(line);
			}
			
			buffer.close();
		}catch(IOException e) {
			
		}
		return data;
		
	}

}
