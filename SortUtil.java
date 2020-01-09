package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SortUtil {
	private static int insertionSortThreshold = 2;
	public static void setInsertionSortThreshold(int val) {
		insertionSortThreshold = val;
	}
	public static int getInsertionSortThreshold() {
		return insertionSortThreshold;
	}
	
	/**
	 * performs a mergesort on the generic ArrayList given as input
	 * switch over to insertion sort when the size of the sublist to be sorted meets a certain threshold 
	 * 
	 **/

	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> c) {
		Object [] temp = new Object [list.size()];		
		mergeSort(list, temp, 0, list.size() -1, c);
		//return temp;	
	}

	public static <T> void mergeSort(ArrayList<T> list, Object [] temp, int start, int end, Comparator<? super T> c) {
		//if start - end is less than base case, return insertion sort
		//if(end - start == 1)return;
		//
		int middle = (start + end )/2;
		if(start >= end) return;
		if((end - start) < insertionSortThreshold) {
			insertionSort(list, start, end, c);
		}
		
		mergeSort(list,temp, start, middle, c);
	
		mergeSort(list, temp, middle + 1, end, c);

		combine(list, temp, start, middle +1, end, c);
	}
	
public static <T> void combine (ArrayList<T> list, Object [] temp, int firstIndex, int secondIndex, int end, Comparator<? super T> c) {
		
		int startIndex = firstIndex;
		int endArr1 = secondIndex -1;
		int tempPos = firstIndex;

		while (firstIndex <= endArr1 && secondIndex <= end) {
			if(c.compare(list.get(firstIndex),list.get(secondIndex)) < 0 ) {
				temp[tempPos] = list.get(firstIndex);
				firstIndex+= 1;
				tempPos += 1;
				
			}else if (c.compare(list.get(secondIndex), list.get(firstIndex)) < 0) {
				temp[tempPos] = list.get(secondIndex);
				secondIndex += 1;
				tempPos += 1;
				
			}else {
				temp[tempPos] = list.get(firstIndex);
				tempPos += 1;
				firstIndex+=1;
				
				temp[tempPos] =list.get(secondIndex);
				secondIndex += 1;
				tempPos += 1;
				
			}
		}
		if(firstIndex > endArr1) {
			copyRemainder(secondIndex, end, tempPos, temp, list);
			
		}else if (secondIndex > end) {
			copyRemainder(firstIndex, endArr1, tempPos, temp, list);
		}
		
		for(; startIndex <= end; startIndex++) {
			list.set(startIndex, (T) temp[startIndex]);
		}
		
	}

	public static <T> void copyRemainder(int start, int end, int tempPos, Object [] temp, ArrayList<T> list) {

		for (; start <= end; start++) {
			temp[tempPos] = list.get(start);
			tempPos += 1;
		}
	}

	/*****
	 * sorts the input array using an insertion sort and the input Comparator object
	 * 
	 * @param list       - an arraylist to be sorted
	 * @param comparator - the comparator object used ot sort the array
	 * @return a sorted version of the input array.
	 * 
	 *****/
	public static <T> void insertionSort(ArrayList<T> list, int start, int end, Comparator<? super T> c) {
		int i = start + 1;
		for (; i <= end; i++) {
			
			T insertItem = list.get(i);
			int j = i;
			while (j > start && (c.compare(list.get(j - 1), insertItem) > 0)) {
				list.set(j, list.get(j - 1));
				j--;
			}
			list.set(j, insertItem);
		}
	}
	// THIS QUICK SORT HAS THE ORIGINAL ASSIGNMENT SIGNATURE (UNCHANGED)
	/***
	 * 
	 * @param arr  - array which will be sorted
	 * @param comp - comparator
	 */
	public static <T> void quicksort(ArrayList<T> arr, Comparator<? super T> comp) {
		// hard coded 1 becuase test results showed picking the median pivot to be the
		// optimal choice
		sortQuick(arr, 0, arr.size() - 1, comp, 1);
	}

	
	// THIS QUICK SORT WAS USED FOR GATERING TEST DATA,
	/***
	 * 
	 * @param arr  - array which will be sorted
	 * @param comp - comparator
	 * @param pVal - determines the pivot strategy 
	 */
	public static <T> void quicksort1(ArrayList<T> arr, Comparator<? super T> comp, int pVal) {
		sortQuick(arr, 0, arr.size() - 1, comp, pVal);
	}
	

	public static <T> void sortQuick(ArrayList<T> arr, int low, int high, Comparator<? super T> comp, int pVal) {

		if (low < high + 1) { // +1 to make sure we get last element
			int part = partition(arr, low, high, comp, pVal);
			sortQuick(arr, low, part - 1, comp, pVal);
			sortQuick(arr, part + 1, high, comp, pVal);
		}

	}

	
	/**
	 * HELPER 
	 * @param arr - array to use
	 * @param low - low index 
	 * @param high - high index
	 * @param comp - compartor
	 * @param pVal - determining the pivot strategy
	 * @return
	 */
	public static <T> int partition(ArrayList<T> arr, int low, int high, Comparator<? super T> comp, int pVal) {

		// Each of these will run depending on which pVal is called
		// pVal 1, 2, or 3 indicate which partition method will be called
		switch (pVal) {
		case 1:
			swap(arr, low, pivotmedian(low, high));
			break;
		case 2:
			swap(arr, low, randomPivot(low, high));
			break;
		case 3:
			swap(arr, low, middlePivot(arr, low, high, comp));
			break;
		default:
			swap(arr, low, middlePivot(arr, low, high, comp));
			break;
		}

		// swap(arr, low, middlePivot(arr, low, high, comp));
		int part = low + 1; // index of smaller element, always one past pivot
		for (int i = part; i <= high; i++) {
			if (comp.compare(arr.get(i), arr.get(low)) < 0) {
				swap(arr, i, part++);
			}
		}
		swap(arr, low, part - 1);
		return part - 1;
	}
	

	/**
	 * Method in which picking the pivot is based on the medain of the low and high
	 * input
	 * 
	 * @param low  - the low index of the array
	 * @param high - the high index of the array
	 * @return - the index of the pivot in which to partition the array
	 */
	public static int pivotmedian(int low, int high) {
		return ((high - low) + 1) / 2 + low;
	}

	/**
	 * Method in which picking the pivot is based on random number generated
	 * 
	 * @param low  - the low index of the array
	 * @param high - the high index of the array
	 * @return - the index of the pivot in which to partition the array
	 */
	public static int randomPivot(int low, int high) {
		Random rand = new Random();
		return rand.nextInt((high - low) + 1) + low;

	}

	/**
	 * Method in which picking the pivot is based on median of 3 values
	 * 
	 * @param      - arr to pick pivot from
	 * @param      - comp a comparator object to compare generic types
	 * @param low  - the low index of the array
	 * @param high - the high index of the array
	 * @return - the index of the pivot in which to partition the array
	 */
	public static <T> int middlePivot(ArrayList<T> arr, int low, int high, Comparator<? super T> comp) {

		  Random rand = new Random();
	        int first= rand.nextInt((high - low) + 1) + low;
	        int second= rand.nextInt((high - low) + 1) + low;
	        int third= rand.nextInt((high - low) + 1) + low;

		ArrayList<T> tempList = new ArrayList<T>();
		tempList.add(arr.get(first));
		tempList.add(arr.get(second));
		tempList.add(arr.get(third));

		insertionSort(tempList, comp);
		// comparng to index one because thats the middle number
//		if (first.equals(tempList.get(1)))
//			return low;
//		if (middle.equals(tempList.get(1)))
//			return ((high - low) + 1) / 2 + low;
		
	    if(arr.get(first).equals(arr.get(1))) return first;
        if(arr.get(second).equals(arr.get(1))) return second;
        return third;

		//return high;
	}



	public static <T> void insertionSort(ArrayList<T> toBeSorted, Comparator<? super T> comparator) {

		// int length = toBeSorted.size();

		for (int i = toBeSorted.size() - 1; i > 0; i--) {
			if (comparator.compare(toBeSorted.get(i), toBeSorted.get(i - 1)) < 0) {
				swap(toBeSorted, i, i - 1);
			}
		}

		for (int i = 2; i < toBeSorted.size(); i++) {
			T temp = toBeSorted.get(i);
			int j = i;
			while (comparator.compare(temp, toBeSorted.get(j - 1)) < 0) {
				toBeSorted.set(j, toBeSorted.get(j - 1));
				j--;
			}
			toBeSorted.set(j, temp);

		}
	}
	/**
	 * generates and returns an ArrayList of integers 1 to size in ascending order
	 * 
	 **/
	public static ArrayList<Integer> generateBestCase(int size){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i <size; i++)
			list.add(i);
		return list;
	}
	/**
	 * generates and returns an ArrayList of integers 1 to size in randomly ordered
	 * 
	 **/
	public static ArrayList<Integer> generateAverageCase(int size){
		ArrayList<Integer> list = generateBestCase(size);
		//long seed = (long) Math.pow(2, 15);
		Random rand = new Random(0);
		for(int i=0; i < list.size(); i++)
			  swap(list, i, rand.nextInt(list.size()));
		
		return list;
		
	}

