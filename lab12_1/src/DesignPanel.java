import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DesignPanel extends JPanel {

    private JLabel label;
    private JButton button;
    final MainFrame frame;

    public DesignPanel(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        this.setPreferredSize(new Dimension(800,500));
        this.setBackground(Color.WHITE);
    }
    public void addComponent() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JComponent component=null;
        String name=frame.getControlPanel().getComponent();

        Class classOfComponent=Class.forName(name);
        if(name.equals("javax.swing.JComboBox")||name.equals("javax.swing.JList")){
            String[] items={"item1", "item2","item3"};

            Class[] signature = new Class[] {Object[].class};
            Constructor construct = classOfComponent.getConstructor(signature);

            component = (JComponent) construct.newInstance((Object) items);
        }else {
            Constructor construct = classOfComponent.getConstructor(String.class);
            component = (JComponent) construct.newInstance(frame.getControlPanel().getText());
        }

        add(component);
        frame.setDesignPanel(this);
    }


}
