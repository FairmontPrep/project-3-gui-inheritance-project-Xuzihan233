import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BirdwomanGUI extends JFrame {
    private BufferedImage characterImage;
    private String description;

    public BirdwomanGUI() {
        setTitle("Random Birdwoman");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        boolean isWhiteWings = Math.random() < 0.5;
        String imagePath = isWhiteWings ? "Birdwomen one.png" : "Birdwomen two.png";
        description = isWhiteWings ? "This woman has white wings" : "This woman has gold wings";


        loadCharacterImage(imagePath);
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

  
        g.setColor(Color.BLACK); 
        g.setFont(new Font("Arial", Font.BOLD, 18)); 
        g.drawString(description, 20, getHeight() - 20); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BirdwomanGUI frame = new BirdwomanGUI();
            frame.setVisible(true);
        });
    }
}
