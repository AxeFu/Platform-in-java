package Game;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Start extends JFrame {
    public Start() {
        super("Title");
        setBounds(0, 0, 1280, 720);
        setDefaultCloseOperation(3);
        Container container = getContentPane();
        container.add(new Game(this));
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Start::new);
    }
}
