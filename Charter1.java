package assignment04;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Charter1 {

  public void createChart(String [] dataFiles, String filename, String [] dataNames) {
    JFreeChart chart = ChartFactory.createXYLineChart("Sort Util Timing", "Size (N)", "Time (ns)",
        createDataSet(dataFiles, dataNames));
    // chart.getXYPlot().setDomainAxis(new LogarithmicAxis("Size (N"));
    try {
      ChartUtilities.saveChartAsPNG(new File(filename), chart, 750, 750);
    } catch (IOException e) {
      e.printStackTrace();
    }
    showChart(chart);
  }

  private void showChart(JFreeChart chart) {
    JFrame frame = new JFrame();
    frame.setTitle("SortUtil Timing Experiement");
    ChartPanel chartPanel = new ChartPanel(chart);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(800, 400));
    frame.add(chartPanel);
    frame.pack();
    frame.setVisible(true);
  }

  private XYDataset createDataSet(String [] dataFiles, String [] dataNames) {
    XYSeriesCollection dataset = new XYSeriesCollection();
    for(int i = 0; i < dataFiles.length; i++) {
	    try (FileReader reader = new FileReader(dataFiles[i]); BufferedReader br = new BufferedReader(reader)) {
	      String line;
	      XYSeries series = new XYSeries(dataNames[i]);
	      while ((line = br.readLine()) != null) {
	        String[] split = line.split("\t");
	        series.add(new XYDataItem(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
	      }
	      dataset.addSeries(series);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
    }
	return dataset;
    
  }
}
