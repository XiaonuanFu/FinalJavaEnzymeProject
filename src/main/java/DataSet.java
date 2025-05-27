import org.jfree.data.general.Series;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataSet {
    private XYSeriesCollection dataset;
    private XYSeries series;

    public void insertValues(String enzymeType, double temperature, double substrate, double pH){
        dataset = new XYSeriesCollection();
        series = new XYSeries("Activity rate");
        for (int i = 0; i < 100; i++ ){
            series.add(1, i);
        }
        dataset.addSeries(series);
    }

    public XYSeriesCollection getSeries(){
        return dataset;
    }
}
