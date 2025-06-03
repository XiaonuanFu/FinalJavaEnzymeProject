public class AmylaseData extends DataSet{
    private  final int optimalpH = 7;
    private  final int optimalTemp = 37;
    private final double vmax = 200;
    private final double km = 1;

    @Override
    public void createTempData() {
        for (int i = 1; i <= 100; i ++){
            seriesTemp.add(i, EnzymeUtilities.temperatureEffect(1, i, optimalTemp));
        }
        datasetTemp.addSeries(seriesTemp);

    }

    @Override
    public void createpHData() {
        for (int i = 1; i <= 14; i ++){
            seriespH.add(i,EnzymeUtilities.pHEffect(1,i, optimalpH));
        }
        datasetpH.addSeries(seriespH);

    }

    @Override
    public void updateSubstrateData(double currentTemp, double currentpH, double inhibitorConcentration) {
        seriesSubstrate.clear();
        for (int i = 0; i < 100; i ++){
            seriesSubstrate.add(i, EnzymeUtilities.calculateRate(1, i, currentTemp,
                    currentpH, optimalTemp, optimalpH, vmax, km, 0, 0));
        }

    }
}
