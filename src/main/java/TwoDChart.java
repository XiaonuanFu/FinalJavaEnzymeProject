import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.Series;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class TwoDChart {
    public void drawChart(JFrame frame){
        DataSet data = new DataSet();
        data.insertValues("", 10,100, 7);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Title",
                "Substrate Concentration",
                "Rate of Reaction",
                data.getSeries(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        //添加line chart 图表，放在中间
        ChartPanel chartPanel = new ChartPanel(chart);   // 创建2d图表面板
        frame.add(chartPanel);                           // 将2d图表面板添加到窗口
        frame.revalidate();      // 重新计算布局
        frame.repaint();         // 重绘窗口
    }

}
