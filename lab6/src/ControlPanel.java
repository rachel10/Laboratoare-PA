import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.exit;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        saveBtn.addActionListener(this::save);
        exitBtn.addActionListener(this::exitt);
        resetBtn.addActionListener(this::reset);
        loadBtn.addActionListener(this::load);
    }

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.getImage(), "PNG", new FileOutputStream("e:/java/img.png"));
        } catch (IOException exception) {
            System.err.println(exception);
        }
    }

    private void exitt(ActionEvent e) {
        exit(-1);
    }

    private void reset(ActionEvent e) {
        Graphics2D graphics = frame.canvas.getImage().createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, frame.canvas.getW(), frame.canvas.getH());
    }

    private void load(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int choose = fileChooser.showOpenDialog(frame);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and jpg images", "png", "jpg");
        fileChooser.addChoosableFileFilter(filter);
        if (choose == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage image = ImageIO.read(new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath()));
                frame.canvas.setImage(image);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }


}
