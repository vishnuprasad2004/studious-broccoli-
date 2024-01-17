import java.awt.*;
import javax.swing.JFrame;

public class GlassAWT extends JFrame {
    GlassAWT() {
        setVisible(true);
        setSize(500, 500);
        setUndecorated(true);
        // setBackground(new Color(10, 10, 20, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setLayout(new FlowLayout());
        add(new Label("Hello World"));
        setOpacity(0.3f);
        
    }

    public static void main(String[] args) {
        new GlassAWT();
    }
}