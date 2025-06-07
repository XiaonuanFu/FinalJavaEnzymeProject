import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Window Class: creates the window where all the information would be displayed - it has sliders and labels that change based on user input
 */
public class Window {
    /**
     * Private attributes that determine the starting values for all the features - all of them are generally ideal values (ideal for enzymes not humans)
     * So usually the first graph the user generates would show the most "optimal" reaction rate
     * */
    private double inhibitor = 50;
    private double temp = 40;
    private double pH = 7;

    /**
     * Attributes for the frame, the label, and the slider for all the features
     */
    JFrame frame;
    JPanel topPanel;
    JPanel bottomPanel;

    JLabel inhibitorLabel;
    JLabel tempLabel;
    JLabel pHLabel;

    JSlider inhibitorSlider;
    JSlider tempSlider;
    JSlider pHSlider;

    JButton updateButton;

    JLabel note;

    /**
     * The user can choose 1 out of 3 enzymes so this is an array that stores all their names
     * Later the user would have an option bar that displays these options so everything that bar requires is initialized here
     */
    String[] enzymeNames = {"Alpha-Amylase", "Succinate Dehydrogenase", "Pyruvate Kinase"};
    int enzymeIndex = 0;
    JComboBox<String> enzymeTypesCombo;

    private ArrayList<DataSet> datasets = new ArrayList<DataSet>();
    private TwoDChart charts = new TwoDChart();

    /**
     * Drawing the window which will show all the graphs
     */
    public void drawWindow(){
        frame = new JFrame("Enzyme Project");
        frame.setSize(1500,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ends the program when you close the window
        frame.setLayout(new BorderLayout());

        /**
         * Draws the sliders and labels
         */
        drawComponent();
        frame.setVisible(true);

        /**
         * Listener that changes the values displayed based on the user input
         */
        Listener();

        /**
         * Draws the graph
         */
        charts.drawChart(frame);

        /**
         * Adds the data sets and draws the actual contents of the graph
         */
        datasets.add(new AmylaseData());
        datasets.add(new SuccinateData());
        datasets.add(new PyruvateData());

        /**
         * If you click the update button after changing some of the values the graph would change as well
         */
        updataChartpHandTemp();
    }

    /**
     * Drawing the components for the window
     */
    private void drawComponent(){
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        inhibitorLabel = new JLabel("Inhibitor Concentration [I]: 0");
        inhibitorSlider = new JSlider(0,100,0);
        topPanel.add(inhibitorLabel);
        topPanel.add(inhibitorSlider);

        tempLabel = new JLabel("Temperature: 40 °C");
        tempSlider = new JSlider(20,100,40);
        topPanel.add(tempLabel);
        topPanel.add(tempSlider);

        pHLabel = new JLabel("pH: 7");
        pHSlider = new JSlider(1,14,7);
        topPanel.add(pHLabel);
        topPanel.add(pHSlider);

        enzymeTypesCombo = new JComboBox<>(enzymeNames);
        topPanel.add(enzymeTypesCombo);

        updateButton = new JButton("Update");
        topPanel.add(updateButton);

        note = new JLabel("Note: data for reference only");
        bottomPanel.add(note);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Listener that changes the values displayed in the window depending on the change in user input
     */
    private void Listener(){
        /**
         * Inhibitor concentration slider listener
         */
        inhibitorSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                inhibitor = inhibitorSlider.getValue();
                inhibitorLabel.setText("Inhibitor Concentration [I]: " + inhibitor); // Updates the amount of inhibitors displayed

            }
        });

        /**
         * pH slider listener
         */
        pHSlider.addChangeListener(e -> {
            pH = pHSlider.getValue(); // Updates the value displayed by pH from 1-14
            pHLabel.setText(String.format("pH: " + pH));
        });

        /**
         * Temperature slider listener
         */
        tempSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temp = tempSlider.getValue();
                tempLabel.setText("Temperature: " + temp + " °C"); // Updates the value displayed for temperature
            }
        });

        /**
         * Update button listener
         */
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChartsForEnzyme();  // updates the charts based on the new data
            }
        });

        /**
         * Listener that changes the current enzyme selected
         */
        enzymeTypesCombo.addActionListener(e -> {
            JComboBox<?> combo = (JComboBox<?>) e.getSource();
            this.enzymeIndex = combo.getSelectedIndex();
            /**
             * Updates the pH and temp data based on the current enzyme - because each enzyme has different pH and temperature conditions they react under
             * Selection can be changed using the combo box
             */
            updataChartpHandTemp();
        });

    }

    /**
     * Method that updates the rate vs substrate graph
     */
    private void updateChartsForEnzyme(){
        datasets.get(enzymeIndex).updateSubstrateData(temp, pH, inhibitor);
        charts.setSubstrateChartData(datasets.get(enzymeIndex).getDatasetSubstrate());
    }

    /**
     * Method that updates the rate vs pH and rate vs temp graphs
     */
    private void updataChartpHandTemp(){
        charts.setpHChartData(datasets.get(enzymeIndex).getDatasetpH());
        charts.setTempChartData(datasets.get(enzymeIndex).getDatasetTemp());
    }

}
