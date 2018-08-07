import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Brian Powell 012362894
 * CECS 328 Pouye Sedighan
 * Lab_1  
 * 
 * This project compares the search times of Binary and Linear Searches of n-length
 *  arrays after bubblesorting them.
 */


public class Lab_1 {
	private static Scanner s;

	public static boolean linSearch(int[] array, int key) {
		//search all elements in order for key
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key)
				return true;
		}
		return false;
	}

	public static boolean binSearch(int[] array, int key) {

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

	public static int[] bubbleSort(int[] array) {
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

	public static void main(String args[]) {
		
		//instantiates time array to hold running times
		ArrayList<Long> times = new ArrayList<Long>();
		long startTime, binaryAverage = 0, linearAverage = 0;
		
		s = new Scanner(System.in);
		//create random number generator
		Random rand = new Random();
		
		//instantiate all working values
		int n = 0, loop = 30, max =  1000, min = -1000;
		
		System.out.println("Please enter a positive integer: ");
		try
		{
			n = Integer.parseInt(s.next());
		}
		catch (Exception e)
		{
			System.out.println("Invalid input");
		}
		
		//instantiate the array named a of size n
		int[] a = new int[n];
		
		for (int i = 0; i < loop; i++) 
		{
			//fill the array with random numbers between -1000 to 1000
			for (int j = 0; j < a.length; j++) 
			{
				a[j] = rand.nextInt(max + 1 - min) + min;
			}
			
			//sort array using sorting function, I chose BubbleSort
			a = bubbleSort(a);
			//get key from choosing a random index in the array
			int key = a[rand.nextInt(a.length)];

			//runs linear search for the loop and adds its time to time array, even index is Lin Search
			startTime = System.nanoTime();
			linSearch(a, key);
			times.add(System.nanoTime() - startTime);

			//runs binary search for the current loop and adds its time to time array, odd index is Bin Search
			startTime = System.nanoTime();
			binSearch(a, key);
			times.add(System.nanoTime() - startTime);
		}

		//for loop to return the time of each loop takes and average time of all loops
		for (int i = 0; i < times.size(); i++) 
		{
			if (i % 2 == 0)
			{
				System.out.println("Linear Search Time: " + times.get(i) + " nanoseconds.");
				linearAverage += times.get(i);
			}
			else
			{
				System.out.println("Binary Search Time: " + times.get(i) + " nanoseconds.");
				binaryAverage += times.get(i);
			}
		}
		System.out.println("\nLinear Average: " + linearAverage / loop + " nanoseconds." + "\nBinary Average: " + binaryAverage / loop + " nanoseconds.");
	}

}
