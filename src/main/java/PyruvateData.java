public class PyruvateData extends DataSet{
    private final int optimalpH = 8;
    private final int optimalTemp = 37;
    private final double ki = 6.5;
    private final double vmax = 220;
    private final double km = 2.31;

    @Override
    public void createTempData() {
        for (int i = 1; i <= 100; i ++){
            seriesTemp.add(i, EnzymeUtilities.temperatureEffect(3, i, optimalTemp));
        }
        datasetTemp.addSeries(seriesTemp);
    }

    @Override
    public void createpHData() {
        for (int i = 1; i <= 14; i ++){
            seriespH.add(i,EnzymeUtilities.pHEffect(3,i, optimalpH));
        }
        datasetpH.addSeries(seriespH);
    }

    //dont use this one
    @Override
    public void updateSubstrateData(double currentTemp, double currentpH, double inhibitorConcentration) {
        seriesSubstrate.clear();
        for (int i = 0; i < 100; i ++){
            seriesSubstrate.add(i, EnzymeUtilities.calculateRate(3, i, currentTemp,
                    currentpH, optimalTemp, optimalpH, vmax, km, inhibitorConcentration, ki));
        }
    }
}
