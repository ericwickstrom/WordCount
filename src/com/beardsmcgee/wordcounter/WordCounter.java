package com.beardsmcgee.wordcounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Set;

/*
 * Reads in a file, counts words, prints a report of the 
 * total occurrences of each word.
 */


public class WordCounter {
	public static final String TESTFILE = "/home/beardsmcgee/workspace/WordCounter/src/com/beardsmcgee/wordcounter/big.txt";
	public static HashMap <String, Integer> wordMap;
	
	public static final void count(String fileName){
		wordMap = new HashMap<String,Integer>();

		try {
			// buffered reader to read text file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = null;
			// BufferedReader goes through each line, splits string in to array.
			// Then it checks if it is in the HashMap.  If it is, the count is updated.
			// If not, the key is added.
			while((line = br.readLine()) != null){
				// words are split on a space.
				String[] words = line.split(" ");
				for(String word : words){
					// store words as lowercase to reduce duplicates
					word = word.toLowerCase();
					if(wordMap.containsKey(word)){
						int count = wordMap.get(word);
						wordMap.put(word, ++count);
					} else {
						wordMap.put(word, 1);
					}
				}
			}
			Set<String> keys = wordMap.keySet();
			for(String key : keys){
				int count = wordMap.get(key);
				System.out.println(key + ": " + String.valueOf(count));
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static final void main(String[] args){
		String fileName;
		if(args.length > 0){
			fileName = args[0];
		} else {
			fileName = TESTFILE;
		}
		count(fileName);
		
	}
}
