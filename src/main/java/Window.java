import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window {
    private double inhibitor = 50;
    private double temp = 40;
    private double pH = 7;

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

    String[] enzymeNames = {"Alpha-Amylase", "Succinate Dehydrogenase", "Pyruvate Kinase"};
    int enzymeIndex = 0;
    JComboBox<String> enzymeTypesCombo;

    private ArrayList<DataSet> datasets = new ArrayList<DataSet>();
    private TwoDChart charts = new TwoDChart();

    //drawing the window for the graph
    public void drawWindow(){
        frame = new JFrame("Enzyme Project");
        frame.setSize(1500,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends the program when you close the window
        frame.setLayout(new BorderLayout());

        drawComponent();
        frame.setVisible(true);

        Listener();


        charts.drawChart(frame);

        datasets.add(new AmylaseData());
        datasets.add(new SuccinateData());
        datasets.add(new PyruvateData());

        updataChartpHandTemp();
    }

    //drawing the components for the window
    private void drawComponent(){
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        inhibitorLabel = new JLabel("Inhibitor Concentration [I]: 50");
        inhibitorSlider = new JSlider(0,100,50);
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

    private void Listener(){
        // inhibitor concentration slider listener
        inhibitorSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                inhibitor = inhibitorSlider.getValue();
                inhibitorLabel.setText("Inhibitor Concentration [I]: " + inhibitor); // 更新底物浓度标签显示

            }
        });

        //pH slider listener
        pHSlider.addChangeListener(e -> {
            pH = pHSlider.getValue(); // changes the value displayed by pH from 1-14
            pHLabel.setText(String.format("pH: " + pH));
        });

        // temperature slider listener
        tempSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temp = tempSlider.getValue();
                tempLabel.setText("Temperature: " + temp + " °C"); // shows the new temperature
            }
        });

        //update按钮按下的监听
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChartsForEnzyme();  // 调用你定义的函数
            }
        });

        enzymeTypesCombo.addActionListener(e -> {
            JComboBox<?> combo = (JComboBox<?>) e.getSource();
            this.enzymeIndex = combo.getSelectedIndex();
            // 根据选择更新UI或数据，可以combo选择变更，也可以用按钮变更
            updataChartpHandTemp();
        });

    }

    private void updateChartsForEnzyme(){
        datasets.get(enzymeIndex).updateSubstrateData(temp, pH, inhibitor);
        charts.setSubstrateChartData(datasets.get(enzymeIndex).getDatasetSubstrate());
    }

    private void updataChartpHandTemp(){
        charts.setpHChartData(datasets.get(enzymeIndex).getDatasetpH());
        charts.setTempChartData(datasets.get(enzymeIndex).getDatasetTemp());
    }

}
