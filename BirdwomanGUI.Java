import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

abstract class ImagePart {
    protected BufferedImage image;

    public ImagePart(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Failed to load: " + fileName);
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}

class Head extends ImagePart {
    public Head() {
        super("head.png");
    }
}

class Wings extends ImagePart {
    public Wings(boolean isGold) {
        super(isGold ? "wings_gold.png" : "wings_white.png");
    }
}

class Leg extends ImagePart {
    public Leg() {
        super("leg.png");
    }
}

class Foot extends ImagePart {
    public Foot() {
        super("foot.png");
    }
}

public class BirdwomanGUI extends JFrame {
    private BufferedImage characterImage;
    private String description;

    public BirdwomanGUI() {
        setTitle("Random Birdwoman");
        setSize(300, 500); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        composeCharacterImage();

        CharacterPanel panel = new CharacterPanel();
        add(panel, BorderLayout.CENTER);
    }

    private void composeCharacterImage() {
        Head head = new Head();
        Leg leg = new Leg();
        Foot foot = new Foot();

        boolean isGold = new Random().nextBoolean();
        Wings wings = new Wings(isGold);
        description = isGold ? "this woman has gold wings" : "this woman has white wings";

        int width = head.getImage().getWidth();
        int totalHeight = head.getImage().getHeight()
                          + wings.getImage().getHeight()
                          + leg.getImage().getHeight()
                          + foot.getImage().getHeight() + 40;

        characterImage = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = characterImage.getGraphics();

        int y = 0;
        g.drawImage(head.getImage(), 0, y, null); y += head.getImage().getHeight();
        g.drawImage(wings.getImage(), 0, y, null); y += wings.getImage().getHeight();
        g.drawImage(leg.getImage(), 0, y, null); y += leg.getImage().getHeight();
        g.drawImage(foot.getImage(), 0, y, null); y += foot.getImage().getHeight();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(description, 10, y + 20);
    }

    private class CharacterPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (characterImage != null) {
                Graphics2D g2d = (Graphics2D) g;

                double scale = 0.6;  
                int scaledWidth = (int) (characterImage.getWidth() * scale);
                int scaledHeight = (int) (characterImage.getHeight() * scale);
                int x = (getWidth() - scaledWidth) / 2;

                g2d.drawImage(characterImage, x, 0, scaledWidth, scaledHeight, null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BirdwomanGUI frame = new BirdwomanGUI();
            frame.setVisible(true);
        });
    }
}
