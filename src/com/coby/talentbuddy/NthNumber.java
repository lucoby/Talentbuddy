package com.coby.talentbuddy;
import java.util.Arrays;

public class NthNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] v = {7,9,10,1,2,3,4,5,6,8};
//		Integer temp = v[0];
//		v[0] = v[1];
//		v[1] = temp;
//		for (int i: v) {
//			System.out.println(i);
//		}
		nth_number(v,3);
		Integer[] w = {7,9,10,1,2,3,4,5,6,8};
		nth_number_r(w, 3);
	}
	public static int quickSearch(Integer[] v, Integer n, Integer lo, Integer hi) {
//		System.out.println(n + " " + lo + " " + hi);
//		for (int i: v) {
//			System.out.println(i);
//		}
		if (lo == hi) {
			return v[lo];
		}
		int pivot = (123456 % (hi - lo)) + lo; // Totally random number based on rolling a dice
//		System.out.println("pivotInd " + pivot + ", pivot " + v[pivot]);
		int temp = v[pivot];
		v[pivot] = v[hi];
		v[hi] = temp;
		int swapInd = lo;
		for (int i = lo; i < hi; i++) {
			if (v[i] > v[hi]) {
				temp = v[i];
				v[i] = v[swapInd];
				v[swapInd] = temp;
				swapInd++;
			}
		}
		temp = v[swapInd];
		v[swapInd] = v[hi];
		v[hi] = temp;
		if(n == swapInd) {
			return v[swapInd];
		} else if (n > swapInd) {
			return quickSearch(v, n, swapInd + 1, hi);
		} else if (n < swapInd) {
			return quickSearch(v, n, lo, swapInd - 1);
		}
		return -1;
	}

    public static void nth_number(Integer[] v, Integer n) {
        // Write your code here
        // To print results to the standard output please use System.out.println
        // Example: System.out.println("Hello world!");
    	System.out.println(quickSearch(v, n - 1, 0, v.length - 1));
    }
    
    
    public static void nth_number_r(Integer[] v, Integer n) {
        // Write your code here
        // To print results to the standard output please use System.out.println
        // Example: System.out.println("Hello world!");
        int[] largest = new int[n];
        int largestPtr=0;
        for(int i=0; i<n; i++){
           largest[i]=v[i];
           //System.out.println(v[i]);
        }
        Arrays.sort(largest);
//        for (int i: largest) {
//        	System.out.println(i);
//        }
        largestPtr=n-1;
        for(int j:v){
            if(j>largest[largestPtr]){
                largestPtr= (largestPtr+1)%n;
                largest[largestPtr]=j;
               
               
            }
        }
        
        int index = (largestPtr+1)%n;
        System.out.println(largest[index]);
    }

}
