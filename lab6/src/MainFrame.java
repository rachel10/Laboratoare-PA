import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel=new ConfigPanel(this);
        this.add(configPanel, BorderLayout.NORTH);

        controlPanel=new ControlPanel(this);
        this.add(controlPanel,BorderLayout.SOUTH);

        canvas = new DrawingPanel(this);
        add(canvas, BorderLayout.CENTER);

        //invoke the layout manager
        pack();
    }

}
