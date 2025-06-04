import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Data Set Class: Gathers the data for each enzyme that has to be put on each graph
 * It doesn't do a lot it's just a superclass for the three enzyme classes - it provides the parent method for calculating the data
 */

public abstract class DataSet {
    /**
     * The data for the temperature, pH, and substrate
     */
    protected XYSeriesCollection datasetTemp;
    protected XYSeriesCollection datasetpH;
    protected XYSeriesCollection datasetSubstrate;

    protected XYSeries seriesTemp;
    protected XYSeries seriespH;
    protected XYSeries seriesSubstrate;

    /**
     * Constructor for the Data Set class that initializes the values (that's what a constructor does !! 😁)
     */
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

    /**
     * Getter for the temp data set
     * @return
     */
    public XYSeriesCollection getDatasetTemp(){
        return datasetTemp;
    }

    /**
     * Getter for the pH data set
     * @return
     */
    public XYSeriesCollection getDatasetpH(){
        return datasetpH;
    }

    /**
     * Getter for the substrate data set
     * @return
     */
    public XYSeriesCollection getDatasetSubstrate(){
        return datasetSubstrate;
    }

    /**
     * Three abstract methods that creates the data - they don't do anything
     * These methods are overridden in the subclasses because the enzymes have these functions in common. but they are each calculated differently (inheritance!!)
     */
    public abstract void createTempData();
    public abstract void createpHData();
    public abstract void updateSubstrateData(double currentTemp, double currentpH, double inhibitorConcentration);
}
