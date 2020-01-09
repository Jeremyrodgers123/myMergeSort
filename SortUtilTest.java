package assignment04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SortUtilTest {
	ArrayList<Integer> averageCase;
	ArrayList<Integer> bestCase;
	ArrayList<Integer> worstCase;
	ArrayList<Integer> emptyCase;
	Comparator<Integer> naturalIntOrder;
	

	ArrayList<Integer> test = new ArrayList<Integer>();
	ArrayList<Integer> test2 = new ArrayList<Integer>();
	ArrayList<Integer> test3 = new ArrayList<Integer>();
	ArrayList<Integer> test4 = new ArrayList<Integer>();
	ArrayList<Integer> test5 = new ArrayList<Integer>();
	ArrayList<Integer> test6 = new ArrayList<Integer>();
	ArrayList<Integer> avgCaseArr = new ArrayList<Integer>();

	Comparator<Integer> comparator = Comparator.<Integer>naturalOrder();
	@Before
	public void setUp() throws Exception {
		averageCase = SortUtil.generateAverageCase(5);
		bestCase = SortUtil.generateBestCase(5);
		worstCase = SortUtil.generateWorstCase(5);
		emptyCase =	new ArrayList<>();
		naturalIntOrder = Comparator.<Integer>naturalOrder();
//      for(int i = 1; i <= 10; i++) {
//      test.add(i);
//  }
//
//  for(int i = 1; i <= 100; i++) {
//      test2.add(i);
//  }
//  
//  for(int i = 0; i < 20; i++) {
//  	test6.add(i);
//  }
//
//  test3.add(0);
//  test3.add(11);
//  test3.add(-87);
//  test3.add(4);
//  test3.add(-1);
//
//  test4.add(19);
//  test4.add(44);
//  test4.add(77);
//  test4.add(91);
//  test4.add(83);
//  test4.add(71);
//  test4.add(13);
//  test4.add(8);
//  test4.add(19);
//  test4.add(67);
//  
//  
//  
//  test5.add(0);
//  test5.add(-1);
//  test5.add(4);
//  test5.add(3);
//  test5.add(-99);
//  test5.add(99);
//  test5.add(23);
// 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBestCase() {
		SortUtil.mergesort(bestCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(0, 1, 2, 3, 4));
		Assert.assertEquals(ans, bestCase);
	}
	@Test
	public void testAverageCase() {
		SortUtil.mergesort(averageCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(0, 1, 2, 3, 4));
		Assert.assertEquals(ans, averageCase);
	}
	@Test
	public void testWorstCase() {
		SortUtil.mergesort(worstCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(0, 1, 2, 3, 4));
		Assert.assertEquals(ans, worstCase);
	}
	@Test
	public void testNullCase() {
		SortUtil.mergesort(emptyCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Assert.assertEquals(ans, emptyCase);
	}

	@Test
	public void testNegativeValues() {
		for(int i = 0; i < averageCase.size(); i++) {
			int newVal = averageCase.get(i) * -1;
			averageCase.set(i, newVal);
		}
		
		SortUtil.mergesort(averageCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList( -4, -3, -2, -1, 0));
		Assert.assertEquals(ans, averageCase);
	}
	@Test
	public void testEvenArrayListSize() {
		averageCase.add(6);
		
		SortUtil.mergesort(averageCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(0, 1, 2, 3, 4, 6));
		Assert.assertEquals(ans, averageCase);
	}
	
	@Test
	public void testArrayListWithDuplicateVals() {
		averageCase.addAll(Arrays.asList(0, 1, 2, 3, 4));
		
		SortUtil.mergesort(averageCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 4, 4));
		Assert.assertEquals(ans, averageCase);
	}
	
	@Test
	public void testArrayListWithMin() {
		int min = Integer.MIN_VALUE;
		averageCase.add(min);
		
		SortUtil.mergesort(averageCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(min, 0, 1, 2, 3, 4));
		Assert.assertEquals(ans, averageCase);
	}
	
	@Test
	public void testArrayListWithMax() {
		int max = Integer.MAX_VALUE;
		averageCase.add(max);
		
		SortUtil.mergesort(averageCase, naturalIntOrder);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.addAll(Arrays.asList(0, 1, 2, 3, 4, max));
		Assert.assertEquals(ans, averageCase);
	}
	
//	@Test
//	 public void pickMedianPivotTest() {
//	        assertTrue(test.get(SortUtil.pivotmedian(0,test.size()-1)).equals(6));
//	        assertTrue(test2.get(SortUtil.pivotmedian(0,test2.size()-1)).equals(51));
//	        assertTrue(test4.get(SortUtil.pivotmedian(0,test4.size()-1)).equals(71));
//	        assertTrue(test5.get(SortUtil.pivotmedian(0,test5.size()-1)).equals(3));
//	        
//	    }
////	
////	
////	
////	
//	@Test
//   public void quickSortTest1() {
//       int[] test4Sorted= {8,13,19,19,44,67,71,77,83,91};
//
//       //System.out.println("print test 4 before sort" + test4);
//       SortUtil.quicksort(test4, comparator);
//       
//       //System.out.println("print test 4 after sort" + test4);
//       for(int i = 0;i < 10; i++) {
//       assertTrue(test4.get(i).equals(test4Sorted[i]));
//       }
//
//   }
//	
//	@Test
//   public void generateArrayTest4() {
//       ArrayList<Integer> averageCase=SortUtil.generateAverageCase(10000000);
//       for(Integer x:averageCase) {
//           assertTrue(x<=10000000);
//       }
//       //sorting arraylist that's in random order
//       SortUtil.quicksort(averageCase, comparator);
//
//       //System.out.println(averageCase);
//       for(int i=0;i<10000000;i++) {
//           assertTrue(averageCase.get(i).equals(i+1));
//       }
//   }
//	
//	
//	
//	
////	
//	@Test
//	public void quickSortTest2() {
//		int[] sorted = {-99, -1, 0, 3, 4, 23, 99};
//		
//		//System.out.println("Quick sort test5 before sort" + test5);
//		SortUtil.quicksort(test5, comparator);
//		for(int i = 0; i < test5.size(); i++) {
//			assertTrue(test5.get(i).equals(sorted[i]));
//		}
//		//System.out.println("Quick sort test5 after sort" + test5);
//		
//	}
//	
//	@Test
//	public void bestCaseTest() {
//		
//		
//		//int[] sorted = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//		ArrayList<Integer> intArray = SortUtil.generateAverageCase(100000);
//		//System.out.println("Best Case test before sort" + test6);
//		SortUtil.quicksort(intArray, comparator);
//		for(int i = 0; i < test6.size(); i++) {
//			assertTrue(intArray.get(i).equals(test6.get(i)));
//		}
//		//System.out.println("Best Case test after sort" + intArray);
//	}
//	
//	@Test
//	public void averCaseTest() {
//		
//		avgCaseArr = SortUtil.generateAverageCase(15);
//		int[] tempArr = new int[15];
//		for(int i = 0; i < avgCaseArr.size(); i++) {
//			tempArr[i] = avgCaseArr.get(i);
//		}
//		
////		for(int i )
////		System.out.println(avgCaseArr);
//		
//		
//	}
//
//	@Test
//	public void timingTest() {
//		ArrayList<Integer> intList = new ArrayList<Integer>();
//		ArrayList<Integer> intList1 = new ArrayList<Integer>();
//		ArrayList<Integer> intList2 = new ArrayList<Integer>();
//		ArrayList<Integer> intList3 = new ArrayList<Integer>();
//		
//		
//		
//		long startTime, midpointTime, stopTime;
//		// adding elements to array
//		long startTime0=System.nanoTime();
//		while(System.nanoTime()- startTime0<1_000_000_000)
//			;
//		
//		
//		for (int arraySize = 1000; arraySize <= 20000; arraySize += 1000) {
//			// System.out.println(i * 2);
//			intList = SortUtil.generateAverageCase(arraySize);
//			//intList = SortUtil.generateBestCase(arraySize);
////			int[] intList2 = new int[intList.size()];
////			int[] intList3 = new int[intList.size()];
//
////			for(int i = 0; i < intList.size(); i++) {
////				intList2.add(i, intList.get(i));
////				intList3.add(i, intList.get(i));
////			}
//			
//			long firstTime=0;
//			long secondTime=0;
//			long thirdTime=0;
//			
//			//may need to make deep copy here
////			intList2 = intList;
////			intList3 = intList;
//
//			// run this part
//			long timesToLoop = 1000;
////		    while (System.nanoTime() - startTime < 1000000000) { // empty block
////		    }
//
//			// START RANDOM PARTITION TIMING
//			//startTime = System.nanoTime();
//
//			for (long i = 0; i < timesToLoop; i++) {
//				intList2.clear();
//				intList3.clear();
//				
//				for(int j=0;j<arraySize;j++) {
//					intList1.add(intList.get(j));
//					intList2.add(intList.get(j));
//					intList3.add(intList.get(j));
//					
//				}
//				//create a copyArrayMethod return a copied array
//				//start timer
//				//sort the copied array
//				//stop timer
//				//totalTime += stop - start
//				startTime = System.nanoTime();
//				SortUtil.quicksort1(intList1, comparator, 1);	
//				long startTime2 = System.nanoTime();
//				
//				SortUtil.quicksort1(intList2, comparator, 2);	
//				
//				long startTime3 = System.nanoTime();
//				
//				SortUtil.quicksort1(intList3, comparator, 3);
//				long stopTime1 = System.nanoTime();
//				
//				firstTime+=startTime2-startTime;
//				secondTime+=startTime3-startTime2;
//				thirdTime+=stopTime1-startTime3;
//			}
//			
//			double pivot1Time=firstTime/ (double) timesToLoop;
//			double pivot2Time=secondTime/ (double) timesToLoop;
//			double pivot3Time=thirdTime/ (double) timesToLoop;
//			
//			//System.out.println("hello");
//			//System.out.println(arraySize + " " + (pivot1Time*1E-6) + " " + (pivot2Time*1E-6) + " " + (pivot3Time*1E-6));// + "\n");
//		}
//	}
//}
//			midpointTime = System.nanoTime();
//
//			// Run an empty loop to capture the cost of running the loop.
//
//			for (long i = 0; i < timesToLoop; i++) { // empty block
//			}
//
//			stopTime = System.nanoTime();
//			double averageRandTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
//
//			// START MIDDLE PARTITION TIMING
//			startTime = System.nanoTime();
//
//			for (long i = 0; i < timesToLoop; i++) {
//				SortUtil.quicksort1(intList2, comparator, 3);
//
//			}
//			midpointTime = System.nanoTime();
//
//			// Run an empty loop to capture the cost of running the loop.
//
//			for (long i = 0; i < timesToLoop; i++) { // empty block
//			}
//
//			stopTime = System.nanoTime();
//			double averageMiddleTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
//
//			// START MEDIAN PARTITION TIMING
//			startTime = System.nanoTime();
//
//			for (long i = 0; i < timesToLoop; i++) {
//				SortUtil.quicksort1(intList3, comparator, 1);
//
//			}
//			midpointTime = System.nanoTime();
//
//			// Run an empty loop to capture the cost of running the loop.
//			for (long i = 0; i < timesToLoop; i++) { // empty block
//			}
//
//			stopTime = System.nanoTime();
//			double averageMedianTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
//
//			// print all timing for average case
//			System.out.println(arraySize + " " + (averageRandTime*1E-6) + " " + (averageMiddleTime*1E-6) + " " + (averageMedianTime*1E-6));// + "\n");
//			intList.clear();
//		}
//
//	}
//	
//	
//	
//	
//	@Test
//	public void deepCopyTest() {
//		
//		ArrayList<Integer> testList = new ArrayList<Integer>();
//		
//		
//		testList = SortUtil.generateAverageCase(1000000);
//		int[] testList2 = new int[testList.size()];
//		//System.out.println(testList.size());
//		for(int i = 0; i < testList.size(); i++) {
//			testList2[i] = testList.get(i);
//		}
//		
//		
//		
//		//testList.add(testList.size(), -99);
//		//System.out.println("Test list " + testList);
//		//System.out.println("Test list2 " + testList2);
//		
//		//System.out.println();
//////		
//	}
	
}
