import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {

    final MainFrame frame;
    private JLabel sidesLabel;
    private JLabel sizeLabel;
    private JSpinner sizeField;
    private JSpinner sidesField;
    private JComboBox colorCombo;
    private JLabel colorLabel;
    private JLabel shapeLabel;
    private JComboBox shapeCombo;
    public ConfigPanel(MainFrame frame){
        this.frame=frame;
        init();
    }

    private void init(){
        String[] shapes={"RegularPolygon","Star","Plus"};
        shapeLabel=new JLabel("Shape");
        shapeCombo=new JComboBox(shapes);
        shapeCombo.setSelectedIndex(1);

        sidesLabel=new JLabel("Number of sides");
        sidesField=new JSpinner(new SpinnerNumberModel(0,0,100,1));
        sidesField.setValue(6);
        sizeLabel=new JLabel("Size");
        sizeField=new JSpinner(new SpinnerNumberModel(10,1,1000,5));

        String[] colors={"Random","Red","Blue","Green","Black","Yellow","Pink"};
        colorLabel=new JLabel("Color");
        colorCombo=new JComboBox(colors);
        colorCombo.setSelectedIndex(1);

        add(shapeLabel);
        add(shapeCombo);
        add(sidesLabel);
        add(sidesField);
        add(sizeLabel);
        add(sizeField);
        add(colorLabel);
        add(colorCombo);
    }

    public String getColor(){
        return colorCombo.getSelectedItem().toString();
    }

    public String getShape(){ return shapeCombo.getSelectedItem().toString();}

    public Object getSizeValue(){
        return sizeField.getValue();
    }

    public Object getSidesNumber(){
        return sidesField.getValue();
    }
}
