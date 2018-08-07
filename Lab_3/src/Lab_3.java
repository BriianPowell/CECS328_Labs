import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Brian Powell 012362894
 * CECS 328 Pouye Sedighan
 * Lab_3
 * 
 * This project compares the sorting times between Quick sort and Insertion Sort. 
 *  Quick sort utilizes the partition function to break the arrays into two halves, 
 *  then decides the correct spot of each integer in each partition.
 */

public class Lab_3{
	private static Scanner s;

	public static int[] insertion_Sort(int[] array) {
		//iterate one item at a time
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			//compare key to next value
			while (j >= 0 && key < array[j]) {
				//move focus one spot and assign value
				array[j + 1] = j;
				j--;
			}
			//assign next integer as next key
			array[j + 1] = key;
		}
		return array;
	}

	static int partition(int arr[], int left, int right) {
		int i = left, j = right;
		int tmp;
		int pivot = arr[(left + right) / 2];

		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) { //swapping places between left and right
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		;

		return i;
	}

	static void quick_Sort(int arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quick_Sort(arr, left, index - 1);
		if (index < right)
			quick_Sort(arr, index, right);
	}

	public static void main(String args[]) {
		
		//creating arrays to hold times
		ArrayList<Long> times = new ArrayList<Long>();
		long startTime, quickSortAverage = 0, insertionSortAverage = 0;
		
		s = new Scanner(System.in);
		
		//create random number generator
		Random rand = new Random();
		
		//instantiate working variables
		int n = 0, max = 7000, min = -7000; 
		
		System.out.println("Please enter a positive integer:");

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
		
		for (int j = 0; j < 100; j++) 
		{
			//insert random variables into the array
			for (int i = 0; i < a.length; i++) 
			{
				a[i] = (rand.nextInt(max + 1 - min) + min);
			}
			
			int[] insertionArray = a;
			
			//Does quick sort then calculates the time, odd index is Quick Sort
			startTime = System.nanoTime();
			quick_Sort(a, 0, a.length - 1);
			times.add(System.nanoTime() - startTime);
			
			//Does insertion sort then calculates the time, even index is Insertion Sort
			startTime = System.nanoTime();
			insertionArray = insertion_Sort(insertionArray);
			times.add(System.nanoTime() - startTime);
		}

		//prints out times of each repetition and and calculates average
		for (int i = 0; i < times.size(); i++) 
		{
			if (i % 2 == 0) 
			{
				System.out.println(i + "." + " InsertionSort Time:\t" + times.get(i));
				quickSortAverage += times.get(i);
			}
			else
			{
				System.out.println(i + "." + " QuickSort Time:\t\t" + times.get(i));
				insertionSortAverage += times.get(i);
			}
		}
		System.out.println("\nQuick Sort Average: " + quickSortAverage / 100 + " nanoseconds." + "\nInsertion Sort Average: " + insertionSortAverage / 100 + " nanoseconds.");		
	}

}
