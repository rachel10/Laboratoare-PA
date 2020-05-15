import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ControlPanel controlPanel;
    private DesignPanel designPanel;

    public MainFrame(){
        super("Dynamic Swing Designer");
        this.setPreferredSize(new Dimension(800,600));
        init();
    }

    private void init(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        controlPanel=new ControlPanel(this);
        this.add(controlPanel, BorderLayout.NORTH);

        designPanel=new DesignPanel(this);
        this.add(designPanel,BorderLayout.CENTER);
        pack();
        this.setLayout(null);
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public DesignPanel getDesignPanel() {
        return designPanel;
    }
    public void setDesignPanel(DesignPanel designPanel){
        this.designPanel=designPanel;
        this.setVisible(true);
    }
}
