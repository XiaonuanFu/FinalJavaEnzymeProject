import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public abstract class DataSet {
    protected XYSeriesCollection datasetTemp;
    protected XYSeriesCollection datasetpH;
    protected XYSeriesCollection datasetSubstrate;

    protected XYSeries seriesTemp;
    protected XYSeries seriespH;
    protected XYSeries seriesSubstrate;

    public DataSet(){
        datasetTemp = new XYSeriesCollection();
        datasetpH = new XYSeriesCollection();
        datasetSubstrate = new XYSeriesCollection();

        seriesTemp = new XYSeries("Temp");
        seriespH = new XYSeries("pH");
        seriesSubstrate = new XYSeries("Substrate");

        datasetSubstrate.addSeries(seriesSubstrate);

        createpHData();
        createTempData();
    }

    public XYSeriesCollection getDatasetTemp(){
        return datasetTemp;
    }

    public XYSeriesCollection getDatasetpH(){
        return datasetpH;
    }

    public XYSeriesCollection getDatasetSubstrate(){
        return datasetSubstrate;
    }

    public abstract void createTempData();
    public abstract void createpHData();
    public abstract void updateSubstrateDataCompetitive(double inhibitorConcentration, double currentTemp, double currentpH);
    public abstract void updateSubstrateData(double currentTemp, double currentpH);
}
