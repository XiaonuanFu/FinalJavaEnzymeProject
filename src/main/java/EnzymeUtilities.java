public class EnzymeUtilities {

    private static final double GAS_CONSTANT = 8.314; // J/mol·K

    // Enzyme identifiers
    public static final int ALPHA_AMYLASE = 1;
    public static final int SUCCINATE_DEHYDROGENASE = 2;
    public static final int PYRUVATE_KINASE = 3;

    private static double[] pHWidth = {0, 1.8, 1.2, 1.5};
    private static double[] tempTolerance = {0, 15.0, 10.0, 12.0}; // 温度耐受范围(°C)

    private static double[] activationEnergy = {0, 55000.0, 60000.0, 58000.0}; // J/mol
    private static double[] hillCoefficient = {0, 1.0, 1.0, 2.0}; // for cooperativity

    public static double pHEffect(int enzyme, double currentpH, double optimalpH) {
        // 使用酶特定的pH曲线宽度
        return Math.exp(-Math.pow((currentpH - optimalpH)/pHWidth[enzyme], 2));
    }

    public static double temperatureEffect(int enzyme, double currentTemp, double optimalTemp) {
        double delta = Math.abs(currentTemp - optimalTemp);
        if (delta > tempTolerance[enzyme]) {
            return Math.exp(-Math.pow((delta - tempTolerance[enzyme])/5.0, 2));
        }
        return 1.0;
    }

    public static double calculateRate(int enzyme, double substrateConc, double temperature, double pH, double optimalTemp, double optimalpH, double vmax, double km, double inhibitor, double ki) {
        // Calculate base rate
        double baseRate;
        if (enzyme == PYRUVATE_KINASE) {
            baseRate = noncompetitiveInhibitor(substrateConc, vmax, km, inhibitor, ki);
        }else if(enzyme == SUCCINATE_DEHYDROGENASE) {
            baseRate = competitiveInhibitor(substrateConc, vmax, km, inhibitor, ki);
        }
        else{
            baseRate = michaelisMenten(substrateConc, vmax, km);
        }

        // Apply environmental corrections
        double tempFactor = temperatureEffect(enzyme, temperature, optimalTemp);
        double pHFactor = pHEffect(enzyme, pH, optimalpH);

        return baseRate * tempFactor * pHFactor;
    }

    // Michaelis-Menten equation for no inhibitor
    public static double michaelisMenten(double substrate, double vmax, double km) {
        return (vmax * substrate) / (km + substrate);
    }

    // competitive inhibitor rate equation
    public static double competitiveInhibitor(double substrate, double vmax, double km, double I, double ki){
        double adjustedKm = km * (1 + I / ki);
        double rate = (vmax * substrate) / (adjustedKm + substrate);

        return rate;
    }

    // non competitive inhibitor rate equation
    public static double noncompetitiveInhibitor(double substrate, double vmax, double km, double I, double ki){
        double inhibitedVmax = vmax / (1 + I / ki);
        double rate = (inhibitedVmax * substrate) / (km + substrate);
        return rate;
    }

//     Hill equation for cooperative enzymes
    private static double hillEquation(double substrate, double vmax,
                                double kHalf, double hillCoeff) {
        return (vmax * Math.pow(substrate, hillCoeff)) /
                (Math.pow(kHalf, hillCoeff) + Math.pow(substrate, hillCoeff));
    }
}
