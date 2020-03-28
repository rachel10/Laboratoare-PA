import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
   final static int W=800,H=600;
   private BufferedImage image;
   private Graphics2D graphics;

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics=image.createGraphics();
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public DrawingPanel(MainFrame frame){
       this.frame=frame;
       createOffscreenImage();
       init();
   }

   private void createOffscreenImage(){
       image=new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
       graphics=image.createGraphics();
       graphics.setColor(Color.WHITE);
       graphics.fillRect(0,0,W,H);

    }
   private void init(){
       setPreferredSize(new Dimension(W,H));
       setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(),e.getY());
                repaint();
            }
        });
   }

   private void drawShape(int x, int y) {
       int radius = (int) frame.configPanel.getSizeValue();
       int sides = (int) frame.configPanel.getSidesNumber();
       Color color=Color.BLACK;
       if (frame.configPanel.getColor().equals("Random")) {
           int R = (int) (Math.random() * 256);
           int G = (int) (Math.random() * 256);
           int B = (int) (Math.random() * 256);
           color = new Color(R, G, B);
       }
       else{
           if(frame.configPanel.getColor().equals("Blue")){
               color=Color.BLUE;
           }else {
               if(frame.configPanel.getColor().equals("Black")){
                   color=Color.BLACK;
               }else {
                   if (frame.configPanel.getColor().equals("Red")){
                       color=Color.RED;
                   }else {
                       if (frame.configPanel.getColor().equals("Yellow")){
                           color=Color.YELLOW;
                       }
                       else {
                           if (frame.configPanel.getColor().equals("Green")){
                               color=Color.GREEN;
                           }else {
                               if (frame.configPanel.getColor().equals("Pink")){
                                   color=Color.PINK;
                               }
                           }
                       }
                   }
               }
           }
       }
       graphics.setColor(color);
       graphics.fill(new RegularPolygon(x, y, radius, sides));
   }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}
