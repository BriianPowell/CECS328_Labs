import java.util.Random;
import java.util.Scanner;

/*
 * Brian Powell 012362894
 * CECS 328 Pouye Sedighan
 * Lab_2
 * 
 * This project forces worst case scenarios for both Linear Search and Binary Search after they've
 *  been bubblesort. ACtual running times of worse case scenarios have been calculated at the bottom.
 */


public class Lab_2 {
	private static Scanner s;

	public static boolean linSearch(int[] array, int key)
	{
		//search all elements in order for key
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key)
				return true;
		}
		return false;
	}

	public static boolean binSearch(int[] array, int key) 
	{
		int begin = 0;
		int end = array.length - 1;

		while (end >= begin) {
			int midpoint = (begin + end) / 2;
			//cut in half and compare to key
			if (array[midpoint] == key)
				return true;
			//chose first half of interval
			if (array[midpoint] < key)
				begin = midpoint + 1;
			//chose second half of interval
			if (array[midpoint] > key)
				end = midpoint - 1;
		}
		return false;
	}

	public static int[] bubbleSort(int[] array) 
	{
		int n = array.length;
		int temp = Integer.MAX_VALUE;

		//loop through the whole array
		X: for (int i = 0; i < n; i++) {
			//inner loop to compare to values
			for (int j = 1; j < (n - i); j++) {
				//compare previous to next value
				if (array[j - 1] > array[j]) {
					temp = array[j - 1]; //assign greater value to temp
					array[j - 1] = array[j]; //shift j one spot to correct spot
					array[j] = temp; //assign greater value to j
				}
				if (j == n && temp == Integer.MAX_VALUE)
					break X;
			}
		}
		return array;
	}
	
	public static void main(String args[]) 
	{
		//instantiate variables to hold time
		long startTime, linearTime, binaryTime;
		
		s = new Scanner(System.in);
		
		//create random generator
		Random rand = new Random();
		
		//instantiate working values
		int n = -1, min = -5000, max = 5000;
		
		System.out.println("Please enter a positive integer: ");
		
		try
		{
			n = Integer.parseInt(s.next());
		} 
		catch (Exception e) 
		{
			System.out.println("Invalid input");
		}
		
		//instantiate array a of size n
		int[] a = new int[n];

		//fill array of size n with random numbers from -5000 to 5000
		for (int j = 0; j < n; j++) {
			a[j] = rand.nextInt(max + 1 - min) + min;
		}
		
		//forcing worst case scenario after bubblesort
		//making last element 7000
		a[a.length - 1] = 7000;
		//making the key to 7000
		int key = 7000;
		//sorting array using Bubble sort
		a = bubbleSort(a);

		//runs linSearch and figures out worst case scenario time
		startTime = System.nanoTime();
		linSearch(a, key);
		linearTime = System.nanoTime() - startTime;

		//runs binSearch and figures out the worst case scenario time
		startTime = System.nanoTime(); 
		binSearch(a, key);
		binaryTime = System.nanoTime() - startTime;
		
		//Print out linear and binary times
		System.out.println("Linear Time: " + linearTime + " nanoseconds." + "\nBinaryTime: " + binaryTime + " nanoseconds.");
		System.out.println("Single Step Linear Time: " + linearTime / n + " nanoseconds." + "\nSingle Step Binary Time: " + (binaryTime/ (Math.log10(n)/Math.log10(2))) + " nanoseconds.");
	
		/*
		 Average running time of Linear Search is O(n), is also worst case
		 Expected: O(100,000) Actual: 326,728 nanoseconds / n
		 Expected: O(10,000,000) Actual: N/A
		 
		 Average running time of Binary Search is O(Log n) is also worse case
		 Expected: O(Log 100,000) Actual: 25,639 nanoseconds / log10(100000)/Log10(2)
		 Expected: O(Log 10,000,000) Actual:N/A 
		 */
		
	}
}