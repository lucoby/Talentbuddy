package com.coby.talentbuddy;

import java.util.*;
//import java.util.ArrayList;

public class TupleSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer arr[] = {3,2,1,4,5,7,6,9,7,8};
		tuple_sum(arr,26);
	}
    public static void tuple_sum(Integer[] a, Integer s) {
        // Write your code here
        // To print results to the standard output please use System.out.println
        // Example: System.out.println("Hello world!");
    	Hashtable<Integer,ArrayList<Integer>> pairSums = new Hashtable<Integer,ArrayList<Integer>>();
        for(int i = 0; i < a.length - 1; i++) {
            for(int j = i+1; j < a.length; j++) {
                if(pairSums.containsKey(a[i] + a[j])) {
//                    if (!(pairSums.get(a[i] + a[j]).contains(i) || pairSums.get(a[i] + a[j]).contains(j))) {
                	if(true) {
	                	pairSums.get(a[i] + a[j]).add(i);
	                    pairSums.get(a[i] + a[j]).add(j);
                    }
                } else {
                    pairSums.put((a[i] + a[j]), new ArrayList<Integer>());
                    pairSums.get(a[i] + a[j]).add(i);
                    pairSums.get(a[i] + a[j]).add(j);
                }
            }
        }
        Enumeration<Integer> en = pairSums.keys();
        
        while (en.hasMoreElements()) {
        	Integer k  = en.nextElement();
        	System.out.println(k + ":" + pairSums.get(k));
        }
        
        en = pairSums.keys();
//        ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> solution = null;
        while (en.hasMoreElements()) {
            Integer k = en.nextElement();
            ArrayList<Integer> v = pairSums.get(k);
            if(s%2 == 0 && s/2 == k) {
            	if(solution == null) {
            		solution = v;
            		Collections.sort(solution);
            	} else if (lexCompare(solution,v) > 1){
            		solution = v;
            	}
            	break;
            } else if (pairSums.containsKey(s - k)) {
            	ArrayList<Integer> temp = new ArrayList<Integer>();
            	temp.add(v.get(0));
            	temp.add(v.get(1));
            	temp.add(pairSums.get(s-k).get(0));
            	temp.add(pairSums.get(s-k).get(1));
            	Collections.sort(temp);
            	if(solution == null) {
            		solution = temp;
            	} else if (lexCompare(solution,v) > 1){
            		solution = v;
            	}
            }
        }
        for(Integer i:solution) {
        	System.out.print(i + " ");
        }
    }
    public static int lexCompare(ArrayList<Integer> a, ArrayList<Integer> b) {
    	for(int i = 0; i < a.size(); i++) {
    		if (a.get(i) < b.get(i)) {
    			return -1;
    		} else if (b.get(i) < a.get(i)) {
    			return 1;
    		}
    	}
    	return 0;
    }
}
