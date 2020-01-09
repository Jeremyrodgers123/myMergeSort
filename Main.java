package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import assignment03.AnagramUtil;
import assignment03.FileReader;
import lab03.Charter;

public class Main {

	public static void main(String[] args) {

		ArrayList<Integer> averageCase = SortUtil.generateAverageCase(20);
		Comparator<Integer> naturalIntOrder = Comparator.<Integer>naturalOrder();
		SortUtil.setInsertionSortThreshold(0);
		//SortUtil.insertionSort(worstCase, 0, worstCase.size() -1, naturalIntOrder);
		//SortUtil.mergesort(worstCase, naturalIntOrder);
		//SortUtil.print(averageCase);
		//testMergeSortThresholds();
		//testQuickSort();
		testMergeAndQuickSort();
	}
	
	
	public static void testMergeSortThresholds() {
		long startTime, midpointTime, stopTime, totalTime;
		
		Comparator<Integer> naturalIntOrder = Comparator.<Integer>naturalOrder();
		ArrayList<Integer> list;
		
		int [] thresholds = {0, 5, 10, 20, 100};
		String [] thresholdString = {"0", "5", "10", "20", "100"};
		String [] fileNames = new String [thresholds.length];
		for(int j = 0; j < thresholds.length; j++) {
			System.out.println("new Threshold " + thresholds[j] );
			long timesToLoop = 1000;
			int powerVal = 5;//start at 2 to the 10
			try (FileWriter fw = new FileWriter(new File( "Threshold" + thresholds[j] +"assignment04.tsv"))){
				SortUtil.setInsertionSortThreshold(thresholds[j]);
				while (powerVal < 12) {	
					int size = (int) Math.pow(2, powerVal);

					totalTime = 0;
				    startTime = System.nanoTime();
				    while (System.nanoTime() - startTime < 1000000000) { // empty block
				    }
				    //list = buildList("average", size);
				    //SortUtil.print(list);
					
				    startTime = System.nanoTime();
				    
				    for (int i = 0; i < timesToLoop; i++) {
						list = buildList("average", size);		
						SortUtil.mergesort(list,naturalIntOrder);
					}
					
				    midpointTime = System.nanoTime();
				    
				    for (int i = 0; i < timesToLoop; i++) {
						list = buildList("average", size);
					}
				    
				    stopTime = System.nanoTime();
				    
					double averageTime = (midpointTime - startTime) - (stopTime - midpointTime) / timesToLoop;
					System.out.println(averageTime);
					powerVal++;
					fw.write(size + "\t" + averageTime + "\n"); // write to file.
				}
			}catch(IOException e) {
	
				e.printStackTrace();
			}
			fileNames[j] = "Threshold" + thresholds[j] +"assignment04.tsv";
		}
		Charter1 charter = new Charter1();
		
		charter.createChart(fileNames, "assignment04.png", thresholdString);
	}
	
	public static void testMergeAndQuickSort() {
		long startTime, midpointTime, stopTime, totalTime;
		
		Comparator<Integer> naturalIntOrder = Comparator.<Integer>naturalOrder();
		ArrayList<Integer> list;
		SortUtil.setInsertionSortThreshold(15);
		String [] listTypes = {"bestMerge","bestQuick", "averageMerge", "averageQuick", "worstMerge", "worstQuick"};
		String [] fileNames = new String [listTypes.length];
		boolean isMergesort = false;
		for(int j = 0; j < listTypes.length; j++) {
			System.out.println("new list Type " + listTypes[j] );
			long timesToLoop = 10000;
			isMergesort = !isMergesort;
			//if 0, use merge sort, if 1 use quicksort
			
				int powerVal = 5;//start at 2 to the 10
				try (FileWriter fw = new FileWriter(new File(listTypes[j] + "case" +"assignment04.tsv"))){
					
					while (powerVal < 16) {	
						int size = (int) Math.pow(2, powerVal);
						startTime = System.nanoTime();
						
						while (System.nanoTime()< 1000000000) { // empty block
					    }
						totalTime = 0;
						startTime = System.nanoTime();
						
//						if(isMergesort) System.out.println("Mergesort");
//						else System.out.println("quicksort");
					    //startTime = System.nanoTime();		
						for (int i = 0; i < timesToLoop; i++) {
							list = buildList(listTypes[j], size);
							if(isMergesort) {
								SortUtil.mergesort(list,naturalIntOrder);
							}else {
								SortUtil.quicksort(list,naturalIntOrder);
							}
							//totalTime += stopTime - startTime;
						}
						
						midpointTime = System.nanoTime();
						for (int i = 0; i < timesToLoop; i++) {
							list = buildList(listTypes[j], size);
						}
						stopTime = System.nanoTime();
						double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
						System.out.println(averageTime);
						powerVal++;
						fw.write(size + "\t" + averageTime + "\n"); // write to file.
					}
				}catch(IOException e) {
		
					e.printStackTrace();
				}
				fileNames[j] = listTypes[j] + "case" +"assignment04.tsv";
			}
		
		Charter1 charter = new Charter1();
		charter.createChart(fileNames, "assignment04.png", listTypes);
		
	}

