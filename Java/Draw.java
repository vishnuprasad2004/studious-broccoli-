import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


class ColorButton extends Button implements ActionListener {
    ColorButton(String label) {
        super(label);
        setBounds(0,0,100,100);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button Clicked");
        int red = (int) (Math.floor(Math.random() * 255));
        int green = (int) (Math.floor(Math.random() * 255));
        int blue = (int) (Math.floor(Math.random() * 255));
        Graphics g = MyFrame.g;
        g.setColor(Color.CYAN);
    }
}

class MyFrame extends JFrame implements MouseMotionListener {
    
    MyFrame() {
        setTitle("Frame");
        setSize(300, 300);
        setLayout(null);
        setVisible(true);
        ColorButton b = new ColorButton("Button");
        add(b);
        addMouseMotionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

    }
    public static Graphics g;
    @Override
    public void mouseDragged(MouseEvent e) {       
        g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillOval(e.getX() - 20, e.getY() - 20, 40, 40);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("[" + e.getX() + ", " + e.getY() + "]");

        // int red = (int) (Math.floor(Math.random() * 255));
        // int green = (int) (Math.floor(Math.random() * 255));
        // int blue = (int) (Math.floor(Math.random() * 255));
        // Graphics g = getGraphics();
        // g.setColor(new Color(red, green, blue));
        // g.fillOval(e.getX() - 20, e.getY() - 20, 40, 40);


        g = getGraphics();
        g.setColor(Color.BLACK);
        g.fillOval(e.getX() - 20, e.getY() - 20, 40, 40);
    }


}


public class Draw {
    public static void main(String[] args) {
        new MyFrame();        
    }
}
