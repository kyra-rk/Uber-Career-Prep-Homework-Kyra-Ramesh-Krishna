// technique: sliding window
// time complexity: O(k*n)
// space complexity: O(n)

import java.util.Arrays;

public class MaxMeanSubarray {

	public static void main (String arg[]) {
		System.out.println("--- Testing MaxMeanSubArray ---");
		int[] test0 = null;
		System.out.println("Input Array: " + Arrays.toString(test0));
		System.out.println("Input k: " + 0);
		System.out.println("Expe![](../../../../../../../var/folders/w7/vg27hlsx0_gc6362mpsz2lx40000gn/T/TemporaryItems/NSIRD_screencaptureui_KlosQS/Screen Shot 2023-03-08 at 10.53.12 PM.png)cted Output is: " + 0.0);
		System.out.println("Actual Output: " + maxMeanSubArray(test0, 0));
		System.out.println("--------------");

		int[] test1 = {};
		System.out.println("Input Array: " + Arrays.toString(test1));
		System.out.println("Input k: " + 0);
		System.out.println("Expected Output is: " + 0.0);
		System.out.println("Actual Output: " + maxMeanSubArray(test1, 0));
		System.out.println("--------------");

		int[] test2 = {4, 5, -3, 2, 6, 1};
		int k = 2;
		System.out.println("Input Array: " + Arrays.toString(test2));
		System.out.println("Input k: " + k);
		System.out.println("Expected Output is: " + 4.5);
		System.out.println("Actual Output: " + maxMeanSubArray(test2, k));
		System.out.println("--------------");

		int[] test3 = {4, 5, -3, 2, 6, 1};
		k = 3;
		System.out.println("Input Array: " + Arrays.toString(test3));
		System.out.println("Input k: " + k);
		System.out.println("Expected Output is: " + 3.0);
		System.out.println("Actual Output: " + maxMeanSubArray(test3, k));
		System.out.println("--------------");

		int[] test4 = {1, 1, 1, 1, -1, -1, 2, -1, -1};
		k = 3;
		System.out.println("Input Array: " + Arrays.toString(test4));
		System.out.println("Input k: " + k);
		System.out.println("Expected Output is: " + 1.0);
		System.out.println("Actual Output: " + maxMeanSubArray(test4, k));
		System.out.println("--------------");

		int[] test5 = {1, 1, 1, 1, -1, -1, 2, -1, -1, 6};
		k = 5;
		System.out.println("Input Array: " + Arrays.toString(test5));
		System.out.println("Input k: " + k);
		System.out.println("Expected Output is: " + 1.0);
		System.out.println("Actual Output: " + maxMeanSubArray(test5, k));
		System.out.println("--------------");
	}

	/* 	Given an array of integers and an integer, k,
		find the maximum mean of a subarray of size k.
	 */
	public static double maxMeanSubArray(int[] arr, int k) {
		// if k is out of the bounds of the array, then return 0
		if (arr == null) {
			return 0;
		}
		if (k > arr.length) {
			return 0;
		}
		int startIx = 0;
		int endIx = k - 1;
		double maxMean = 0;
		double currTotal;
		while (endIx < arr.length) {
			currTotal = 0;
			for (int i = startIx; i <= endIx; i++) {
				currTotal += arr[i];
			}

			if ((currTotal/k) > maxMean) {
				maxMean = currTotal/k;
			}
			startIx++;
			endIx++;
		}
		return maxMean;
	}

}

// time taken to complete: about 40 min (I finished right as the timer went off)
// this included figuring out my general work organization (eg. working w/ vim + intelij)
