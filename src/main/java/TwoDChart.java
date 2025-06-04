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

/**
 * 2D Chart Class: creates the graphs for the data
 */

public class TwoDChart {
    /**
     * Charts that have to be drawn (pH chart, temp chart, and big substrate chart)
     */
    private JFreeChart pHChart;
    private JFreeChart tempChart;
    private JFreeChart substrateChart;

    /**
     * The chart panels for each of the charts
     */
    private ChartPanel phChartPanel;
    private ChartPanel tempChartPanel;
    private ChartPanel substrateChartPanel;

    /**
     * I plan to have two small charts (pH and temperature) on the top
     * And one large chart on the bottom (substrate concentration)
     */
    private JPanel topChartPanel;
    private JPanel bottomChartPanel;

    /**
     * The function that draws all the charts
     * @param frame
     */
    public void drawChart(JFrame frame){
        /**
         * Drawing the pH graph
         */
        pHChart = ChartFactory.createXYLineChart(
                "Rate of Reaction as a Function of pH",
                "pH",
                "Rate of Reaction (mol·L⁻¹s⁻¹)",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        /**
         * Drawing the temperature graph
         */
        tempChart = ChartFactory.createXYLineChart(
                "Rate of Reaction as a Function of Temperature",
                "Temperature (°C)",
                "Rate of Reaction (mol·L⁻¹s⁻¹)",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        /**
         * Drawing the substrate graph
         */
        substrateChart = ChartFactory.createXYLineChart(
                "Rate of Reaction as a Function of Substrate Concentration (Influenced by pH and Temperature)",
                "Substrate Concentration (mol·L⁻¹)",
                "Rate of Reaction (mol·L⁻¹s⁻¹)",
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

    /**
     * Plotting the data points for the pH vs reaction rate data
     * @param data
     */
    public void setpHChartData(XYSeriesCollection data){
        XYPlot plot = pHChart.getXYPlot();
        plot.setDataset(data);
    }

    /**
     * Plotting the data points for the temp vs reaction rate data
     * @param data
     */
    public void setTempChartData(XYSeriesCollection data){
        XYPlot plot = tempChart.getXYPlot();
        plot.setDataset(data);
    }

    /**
     * Plotting the data points for the substrate concentration vs reaction rate data
     * @param data
     */
    public void setSubstrateChartData(XYSeriesCollection data){
        XYPlot plot = substrateChart.getXYPlot();
        plot.setDataset(data);
    }


}
