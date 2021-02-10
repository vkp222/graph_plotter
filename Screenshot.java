import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Screenshot
{

   Rectangle rect;
   BufferedImage img;
   Screenshot()//full screen rect
   {
	   rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	   takeScreenshot();
   }
   Screenshot(Rectangle r)
   {
        rect = r;
        takeScreenshot();
   }
   void takeScreenshot()
   {
        try
        {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    Robot robot = new Robot();
		    img = robot.createScreenCapture(rect);
		    Thread.sleep(1);
		}
		catch(Exception e){}

        JFileChooser ch = new JFileChooser();
        if(ch.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
        {
          System.out.println("Current Directory : "+ch.getCurrentDirectory());
          System.out.println("Selected File     : "+ch.getSelectedFile());
          try
          {
          ImageIO.write(img,"jpg",new File(ch.getSelectedFile()+".jpg"));
          JOptionPane.showMessageDialog(null,"Image successfully saved !");
	      }
	      catch(IOException e){}
        }
        else
        {
          System.out.println("No Selection");
        }


   }
   public static void main(String args[])
   {
     new Screenshot();
   }
}