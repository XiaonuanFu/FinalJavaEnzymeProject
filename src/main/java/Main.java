public class Main {
    /**
     * Main function: I try to not do anything in this class except initialize a new window
     * (Otherwise the code gets messy and I get confused)
     *
     * I'm going ot have 4 classes that actually do all my functions:
     * - Window
     * - 2D Chart
     * - Data set (superclass to 3 subclasses - each subclass is a specific enzyme)
     * - Enzyme utilities
     *
     * The main initializes a Window object then draws a window
     * The window then calls to the 2D Chart which draws a chart on the window
     * The window also calls to the Data Set class which essentially gathers all the data points on the graphs
     * My three enzymes extend the Data Set class because they have different data and constants
     * (it has abstract methods that the subclasses inherit and override - those overriden methods are essentially what create the final graph)
     * The Enzyme Utilities class are all the equations that calculate rate vs pH, rate vs substrate, rate vs temperature etc.
     * So the enzyme classes would call the functions from the utilities class in for-loops
     * @param args
     */
    public static void main(String[] args){
        Window window = new Window();
        window.drawWindow();
    }
}