//	/**
//	 * 
//	 * @param size - number of items to be generated in array
//	 * @return array with generated values that are shuffled
//	 */
//	public static ArrayList<Integer> generateAverageCase(int size) {
//
//		ArrayList<Integer> arrayToMixup = new ArrayList<Integer>();
//
//		// create the array
//		for (int i = 1; i <= size; i++) {
//			arrayToMixup.add(i);
//		}
//
//		// shuffle the array
//		Collections.shuffle(arrayToMixup);
//		return arrayToMixup;
//	}
	
//	/**
//	 * generates and returns an ArrayList of integers 1 to size in decending ordered
//	 * 
//	 **/
//	public static ArrayList<Integer> generateWorstCase(int size){
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		for(int i = size -1; i >= 0; i--)
//			list.add(i);
//		return list;
//		
//	}
	
	public static ArrayList<Integer> generateWorstCase(int size) {

		ArrayList<Integer> worstCaseArray = new ArrayList<Integer>();

		// create the array
		for (int i = size; i > 0; i--) {
			worstCaseArray.add(i);
		}
		return worstCaseArray;

	}
	/**
	 * prints the ArrayList
	 * @param <T>
	 * 
	 **/
	public static <T> void print(ArrayList<T> list){
		for(int i = 0; i < list.size(); i++)
			System.out.print(list.get(i) + " ");
	}
	
	public static <T> ArrayList<T> swap(ArrayList<T> list, int firstIndex, int secondIndex) {
		T val = list.get(firstIndex);
		list.set(firstIndex, list.get(secondIndex));
		list.set(secondIndex, val);
		return list;
	}
	
}