	public static void testQuickSort() {


		final int ITER_COUNT = 100;
		Comparator<Integer> naturalInteger = Comparator.<Integer>naturalOrder();
		long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
          ;
//          Random random = new Random();
          for (int exp = 1000; exp <= 10000; exp+=1000) { // This is used as the exponent to calculate the size of the set.
            int size = exp; //(int) Math.pow(2, exp); // or ..


            // Do the experiment multiple times, and average out the results
            long totalTime = 0;
            long firstTime = 0;
            long secondTime = 0;
            long thirdTime = 0;

//            ArrayList<Integer> bestCase=SortUtil.generateBestCase(size);

            ArrayList<Integer> averageCase=SortUtil.generateAverageCase(size);
            ArrayList<Integer> averageCase1=new ArrayList<>();
            ArrayList<Integer> averageCase2=new ArrayList<>();
            ArrayList<Integer> averageCase3=new ArrayList<>();

            ArrayList<Integer> bestCase=SortUtil.generateBestCase(size);
            ArrayList<Integer> bestCase1=new ArrayList<>();
            ArrayList<Integer> bestCase2=new ArrayList<>();
            ArrayList<Integer> bestCase3=new ArrayList<>();
            
            ArrayList<Integer> worstCase=SortUtil.generateWorstCase(size);
            ArrayList<Integer> worstCase1=new ArrayList<>();
            ArrayList<Integer> worstCase2=new ArrayList<>();
            ArrayList<Integer> worstCase3=new ArrayList<>();
            
            for (int iter = 0; iter < ITER_COUNT; iter++) {
                // SET UP!
            	  averageCase1.clear();
                  averageCase2.clear();
                  averageCase3.clear();
                  
                  //bestCase1.clear();
                  //bestCase2.clear();
                  //bestCase3.clear();
                  //worstCase2.clear();
                  for(int i=0;i<size;i++) {
//                      averageCase1.add(averageCase.get(i));
//                      averageCase2.add(averageCase.get(i));
//                      averageCase3.add(averageCase.get(i));
                	  
//                      bestCase1.add(bestCase.get(i));
//                      bestCase2.add(bestCase.get(i));
//                      bestCase3.add(bestCase.get(i));
                      
                      worstCase1.add(worstCase.get(i));
                      worstCase2.add(worstCase.get(i));
                      worstCase3.add(worstCase.get(i));

                  }

                // TIME IT!
                long start = System.nanoTime();

                SortUtil.quicksort1(worstCase1, naturalInteger, 2);

                long start2 = System.nanoTime();

                SortUtil.quicksort1(worstCase2, naturalInteger, 1);

                long start3 = System.nanoTime();

                SortUtil.quicksort1(worstCase3, naturalInteger, 3);

                long stop = System.nanoTime();
                
                firstTime += start2-start;
                secondTime += start3-start2;
                thirdTime += stop-start3;
                totalTime += stop-start;
              }
              double randomPivotTime= firstTime / (double) ITER_COUNT;
              double medianPivotTime= secondTime / (double) ITER_COUNT;
              double medianOfThreePivotTime= thirdTime / (double) ITER_COUNT;
              double averageTime = totalTime / (double) ITER_COUNT;

              //time in milliseconds
             // System.out.println(size + "\t" + averageTime1E-6); // print to console

              //System.out.println(size + "\t" + "Random pivot: "+randomPivotTime1E-6+" "+"Median pivot: "+medianPivotTime1E-6+" "+"Median of 3 pivot: "+medianOfThreePivotTime1E-6); // print to console
//              System.out.println(size + "\t" + "Best Case: "+ randomPivotTime*1E-6+" "+"Average Case: "+medianPivotTime*1E-6+" "+"Worst Case: "+medianOfThreePivotTime*1E-6); // print to console
              System.out.println(size + "\t" + "Pivot2: "+ randomPivotTime*1E-6+" "+"Pivot1: "+medianPivotTime*1E-6+" "+"Pivot3: "+medianOfThreePivotTime*1E-6); // print to console
	
          }
	}
    public static String lastPrinted= "";
	public static ArrayList<Integer>  buildList(String listType, int size) {
		ArrayList<Integer> list;
		switch(listType)
		{
			case "bestMerge":
				list = SortUtil.generateBestCase(size);
//				if(lastPrinted != "Best") {
//					System.out.println("Best Case Generated");
//					lastPrinted = "Best";
//				}
				break;
			case "averageMerge":
				list = SortUtil.generateAverageCase(size);
//				if(lastPrinted != "Average") {
//					System.out.println("Average Case Generated");
//					lastPrinted = "Average";
//				}
				break;
			case "worstMerge":
				list = SortUtil.generateWorstCase(size);
//				if(lastPrinted != "Worst") {	
//					System.out.println("Worst Case Generated");
//					lastPrinted = "Worst";
//				}
				break;
			case "bestQuick":
				list = SortUtil.generateBestCase(size);
//				if(lastPrinted != "Best") {
//					System.out.println("Best Case Generated");
//					lastPrinted = "Best";
//				}
				break;
			case "averageQuick":
				list = SortUtil.generateAverageCase(size);
//				if(lastPrinted != "Average") {
//					System.out.println("Average Case Generated");
//					lastPrinted = "Average";
//				}
				break;
			case "worstQuick":
				list = SortUtil.generateWorstCase(size);
//				if(lastPrinted != "Worst") {
//					System.out.println("Worst Case Generated");
//					lastPrinted = "Worst";
//				}
				break;
			default:
				throw new Error("Determine best, average, or worse case");
		}
		
		return list;
	}
	
  

}
