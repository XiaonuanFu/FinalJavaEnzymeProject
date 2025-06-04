/**
 * Enzyme Utilities Class: The equations and functions used to calculate the data points - the data sets call to this class
 */

public class EnzymeUtilities {

    /**
     * Gas constant instance variable
     */
    private static final double GAS_CONSTANT = 8.314; // J/mol·K

    /**
     * Enzyme identifiers
     */
    public static final int ALPHA_AMYLASE = 1;
    public static final int SUCCINATE_DEHYDROGENASE = 2;
    public static final int PYRUVATE_KINASE = 3;

    private static double[] pHWidth = {0, 1.8, 1.2, 1.5};
    private static double[] tempTolerance = {0, 15.0, 10.0, 12.0}; // temperature tolerance range (°C)

    /**
     * Equation for the pH vs rate of reaction graph based on the enzyme, current pH, and the optimal pH
     * @param enzyme
     * @param currentpH
     * @param optimalpH
     * @return
     */
    public static double pHEffect(int enzyme, double currentpH, double optimalpH) {
        // Equation for the pH vs rate graph
        return Math.exp(-Math.pow((currentpH - optimalpH)/pHWidth[enzyme], 2));
    }

    /**
     * Equation for the temp vs rate of reaction based on the enzyme, current temp, and the optimal temp
     * @param enzyme
     * @param currentTemp
     * @param optimalTemp
     * @return
     */
    public static double temperatureEffect(int enzyme, double currentTemp, double optimalTemp) {
        double delta = Math.abs(currentTemp - optimalTemp);
        if (delta > tempTolerance[enzyme]) {
            return Math.exp(-Math.pow((delta - tempTolerance[enzyme])/5.0, 2));
        }
        return 1.0;
    }

    /**
     * Calculates the rate of reaction for each enzyme as a function of substrate concentration while also taking into account the current pH, temp, and inhibitor concentration
     * (Inhibitors compete with substrates for the enzymes so if an inhibitor binds to the enzyme it won't react so it would slow down the rate)
     * Since there are three different enzymes (each has a different competitor) there are three different equations
     * The function determines which enzyme is currently selected and calls the specific function for that enzyme
     * It then multiplies the base rate value with the temperature factor and pH factor to determine the final value (reaction rate)
     * @param enzyme
     * @param substrateConc
     * @param temperature
     * @param pH
     * @param optimalTemp
     * @param optimalpH
     * @param vmax
     * @param km
     * @param inhibitor
     * @param ki
     * @return
     */
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

    /**
     * Alpha-amylase has no inhibitors/competition so it is a standard Michaelis-Menten equation for the graph
     * @param substrate
     * @param vmax
     * @param km
     * @return
     */
    public static double michaelisMenten(double substrate, double vmax, double km) {
        return (vmax * substrate) / (km + substrate);
    }

    /**
     * Succinate dehydrogenase has competitive inhibitors so the equation would be a Michaelis-Menten equation for competitive inhibition for the graph
     * @param substrate
     * @param vmax
     * @param km
     * @param I
     * @param ki
     * @return
     */
    public static double competitiveInhibitor(double substrate, double vmax, double km, double I, double ki){
        double adjustedKm = km * (1 + I / ki);
        double rate = (vmax * substrate) / (adjustedKm + substrate);
        return rate;
    }

    /**
     * Pyruvate kinase has a non-competitive inhibitor so this is a Michaelis-Menten equation for non-competitive inhibition for the graph
     * @param substrate
     * @param vmax
     * @param km
     * @param I
     * @param ki
     * @return
     */
    public static double noncompetitiveInhibitor(double substrate, double vmax, double km, double I, double ki){
        double inhibitedVmax = vmax / (1 + I / ki);
        double rate = (inhibitedVmax * substrate) / (km + substrate);
        return rate;
    }
}
