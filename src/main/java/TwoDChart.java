import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Series;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class TwoDChart {
    private JFreeChart pHChart;
    private JFreeChart tempChart;
    private JFreeChart substrateChart;

    private ChartPanel phChartPanel;
    private ChartPanel tempChartPanel;
    private ChartPanel substrateChartPanel;

    private JPanel topChartPanel;
    private JPanel bottomChartPanel;

    public void drawChart(JFrame frame){
        pHChart = ChartFactory.createXYLineChart(
                "Rate of Reaction as a Function of pH",
                "pH",
                "Rate of Reaction",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        tempChart = ChartFactory.createXYLineChart(
                "Rate of Reaction as a Function of Temperature",
                "Temperature",
                "Rate of Reaction",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        substrateChart = ChartFactory.createXYLineChart(
                "Rate of Reaction as a Function of Substrate Concentration (Influenced by pH and Temperature)",
                "Substrate",
                "Rate of Reaction",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        phChartPanel = new ChartPanel(pHChart);
        tempChartPanel = new ChartPanel(tempChart);
        substrateChartPanel = new ChartPanel(substrateChart);

        phChartPanel.setPreferredSize(new Dimension(720, 300));
        tempChartPanel.setPreferredSize(new Dimension(720, 300));
        substrateChartPanel.setPreferredSize(new Dimension(1500, 400));

        topChartPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topChartPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));
        topChartPanel.add(phChartPanel);
        topChartPanel.add(tempChartPanel);

        bottomChartPanel = new JPanel(new BorderLayout());
        bottomChartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomChartPanel.add(substrateChartPanel);

        frame.add(topChartPanel, BorderLayout.NORTH);
        frame.add(bottomChartPanel, BorderLayout.CENTER);

        frame.revalidate();      // recalculate layout
        frame.repaint();         // redraws the window
    }

    public void setpHChartData(XYSeriesCollection data){
        XYPlot plot = pHChart.getXYPlot();
        plot.setDataset(data);
    }

    public void setTempChartData(XYSeriesCollection data){
        XYPlot plot = tempChart.getXYPlot();
        plot.setDataset(data);
    }

    public void setSubstrateChartData(XYSeriesCollection data){
        XYPlot plot = substrateChart.getXYPlot();
        plot.setDataset(data);
    }


}
