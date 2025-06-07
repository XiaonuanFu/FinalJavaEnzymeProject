/**
 * This is the data set calculations for Pyruvate Kinase (it is a subclass of the Data Set class since this enzyme's data is specific towards itself
 * It overrides all the abstract methods that calculate data in the Data Set class
 */

public class PyruvateData extends DataSet{
    private final double optimalpH = 7.5;
    private final int optimalTemp = 25;
    private final double ki = 6.5;
    private final double vmax = 220;
    private final double km = 2.31;

    /**
     * Calculates the data for the temp vs rate graph
     */
    @Override
    public void createTempData() {
        for (int i = 1; i <= 100; i ++){
            seriesTemp.add(i, EnzymeUtilities.temperatureEffect(3, i, optimalTemp));
        }
        datasetTemp.addSeries(seriesTemp);
    }

    /**
     * Calculates the data for the pH vs rate graph
     */
    @Override
    public void createpHData() {
        for (int i = 1; i <= 14; i ++){
            seriespH.add(i,EnzymeUtilities.pHEffect(3,i, optimalpH));
        }
        datasetpH.addSeries(seriespH);
    }

    /**
     * Calculates the substrate concentration vs rate graph
     * Takes in the current temp, pH, and inhibitor concentration to calculate the final rate
     * Goes through a for-loop (i represents the amount of substrate - from 1 to 100) and calls the equation each time for the different substrate values
     * @param currentTemp
     * @param currentpH
     * @param inhibitorConcentration
     */
    @Override
    public void updateSubstrateData(double currentTemp, double currentpH, double inhibitorConcentration) {
        seriesSubstrate.clear();
        for (int i = 0; i < 100; i ++){
            seriesSubstrate.add(i, EnzymeUtilities.calculateRate(3, i, currentTemp,
                    currentpH, optimalTemp, optimalpH, vmax, km, inhibitorConcentration, ki));
        }
    }
}
