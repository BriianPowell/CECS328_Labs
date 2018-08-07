import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Brian Powell 012362894
 * CECS 328 Pouye Sedighan
 * Lab_4
 *
 * This project compares the sorting times between Heap sort, Quick sort, and Insertion Sort. 
 *  Quick sort utilizes the partition function to break the arrays into two halves, 
 *  then decides the correct spot of each integer in each partition.
 *  Heap sort utilizes both the build_Maxheap functions and max_Heapify to sort and 
 *  order the heap.
 *
 */

public class Lab_4
{
	private static int N;
	public static Scanner s;

	public static void heap_Sort(int array[]) 
	{
		//start from max array length and work backwards
		for (int i = N; i > 0; i--) {
			swap(array, 0, i);
			N--;
			max_Heapify(array, 0);
		}
	}

	public static void build_MaxHeap(int array[]) 
	{
		//build max heap backwards
		N = array.length - 1;
		for (int i = N; i >= 0; i--)
			max_Heapify(array, i);
	}

	public static void max_Heapify(int array[], int i) 
	{
		int max = i;
		int left = 2 * i;
		int right = 2 * i + 1;

		
		if (left <= N && array[left] > array[i])
			max = left;
		
		if (right <= N && array[right] > array[max])
			max = right;

		if (max != i) {
			swap(array, i, max);
			max_Heapify(array, max);
		}
	}

	//Function to swap two elements in an array
	public static void swap(int array[], int i, int j) 
	{
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	static int partition(int arr[], int left, int right)
	{
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
	
	static void quick_Sort(int arr[], int left, int right) 
	{
		int index = partition(arr, left, right);
		if (left < index - 1)
			quick_Sort(arr, left, index - 1);
		if (index < right)
			quick_Sort(arr, index, right);
	}
	
	public static int[] selection_Sort(int[] list) 
	{
		int[] listCopy = list;
		for (int i = 0; i < listCopy.length - 1; i++) {
			// Find the index of the minimum value
			int minPos = i;
			for (int j = i + 1; j < listCopy.length; j++) {
				if (listCopy[j] < listCopy[minPos]) {
					minPos = j;
				}
			}
			swap(listCopy, minPos, i);
		}
		return listCopy;
	}
	
	
	public static void main(String[] args) {
		
		//create array to hold times, holds heap sort times
		ArrayList<Long> times = new ArrayList<Long>();
		//holds quick sort and selection sort times
		ArrayList<Long> times2 = new ArrayList<Long>();
		long startTime, heapSortAverage = 0, quickSortAverage = 0, selectionSortAverage = 0;

		s = new Scanner(System.in);
		
		//create random number generator
		Random rand = new Random();
		
		//instantiate working variables
		int n = 0, max = 7000, min = -7000; 
		
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

		for(int i = 0; i < 100; i++)
		{
			//filling the array with values
			for (int j = 0; j < a.length; j++) 
			{
				a[i] = (rand.nextInt(max + 1 - min) + min);
			}
			
			int[] quickSortA = a, selectionSortA = quickSortA;
					
			build_MaxHeap(a);
			
			//Does Heap sort then calculates the time, no index needed since only working with heap_sort
			startTime = System.nanoTime();
			heap_Sort(a);
			times.add(System.nanoTime() - startTime);
			
			//Does Quick sort then calculates the time, odd index is Quick Sort
			startTime = System.nanoTime();
			quick_Sort(quickSortA, 0, quickSortA.length-1);
			times2.add(System.nanoTime()- startTime);
			
			//Does Selection sort then calculates the time, even index is Insertion Sort
			startTime = System.nanoTime();
			selection_Sort(selectionSortA);
			times2.add(System.nanoTime()- startTime);
		}
		
		for(int i = 0; i < times.size(); i++)
		{
			System.out.println("Heap Sort Time:\t\t" + times.get(i));
			heapSortAverage += times.get(i);
		}
		
		for(int i = 0; i < times2.size(); i++)
		{
			if (i % 2 == 0) 
			{
				System.out.println("InsertionSort Time:\t\t" + times2.get(i));
				quickSortAverage += times2.get(i);
			}
		}
		
		for(int i = 0; i < times2.size(); i++)
		{
			if(i % 2 == 1)
			{
				System.out.println("QuickSort Time:\t\t" + times2.get(i));
				selectionSortAverage += times2.get(i);
			}
		}
		
		System.out.println("\nHeap Sort Average: " + heapSortAverage / 100 + " nanoseconds." + "\nInsertion Sort Average: " + selectionSortAverage / 100 + " nanoseconds." + "\nQuick Sort Average: " + quickSortAverage / 100 + " nanoseconds.");
	}
}

/* n = 100,000
 * 
 * Heap Sort Running time: 		351,300 nanoseconds
 * Insertion Sort Running time: 1,531,071,713 nanoseconds
 * Quick Sort Running time:		2,848,680 nanoseconds
 *  
 */



