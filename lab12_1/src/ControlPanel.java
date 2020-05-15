import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private MainFrame frame;
    private JComboBox componentsBox;
    private JButton addButton;
    private JTextField textField;
    public ControlPanel(MainFrame frame){
        this.frame=frame;
        init();
    }

    private void init(){
        String[] components={"javax.swing.JButton", "javax.swing.JLabel","javax.swing.JTextField","javax.swing.JList","javax.swing.JComboBox"};
        componentsBox=new JComboBox(components);
        add(componentsBox);
        textField=new JTextField("DefaultName");
        add(textField);
        textField.setPreferredSize(new Dimension(150,25));
        addButton=new JButton("Add");
        add(addButton);

        addButton.addActionListener(this::addC);
    }
    private void addC(ActionEvent e){
        try {
            frame.getDesignPanel().addComponent();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String getComponent() {
        return componentsBox.getSelectedItem().toString();
    }
    public String getText(){
        return textField.getText().toString();
    }
}
