package southpark;

import javax.swing.SwingUtilities;

public class SouthPark
{
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        SouthParkGUI gui = new SouthParkGUI();
        gui.createGUI();
      }
    });
  }
}
