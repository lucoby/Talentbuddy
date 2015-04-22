package com.coby.talentbuddy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javafx.scene.shape.Line;

public class TwitterQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "tweetQuery.txt";
		String[][] ret = readTweets(file);
		String[] users = ret[0];
		String[] queries = ret[1];

		typeahead(users, queries);
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
		for (String q : queries) {
			String line = "";
//			boolean printed = false;
//			for (String un : usernames) {
//				if (un.toLowerCase().startsWith(q.toLowerCase())) {
//					line = line + un;
//					printed = true;
//					break;
//				}
//			}
//			if (!printed) {
//				line = line + "-1";
//			}
			int i = Arrays.binarySearch(usernames, q,
					String.CASE_INSENSITIVE_ORDER);
			if(i < 0) {
				i = -(i + 1);
			}
//			System.out.println(i);
			if (i >= 0 && i < usernames.length && usernames[i].toLowerCase().startsWith(q.toLowerCase())) {
				System.out.println(usernames[i]);
			} else {
				System.out.println(-1);
			}

		}
		System.out.println(usernames.length);
		System.out.println(queries.length);
	}

}
