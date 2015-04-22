package com.coby.talentbuddy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


class Trie {
	String data;
	HashMap<String,Trie> children;
	
	public Trie(String in) {
		this.data  = in.substring(0, 1);
		children = new HashMap<String,Trie>();
	}
	
	public boolean isWord(String in) {
		if(in.isEmpty()) {
			if (children.containsKey("/")) {
				return true;
			} else {
				return false;
			}
		} else {
			if (children.containsKey(in.substring(0,1))) {
				return children.get(in.substring(0, 1)).isWord(in.substring(1));
			} else {
				return false;
			}
		}
	}
	
	public boolean isPrefix(String in) {
		if(in.isEmpty()) {
			return true;
		} else {
			if (children.containsKey(in.substring(0,1))) {
				return children.get(in.substring(0, 1)).isPrefix(in.substring(1));
			} else {
				return false;
			}
		}
	}
	
	public String getFirst(String pre) {
		if(pre.isEmpty()) {
			return "foo";
		} else {
			if (children.containsKey(pre.substring(0,1))) {
				return children.get(pre.substring(0, 1)).getFirst(pre.substring(1));
			} else {
				return "-1";
			}
		}
	}
	
	public ArrayList<String> getAll(String pre) {
		return getAllHelper(pre, "");
	}
	
	private ArrayList<String> getAllHelper(String pre, String curr) {
		ArrayList<String> ret = new ArrayList<String>();
		if(pre.isEmpty()) {
			
		} else {
			if (children.containsKey(pre.substring(0,1))) {
				return children.get(pre.substring(0,1)).getAllHelper(pre.substring(0,1),curr + pre.substring(0,1));
			} else {
				return ret;
			}
		}
		return ret;
	}
	
	public void add(String in) {
		if (in.isEmpty()) {
			in = "/";
		}
//		System.out.println(in);
		if(!children.containsKey(in.substring(0, 1))) {
			Trie child = new Trie(in.substring(0,1));
			children.put(in.substring(0,1), child);
		}
		if(in.equals("/")) {
			return;
		} else {
			children.get(in.substring(0,1)).add(in.substring(1));
		}
	}
	
}

public class TrieTest {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String file = "tweetQuery.txt";
//		String[][] ret = readTweets(file);
//		String[] users = ret[0];
//		String[] queries = ret[1];
//		
//		Trie dictionary = new Trie("^");
//		for (String u:users) {
//			dictionary.add(u);
//		}
		Trie dictionary = new Trie("^");
		ArrayList<String> wordList = readBook("3. A Storm of Swords_djvu.txt");
//		for (String s:wordList) {
//			dictionary.add(s);
//		}
		
		Collections.sort(wordList, String.CASE_INSENSITIVE_ORDER);
		HashMap<String, String> hsh = new HashMap<String, String>();
		int wordLength = 0;
		for (String s:wordList) {
			if(!hsh.containsKey(s)) {
				hsh.put(s, s);
				System.out.println(s);
			}

		}
		for (String s:hsh.keySet()) {
			wordLength += s.length();
		}
		float fwordLength = (float) wordLength / hsh.size();
		System.out.println(hsh.size());
		System.out.println(wordList.size());
		System.out.println(fwordLength);
	}

	public static ArrayList<String>  readBook(String file) {
		ArrayList<String> wordList = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {
				if (line.trim() != null && line.trim().length() > 0) {
					String[] t = line.split("[\\s\\.,\\?!;:\\-\"]");
					for (String s:t){
						if(!s.isEmpty()) {
							System.out.println(s);
							wordList.add(s.toLowerCase());
						}
						
					}
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println(wordList.size());
		return wordList;
	}
	
	public static String[][] readTweets(String file) {
		String[] users = null;
		String[] queries = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str = in.readLine();
			String[] strs = str.split(", ");
			users = new String[strs.length];
			for (int i = 0; i < strs.length; i++) {
				users[i] = strs[i].replaceAll("\"", "");
			}
			str = in.readLine();
			strs = str.split(", ");
			queries = new String[strs.length];
			for (int i = 0; i < strs.length; i++) {
				queries[i] = strs[i].replaceAll("\"", "");
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		String[][] ret = { users, queries };
		return ret;
	}
    public static void typeahead(String[] usernames, String[] queries) {
        // Write your code here
        // To print results to the standard output please use System.out.println
        // Example: System.out.println("Hello world!");

        Arrays.sort(usernames, String.CASE_INSENSITIVE_ORDER);
        for (String q:queries) {
            boolean printed = false;
            for (String un: usernames) {
                if (un.toLowerCase().startsWith(q.toLowerCase())) {
                    System.out.println(un);
                    printed = true;
                    break;
                }
            }
            if (!printed) {
                System.out.println("-1");
            }
        }
    }

}
