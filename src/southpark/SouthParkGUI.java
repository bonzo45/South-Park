package southpark;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

class SouthParkGUI
{
  private int episode = 0;
  private final JLabel episodeLabel = new JLabel("", 0);
  JLabel southPark;
  JLabel sign;
  boolean day = true;
  
  public void createGUI()
  {
	URL resource = getClass().getResource("/SouthParkSmall.png");
	southPark = new JLabel(new ImageIcon(resource));
	sign = new JLabel(new ImageIcon(getClass().getResource("/blank.png")));
    this.southPark.addMouseListener(new RandomListener());
    
    JLayeredPane layers = new JLayeredPane();
    layers.setPreferredSize(new Dimension(800, 450));
    this.southPark.setBounds(0, 0, 800, 450);
    layers.add(this.southPark, new Integer(0));
    
    this.episodeLabel.setFont(new Font("Serif", 1, 50));
    this.episodeLabel.setBounds(0, 25, 150, 50);
    layers.add(this.episodeLabel, new Integer(1));
    this.sign.setBounds(390, 141, 283, 264);
    this.sign.addMouseListener(new WebListener());
    layers.add(this.sign, new Integer(2));
    
    JFrame frame = new JFrame("Random South Park Episode");
    frame.add(layers);
    frame.setSize(800, 450);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
  }
  
  class RandomListener
    implements MouseListener
  {
    private static final int EPISODE_COUNT = 257;
    
    RandomListener() {}
    
    public void mouseClicked(MouseEvent arg0) {}
    
    public void mouseEntered(MouseEvent arg0) {}
    
    public void mouseExited(MouseEvent arg0) {}
    
    public void mousePressed(MouseEvent arg0)
    {
      Random rand = new Random();
      SouthParkGUI.this.episode = (rand.nextInt(EPISODE_COUNT) + 1);
      SouthParkGUI.this.episodeLabel.setText(Integer.toString(SouthParkGUI.this.episode));
      if (arg0.getButton() == 3) {
        if (SouthParkGUI.this.day)
        {
          SouthParkGUI.this.southPark.setIcon(new ImageIcon(getClass().getResource("/SouthParkNightSmall.png")));
          SouthParkGUI.this.episodeLabel.setForeground(Color.WHITE);
          SouthParkGUI.this.day = false;
        }
        else
        {
          SouthParkGUI.this.southPark.setIcon(new ImageIcon(getClass().getResource("/SouthParkSmall.png")));
          SouthParkGUI.this.episodeLabel.setForeground(Color.BLACK);
          SouthParkGUI.this.day = true;
        }
      }
    }
    
    public void mouseReleased(MouseEvent arg0) {}
  }
  
  class WebListener
    implements MouseListener
  {
    WebListener() {}
    
    public void mouseClicked(MouseEvent arg0)
    {
      String link = SouthParkGUI.this.episodeLabel.getText();
      link = "http://allsp.ch/l.php?id=e" + link;
      try
      {
        Desktop.getDesktop().browse(URI.create(link));
      }
      catch (IOException e)
      {
        System.err.println("Oh for goodness sake...");
        e.printStackTrace();
      }
    }
    
    public void mouseEntered(MouseEvent arg0)
    {
      if (SouthParkGUI.this.day) {
        SouthParkGUI.this.sign.setIcon(new ImageIcon(getClass().getResource("/Sign.png")));
      } else {
        SouthParkGUI.this.sign.setIcon(new ImageIcon(getClass().getResource("/SignNight.png")));
      }
    }
    
    public void mouseExited(MouseEvent arg0)
    {
      SouthParkGUI.this.sign.setIcon(new ImageIcon(getClass().getResource("/blank.png")));
    }
    
    public void mousePressed(MouseEvent arg0) {}
    
    public void mouseReleased(MouseEvent arg0) {}
  }
}
