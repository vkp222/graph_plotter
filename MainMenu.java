import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.net.URI;

class MyFrame extends JFrame implements ActionListener
{
	Container C;
	JPanel P1,P2,P3;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	JLabel L1,L2;
    Color Cr;
    Font F,F1,F2;
    ImageIcon I1,I2,I3,I4,I5,I6,I7,I8;
    Image Im3,Im4,Im5,Im6,Im7,Im8;
    Cursor cu;
	MyFrame()
	{
       C=this.getContentPane();
       C.setLayout(null);


       addWindowListener(new PQR(this));
       I1=new ImageIcon("download.jpg");
       I2=new ImageIcon("10.png");
       I3=new ImageIcon("H.png");
       Im3=I3.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
       I4=new ImageIcon("Dr.png");
       Im4=I4.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
       I5=new ImageIcon("hel.png");
       Im5=I5.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
       I6=new ImageIcon("abo.png");
       Im6=I6.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
       I7=new ImageIcon("ex.png");
       Im7=I7.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

       Cr=new Color(0,0,0);
       cu=new Cursor(Cursor.HAND_CURSOR);

       F=new Font("Courier new",Font.BOLD,18);
       F1=new Font("Algerian",Font.PLAIN,42);
       F2=new Font("Copperplate Gothic Bold",Font.BOLD,38);

       P1=new JPanel();
       P1.setLayout(new FlowLayout());
       P1.setBounds(0,0,1024,100);
       P1.setBackground(Cr);
       P2=new JPanel();
       P2.setLayout(new GridLayout(5,1));
       P2.setBounds(0,100,300,618);
       P2.setBackground(Cr);
       P3=new JPanel();
       P3.setLayout(null);
       P3.setBounds(300,100,724,618);
       P3.setBackground(Cr);

       b1=new JButton(new ImageIcon(Im3));
       b1.setContentAreaFilled(false);
       b1.setFont(F);
       b1.setBorderPainted(false);
       b1.setForeground(Color.white);
       b1.addActionListener(this);

       b2=new JButton(new ImageIcon(Im4));
       b2.setContentAreaFilled(false);
       b2.setFont(F);
       b2.setBorderPainted(false);
       b2.setForeground(Color.white);
       b2.addActionListener(this);

       b3=new JButton(new ImageIcon(Im5));
       b3.setContentAreaFilled(false);
       b3.setFont(F);
       b3.setBorderPainted(false);
       b3.setForeground(Color.white);
       b3.addActionListener(this);

       b4=new JButton(new ImageIcon(Im6));
       b4.setContentAreaFilled(false);
       b4.setFont(F);
       b4.setBorderPainted(false);
      b4.setForeground(Color.white);
       b4.addActionListener(this);

       b5=new JButton(new ImageIcon(Im7));
       b5.setContentAreaFilled(false);
       b5.setFont(F);
       b5.setBorderPainted(false);
       b5.setForeground(Color.white);
       b5.addActionListener(this);

       b6=new JButton(I1);
       MouseListener A=new MouseListener(){
		   public void mouseEntered(MouseEvent e)
		   {
			   b6.setBounds(150-25,250-25,150+50,150+50);
			   b6.setCursor(cu);
			   b6.setIcon(new ImageIcon("download2.jpg"));
		   }
		   public void mouseExited(MouseEvent e)
		   {
			   b6.setBounds(150,250,150,150);
			   b6.setIcon(I1);
		   }
		   public void mousePressed(MouseEvent e){}
		   public void mouseReleased(MouseEvent e){}
		   public void mouseClicked(MouseEvent e){}
	   };
	   b6.addMouseListener(A);
	   b6.addActionListener(this);

       b7=new JButton(I2);
       MouseListener AB=new MouseListener(){
	   		   public void mouseEntered(MouseEvent e)
	   		   {
	   			   b7.setBounds(400-25,250-25,150+50,150+50);
	   			   b7.setCursor(cu);
	   			   b7.setIcon(new ImageIcon("11.png"));
	   		   }
	   		   public void mouseExited(MouseEvent e)
	   		   {
	   			   b7.setBounds(400,250,150,150);
	   			   b7.setIcon(I2);
	   		   }
	   		   public void mousePressed(MouseEvent e){}
	   		   public void mouseReleased(MouseEvent e){}
	   		   public void mouseClicked(MouseEvent e){}
	   	   };
	   b7.addMouseListener(AB);
	   b7.addActionListener(this);

       L1=new JLabel(new ImageIcon("banner.jpg"));

       L2=new JLabel(new ImageIcon("bca.png"));
       L2.setForeground(new Color(0,0,0,80) );
       L2.setBounds(0,0,724,618);


       P2.add(b1);
       P2.add(b2);
       P2.add(b3);
       P2.add(b4);
       P2.add(b5);
       P3.add(b6);
       P3.add(b7);
       P1.add(L1);
       P3.add(L2);

       C.add(P1);
       C.add(P2);
       C.add(P3);
	}

	public void actionPerformed(ActionEvent e)
	{
            if(e.getSource()==b1)
            {
				L2.setVisible(true);
			 // L2.setText("GRAPH PLOTTER");
			  //L2.setBounds(250,300,400,50);
			  b6.setVisible(false);
			  b7.setVisible(false);

		    }
		    else if(e.getSource()==b2)
			{
				L2.setVisible(false);
				b6.setVisible(true);
				b6.setBounds(150,250,150,150);

				b7.setVisible(true);
				b7.setBounds(400,250,150,150);


			}
		    else if(e.getSource()==b3)//help button
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://www.graphplottertool.blogspot.com/"));
				}
				catch(Exception err){}

			}
		    else if(e.getSource()==b4)//about button
			{
				try
				{
					Desktop.getDesktop().browse(new URI("http://www.graphplottertool.blogspot.com/"));
				}
				catch(Exception err){}

			}
		    else if(e.getSource()==b5)//exit button
			{
              // Dia D=new Dia();
              // D.setVisible(true);
              int x=JOptionPane.showConfirmDialog(null,"Do you want to exit ?","Main Menu",JOptionPane.YES_NO_OPTION);
              if(x==JOptionPane.YES_OPTION)
                 System.exit(0);

			}
			else if(e.getSource()==b6)//bar graph
			{
		       new BarGraph();
		    }
		    else if(e.getSource()==b7)//pie chart
		    {
			   new PieGraph();
		   }
	}
}



class PQR extends WindowAdapter
{
	JFrame F;
	PQR(JFrame x)
	{
		F=x;
	}
	public void windowClosing(WindowEvent e)
	{
		 int x=JOptionPane.showConfirmDialog(F,"Do you want to exit ?","Main Menu",JOptionPane.YES_NO_OPTION);
		 if(x==JOptionPane.YES_OPTION)
            System.exit(0);

	}
}
class MainMenu
{
	public static void main(String args[])
	{
		MyFrame M=new MyFrame();
		M.setTitle("Graph Plotter");
		M.setVisible(true);
		M.setBounds(0,0,1024,768);
		M.setResizable(false);
		M.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		ImageIcon I=new ImageIcon("G.jpg");
		M.setIconImage(I.getImage());
	}
}