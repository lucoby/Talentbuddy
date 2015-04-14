package com.coby.talentbuddy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

class TweetComparator implements Comparator<Integer[]> {
	@Override
	public int compare(Integer[] x, Integer[] y) {
		if (x[0] < y[0]) {
			return 1;
		}
		if (x[0] > y[0]) {
			return -1;
		}
		return 0;
	}
}

public class TweetsPerSecond {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String infile = "tps2.txt";
		Integer[] tps = readTweets(infile);
		long startTime = System.nanoTime();
		for(int i = 0; i < 10; i++) tweets_per_second(tps, 2064);
		long endTime = System.nanoTime();
		long pq = (endTime - startTime)/10000000;
		
		startTime = System.nanoTime();
		for(int i = 0; i < 10; i++) tweets_per_second2(tps, 2064);
		endTime = System.nanoTime();
		long arr = (endTime - startTime)/10000000;
		
		
//		startTime = System.nanoTime();
//		tweets_per_second3(tps, 28064);
//		endTime = System.nanoTime();
//		long mh = startTime - endTime;
		
		
		System.out.println("PQ Total execution time: " + pq );
		System.out.println("Arr Total execution time: " + arr );
		
	}

	private static Integer[] readTweets(String infile)
			throws FileNotFoundException, IOException {
		BufferedReader in = new BufferedReader(new FileReader(infile));
		String str = in.readLine();
		String[] strs = str.split(", ");
		Integer[] nums = new Integer[strs.length];
		for (int i = 0; i < strs.length; i++) {
			try {
				nums[i] = Integer.parseInt(strs[i]);
			} catch (NumberFormatException nfe) {};
		}
		in.close();
		return nums;
	}

	public static void tweets_per_second(Integer[] tps, Integer k) {
		Comparator<Integer[]> c = new TweetComparator();
		PriorityQueue<Integer[]> tweets= new PriorityQueue<Integer[]>(2*k, c);
		for (int i = 0; i < tps.length; i++) {
			if (tweets.peek() != null && tps[i] > tweets.peek()[0]) {
				tweets = new PriorityQueue<Integer[]>(k, c);
			}
			Integer[] arr = {tps[i], i};
			tweets.add(arr);
			while(i - tweets.peek()[1] >= k) {
				tweets.poll();
			}
			System.out.println(tweets.peek()[0]);
		}
	}
	
    public static void tweets_per_second2(Integer[] tps, Integer k) {

        int max = tps[0];
        System.out.println(max);
        for(int i=1; i < k; i++){
            if(tps[i]>max)
                max = tps[i];
            System.out.println(max);
        }
        for(int i=k; i<tps.length; i++){
            if(tps[i]>max){
                max = tps[i];
                System.out.println(max);
            }else{
                if(tps[i-k]==max){
                    max = tps[i-k+1];
                    for(int j=i-k+2; j <= i; j++){
                        if(tps[j]>max)
                            max=tps[j];
                    }
                    System.out.println(max);
                }else{
                    System.out.println(max);
                }        
            }
        }
        
    }
}
