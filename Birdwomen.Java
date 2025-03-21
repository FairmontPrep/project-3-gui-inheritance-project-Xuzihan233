import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

class Birdwoman extends JPanel {
    private BufferedImage characterImage;

    public Birdwoman() {
        loadCharacterImage(getRandomCharacterImage());
    }


    private static String getRandomCharacterImage() {
        Random rand = new Random();
        return rand.nextBoolean() ? "Birdwomen one.png" : "Birdwomen two.png"; 
    }

    private void loadCharacterImage(String filePath) {
        try {
            characterImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (characterImage != null) {
            g.drawImage(characterImage, 0, 0, this);
        }
    }
}


public class BirdwomanGUI extends JFrame {
    public BirdwomanGUI() {
        setTitle("Random Birdwomen");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        add(new Birdwoman(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BirdwomanGUI frame = new BirdwomanGUI();
            frame.setVisible(true);
        });
    }
}
