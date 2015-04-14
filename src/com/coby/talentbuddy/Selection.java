package com.coby.talentbuddy;

public class Selection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] v = {4,5,7,3,8,9,1,2,6,0};
		select_numbers(v,5);
	}

	public static void quickSelection(Integer[] v, Integer n, Integer lo, Integer hi) {
		System.out.println("n "+ n + " lo " + lo + " hi " + hi);
		for (int i: v) {
			System.out.println(i);
		}
		if (lo >= hi) {
			return;
		}
//		int pivot = (123456 % (hi - lo)) + lo; // Totally random number based on rolling a dice
		int pivot = lo;
		System.out.println("pivotInd " + pivot + ", pivot " + v[pivot]);
		int temp = v[pivot];
		v[pivot] = v[hi];
		v[hi] = temp;
		int swapInd = lo;
		for (int i = lo; i < hi; i++) {
			if (v[i] < v[hi]) {
				temp = v[i];
				v[i] = v[swapInd];
				v[swapInd] = temp;
				swapInd++;
			}
		}
		temp = v[swapInd];
		v[swapInd] = v[hi];
		v[hi] = temp;
		if (n > swapInd) {
			quickSelection(v, n, swapInd + 1, hi);
            quickSelection(v, n, lo, swapInd - 1);
		} else if (n <= swapInd) {
			quickSelection(v, n, lo, swapInd - 1);
		}
	}
    
    public static void select_numbers(Integer[] v, Integer k) {
        // Write your code here
        // To print results to the standard output please use System.out.println
        // Example: System.out.println("Hello world!");
        quickSelection(v,k - 1,0,v.length - 1);
        for(int i = 0; i < k; i++) {
            System.out.print(v[i] + " ");
        }
    }
}
