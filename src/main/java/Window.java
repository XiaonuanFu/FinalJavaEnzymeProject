import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Window {
    private double substrate;
    private double temp;
    private double pH;
    private int inhibitor;
    private double Km;

    JFrame frame;
    JPanel topPanel;
    JPanel bottomPanel;

    JLabel substrateLabel;
    JLabel tempLabel;
    JLabel pHLabel;

    JSlider substrateSlider;
    JSlider tempSlider;
    JSlider pHSlider;

    JButton updateButton;

    JLabel note;

    String[] enzymeNames = {"Alpha-Amylase", "Succinate Dehydrogenase", "Pyruvate Kinase"};
    JComboBox<String> enzymeTypesCombo;

    //drawing the window for the graph
    public void drawWindow(){
        frame = new JFrame("Enzyme Project");
        frame.setSize(1500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        drawComponent();
        Listener();

        TwoDChart chart = new TwoDChart();
        chart.drawChart(frame);

        frame.setVisible(true);


    }

    //drawing the components for the window
    private void drawComponent(){
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        substrateLabel = new JLabel("Substrate Concentration [S]: 50");
        substrateSlider = new JSlider(0,100,50);
        topPanel.add(substrateLabel);
        topPanel.add(substrateSlider);

        tempLabel = new JLabel("Temperature: 25 °C");
        tempSlider = new JSlider(20,100,25);
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

        note = new JLabel("Note: data may not be accurate");
        bottomPanel.add(note);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void Listener(){
        // 底物浓度滑块slider事件监听
        substrateSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                substrate = substrateSlider.getValue();
                substrateLabel.setText("Substrate Concentration [S]: " + substrate); // 更新底物浓度标签显示

            }
        });

        //pH 滑块slider的事件监听
        pHSlider.addChangeListener(e -> {
            pH = pHSlider.getValue(); // 转换为 0.0-14.0
            pHLabel.setText(String.format("pH: " + pH));

//            if (pH < 3 || pH > 10) {
//                JOptionPane.showMessageDialog(frame,
//                        "警告：极端 pH 值（<3 或 >10）可能导致酶失活！",
//                        "pH 警告",
//                        JOptionPane.WARNING_MESSAGE);
//            }
        });

        // 温度滑块slider的事件监听
        tempSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temp = tempSlider.getValue();
                tempLabel.setText("Temperature: " + temp + " °C"); // 更新标签显示

//                // 检测高温变性
//                if (temp > 80) {
//                    JOptionPane.showMessageDialog(frame,
//                            "警告：温度超过80°C，酶将变性！",
//                            "高温警告",
//                            JOptionPane.WARNING_MESSAGE);
//                }
            }
        });
    }

}
