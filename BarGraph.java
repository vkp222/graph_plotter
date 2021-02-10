//package bargraph;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
/*
   Invokes all the other classes
   such as Tabel_panel, Graph_panel and
   acts as main UI window.
*/
public class BarGraph extends JFrame
{

   int width,height;
   JPanel lower_panel;
   Table_panel table_panel;
   Graph_panel graph_panel;
   JButton reset_button,draw_button,color_button;

   public BarGraph()
   {
      super("Bar Graph");

      addWindowListener(new windowHandler(this));
      Image img =Toolkit.getDefaultToolkit().getImage("Dr.png");
      setIconImage(img);
      setLayout(null);
      setResizable(false);
      width = 1024;
      height = 768;
      setLocation(0,0);
      setSize(width,height);
      setVisible(true);

      graph_panel = new Graph_panel((width-300),(height-200));
      add(graph_panel);
      graph_panel.setBounds(0,0,(width-300),(height-200));


      table_panel = new Table_panel(300,(height-200),this);
      add(table_panel);
      table_panel.setBounds((width-300),0,300,(height-200));
      table_panel.setBackground(new Color(145,170,180));


      lower_panel = new JPanel();
	  add(lower_panel);
	  lower_panel.setBounds(0,(height-200),width,200);
      lower_panel.setLayout(null);
      lower_panel.setBackground(new Color(100,100,100));
        reset_button = new JButton("Reset");
         reset_button.setBounds((width-450),40,150,90);
         reset_button.setFont(new Font("mv boli",Font.BOLD,25));
         reset_button.setBackground(Color.gray);
         reset_button.setForeground(Color.white);
         lower_panel.add(reset_button);

         draw_button = new JButton("Draw");
         draw_button.setBounds((width-280),40,250,90);
         draw_button.setFont(new Font("mv boli",Font.BOLD,25));
         draw_button.setBackground(Color.gray);
         draw_button.setForeground(Color.white);
         lower_panel.add(draw_button);


         color_button = new JButton("Colors");
         color_button.setBounds(25,40,150,90);
         color_button.setFont(new Font("mv boli",Font.BOLD,25));
         color_button.setBackground(Color.gray);
         color_button.setForeground(Color.white);
         lower_panel.add(color_button);

         color_Window.passBarGraphToCW(this);


         reset_button.addActionListener(new BarButtonsAct(this,table_panel,graph_panel));
         draw_button.addActionListener(new BarButtonsAct(this,table_panel,graph_panel));
         color_button.addActionListener(new BarButtonsAct(this));
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
   }
  public static void main(String args[])
  {
	  new BarGraph();
  }
}
/*
   This class uses details of Table class,
   processes and calculates all data entered
   in table, and finally plots a graph if
   possible to draw.
*/
class Graph_panel extends JPanel
{
	int drawGraphFLAG;
	int width,height;
	Table_panel T;
	int U=0,U5=0,LASTU5=0;
    Bar bar[] = new Bar[7];

    static Graph_panel GP;//own static object
    static Color background_color,bar_color,xdata_color,ydata_color;

	Graph_panel(int w,int h)
	{

       drawGraphFLAG = 0;

       GP=this;//to invoke repaint from static void refreshBarGraph() method

       width = w;//724
       height = h;//568
       setLayout(null);
       setBackground(new Color(250,230,140));

       background_color = (new Color(250,230,140));
       bar_color = (new Color(0,0,250));
       xdata_color = (new Color(0,0,0));
       ydata_color = (new Color(0,0,0));

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

        setBackground(background_color);

		g.drawRect(10,10,width-20,height-20);
		g.drawLine(100,56,100,(height-80));//Y-axis
        g.drawLine(85,(height-80),(width-50),(height-80));//X-axis
        g.setFont(new Font("Arial",Font.BOLD,12));
        g.setColor(new Color(185,122,87));

        g.drawString("Y",98,40);
        g.drawString("X",(width-40),(height-76));

        int tempY=(height-72)-8,count=0,ic=0;
        for(int i=1;count<10;i++)
        {
			ic++;
			tempY-=8;
			if(ic==5)
			{
			  g.setColor(Color.red);
			  g.drawLine(85,tempY,115,tempY);
			  ic=0;
			  count++;
			}
			else
			{
			  g.setColor(Color.black);
			  g.drawLine(95,tempY,105,tempY);
			}
		}
		/***************************************
		      Drawing Bar Graph From Here
		***************************************/
		tempY = 92;
		int n = LASTU5;
		if(drawGraphFLAG==1)
		{
			 g.setColor(Color.black);
             if(T.jtfX.getText().trim().length()>=15 && T.jtfY.getText().trim().length()<15)
		     {
			   g.drawString("X :  "+T.jtfX.getText().trim().substring(0,12)+"...",550,30);
			   g.drawString("Y :  "+T.jtfY.getText().trim(),550,50);
			 }
			 if(T.jtfX.getText().trim().length()<15 && T.jtfY.getText().trim().length()>=15)
			 {
			   g.drawString("X :  "+T.jtfX.getText().trim(),550,30);
			   g.drawString("Y :  "+T.jtfY.getText().trim().substring(0,12)+"...",550,50);
			 }
			 if(T.jtfX.getText().trim().length()>=15 && T.jtfY.getText().trim().length()>=15)
			 {
			   g.drawString("X :  "+T.jtfX.getText().trim().substring(0,12)+"...",550,30);
			   g.drawString("Y :  "+T.jtfY.getText().trim().substring(0,12)+"...",550,50);
			 }
			 if(T.jtfX.getText().trim().length()<15 && T.jtfY.getText().trim().length()<15)
			 {
			   g.drawString("X :  "+T.jtfX.getText().trim(),550,30);
			   g.drawString("Y :  "+T.jtfY.getText().trim(),550,50);
			 }

			 g.drawRect(530,15,179,40);

			 g.setColor(ydata_color);
             for(int i=1;i<=11;i++)//ydata printing loop
             {
				 g.drawString(Integer.toString(n),25,tempY);
                 n-=U5;
                 tempY+=40;
			 }
             for(int i=0;i<7;i++)
             {
				 if(bar[i].existFLAG==1)
				 {
					 g.setColor(bar_color);
					 g.fillRect(bar[i].x,bar[i].y,Bar.width,bar[i].height);

    			     g.setColor(ydata_color);
    			     g.drawString(""+T.getjtfy(T.jtfy[i]),bar[i].x,(bar[i].y)-5);

    			     g.setColor(xdata_color);
       			     g.drawString(T.jtfx[i].getText().trim().length()>=7?
       			                  T.jtfx[i].getText().trim().substring(0,6)+"...":
       			                  T.jtfx[i].getText().trim(),bar[i].x,(height-60));

				 }

			 }
	    }
	    /*  Bar Graph is Plotted */

	    /* repainting scale */
	    tempY=(height-72)-8;
	    count=0;
	    ic=0;
	    for(int i=1;count<10;i++)
		        {
					ic++;
					tempY-=8;
					if(ic==5)
					{
					  g.setColor(Color.red);
					  g.drawLine(85,tempY,115,tempY);
					  ic=0;
					  count++;
					}
					else
					{
					  g.setColor(Color.black);
					  g.drawLine(95,tempY,105,tempY);
					}
		}

    }
    void resetGraph()
    {
          drawGraphFLAG = 0;
          repaint();
	}
    void drawGraph(Table_panel t)
    {

		T = t;
		refreshTable();
		refreshTable();
       drawGraphFLAG = 1;

        int X=140,Y=0,HEIGHT=0;

        int distance = 20;
        Bar.width = 50;
        Bar.color = new Color(0,0,255);


        int MAX = T.getMaxjtfy(), loopFLAG=0;
        LASTU5 = MAX;
        while(true)
        {
			if(LASTU5%10==0)
			{
				U5 = LASTU5/10;
				if(U5%5==0)
				{
					U = U5/5;
					loopFLAG = 1;
				}
			}
			if(loopFLAG==1)
			{
				break;
			}
			LASTU5++;
		}
        loopFLAG = 0;
        int i;
        for(i=0;i<7;i++)
        {
			bar[i] = new Bar();
			if(T.getjtfy(T.jtfy[i])!=0)
			{
				bar[i].existFLAG=1;

				int u=U;
				int val = T.getjtfy(T.jtfy[i]);
				HEIGHT = 0;

				for(int j=1;j<=50;j++)
				{

				   if(val==u)
				   {
                      HEIGHT+=8;
                      Y = (height-80)-HEIGHT;
                      bar[i].setValues(X,Y,HEIGHT);
                      loopFLAG=1;
				   }
				   else if(val<u)
				   {
					   int prevU = u-U;
					   if((val-prevU)<(u-val))
					   {
						   HEIGHT+=3;
					   }
					   else
					   {
						   HEIGHT+=6;
					   }
					   Y = (height-80)-HEIGHT;
					   bar[i].setValues(X,Y,HEIGHT);
					   loopFLAG=1;
				   }
				   else
				   {
					   u+=U;
					   HEIGHT+=8;
				   }
				   if(loopFLAG==1)
				   {
					   break;
				   }

			    }

			}
		  X += (Bar.width+distance);
		  loopFLAG = 0;
		}

        repaint();
	}
	void refreshTable()
	{
		for(int i=0;i<7;i++)
		{
			if(T.getjtfy(T.jtfy[i])==0 || T.jtfx[i].getText().trim().equals(""))
			{
               for(int j=i;j<7;j++)
               {
				   if(T.getjtfy(T.jtfy[j])!=0)
				   {
					   T.jtfx[i].setText(T.jtfx[j].getText());
					   T.jtfy[i].setText(T.jtfy[j].getText());
					   T.jtfx[j].setText("");
					   T.jtfy[j].setText("");

					   break;
				   }
				   else
				   {
					   T.jtfx[j].setText("");
				   }

			   }
			}
		}
	}
    static void refreshBarGraphPanel()
	{
		GP.repaint();
	}
}
/*  This class holds details of each Bar
    and helps  Graph 'drawGraph()' method
    to plot Bar Graph.
*/
class Bar
{
	int existFLAG;
	int x,y,height;
    static int width;

    static Color color;
	Bar()
	{
		existFLAG = 0;
	}
	void setValues(int X,int Y,int Height)
	{
		x = X;
		y = Y;
		height = Height;
	}
}
/*
    This class contains all table panel
    module.
*/
class Table_panel extends JPanel
{

	BarGraph F;
	int width,height, rows;
    JTextField jtfX,jtfY;
    public JTextField jtfx[] = new JTextField[7];
    public JTextField jtfy[] = new JTextField[7];
    static int invalidFLAG;

	Table_panel(int width,int height,BarGraph f)
	{
		F = f;

	    rows = 0;
	    this.width = width;
	    this.height = height;
        int ty=170;
	    for(int i=0;i<7;i++)
	    {
			jtfX = new JTextField("");
			jtfY = new JTextField("");
			add(jtfX);
			add(jtfY);

			jtfx[i] = new JTextField("");
			jtfy[i] = new JTextField("");

     		jtfx[i].setBounds(5,ty,(width/2)-10,30);
     		jtfy[i].setBounds((width/2)+5,ty,width-10,30);
      		ty+=40;

     		add(jtfx[i]);
    		add(jtfy[i]);
		}

	    setLayout(null);
	}
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);

        g.setFont(new Font("Arial",1,15));
        g.setColor(Color.white);
        g.drawString("[ Parameters ]",25,40);
        g.drawString("[ Values ]",190,40);

        g.setFont(new Font("System",Font.BOLD,16));
        g.setColor(Color.black);
        g.drawLine(0,77,width,77);
        g.drawLine(0,80,width,80);
        g.setColor(new Color(50,50,85));
        g.drawString("X-Axis",50,100);
        g.drawString("Y-Axis",200,100);
        g.setColor(Color.black);
        g.drawLine(0,110,width,110);

        Font f1 = new Font("Calibri",Font.BOLD,22);


        jtfX.setBounds(5,120,(width/2)-10,30);
        jtfX.setFont(f1);
        jtfX.setBackground(new Color(180,180,180));
        jtfX.setForeground(Color.white);

        jtfY.setBounds((width/2)+5,120,width-10,30);
        jtfY.setFont(f1);
        jtfY.setBackground(new Color(180,180,180));
        jtfY.setForeground(Color.white);

        g.drawLine(0,160,width,160);

       Font f2 = new Font("Mangal",Font.ITALIC,18);
       int i;
       for(i=0;i<7;i++)
       {

               jtfx[i].setFont(f2);
               jtfy[i].setFont(f2);

               jtfx[i].setBackground(this.getBackground());
               jtfx[i].setForeground(Color.black);
               jtfy[i].setBackground(this.getBackground());
               jtfy[i].setForeground(Color.black);
       }

        g.drawLine((width/2),80,(width/2),455);

        g.drawLine(0,455,width,455);
        g.drawLine(0,458,width,458);
	}
	boolean jtfyIsValid(JTextField jtfy)
	{

	   return false;
	}
	boolean jtfyIsEmpty(JTextField jtfy)
	{
		if((jtfy.getText().trim()).equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	int getjtfy(JTextField jtfy)//returns -1 if invalid, 0 if empty, <value> if valid.
	{
		int v=-1;
		if(jtfyIsEmpty(jtfy))
		{
			v=0;
	    }
	    else
	    {
		  try
		  {
			String str = jtfy.getText();
			v = Integer.parseInt(str.trim());
		  }
	      catch(Exception e){}
	    }
		return v;
	}
	int getMaxjtfy()//returns 0 if table is reseted, -1 if invalid data, <max> if all data is valid
	{
		int max = 0;
		invalidFLAG = 0;

            for(int i=0;i<7;i++)
            {
    			if(getjtfy(jtfy[i])==-1)
    			{
    				invalidFLAG = 1;
    				max = -1;
    				jtfy[i].setForeground(Color.red);
    			}
     			else
     			{
    				jtfy[i].setForeground(Color.black);
     			}
            }

		if(invalidFLAG==0)
		{
			int i,n;
			for(i=0;i<7;i++)
			{
				if(!jtfyIsEmpty(jtfy[i]))//skips empty y-axis row
				{
					max = getjtfy(jtfy[i]);
				    break;
				}
			}
			for(int j=i+1;j<7;j++)
			{
			   if(!jtfyIsEmpty(jtfy[j]))//skips empty y-axis row
			   {
				   n = getjtfy(jtfy[j]);
				   if(max<n)
				   {
					 max = n;
				   }
			   }
		    }
		}
		return max;
	}
	void invalidDataWarning()
	{

		    	String str1,str2,str3;
		    	str1 = "Can't Process Entered Data.";
		    	str2 = "\n\nMake sure that everything is valid.";
		    	str3 = "\nYou may also prefer Online Help from Main Menu.";
		    	JOptionPane.showMessageDialog(null,str1+str2+str3);
                for(int i=0;i<7;i++)
                {
    	    		if(getjtfy(jtfy[i])==-1)
    	     		{
    	    			invalidFLAG = 1;
    	    			jtfy[i].setForeground(Color.red);
    	    		}
     	    		else
     	    		{
    	    			jtfy[i].setForeground(Color.black);
     	    		}
                }
	}
}
/*
  This class controls all buttons' operations
*/
class BarButtonsAct implements ActionListener
{
    BarGraph F;
    Table_panel T;
    Graph_panel G;
    color_Window CW;
    BarButtonsAct(BarGraph f,Table_panel t,Graph_panel g)
    {
		F = f;
		T = t;
		G = g;
	}
	BarButtonsAct(BarGraph f)
	{
		F = f;
	}
	BarButtonsAct(Graph_panel g)
	{
		G = g;
	}
    public void actionPerformed(ActionEvent ae)
    {
		if(ae.getSource()==F.draw_button)//draw button pressed
		{
           if(T.getMaxjtfy()==0)//empty table
           {
			   JOptionPane.showMessageDialog(null,"No data found to Process.");
		   }
		   else if(T.invalidFLAG==1)//invalid data
		   {
               T.invalidDataWarning();
           }
           else//valid data
           {
			  G.drawGraph(T);
		   }
		}
		else if(ae.getSource()==F.reset_button)//reset button pressed
		{
           T.jtfX.setText("");
           T.jtfY.setText("");
           for(int i=0;i<7;i++)
           {
			   T.jtfx[i].setText("");
			   T.jtfy[i].setText("");
		   }
		   G.resetGraph();
		}
		else if(ae.getSource()==F.color_button)//exit button pressed
		{
            CW = new color_Window();
		}

	}
}
class color_Window extends JDialog implements ActionListener
{
    static int existFLAG;
	JButton background,bar,xdata,ydata;//Panel P1 buttons
	JButton reset,ok;//Panel P2 buttons
	Color background_color,bar_color,xdata_color,ydata_color;
	static BarGraph F;
	color_Window()
	{
		super(F);
		if(existFLAG==0)
		{
		existFLAG=1;
		addWindowListener(new windowHandler(this));
		setLayout(new BorderLayout());
		setLocation(100,100);
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Panel P1 = new Panel();
		P1.setLayout(new GridLayout(2,2));

		background = new JButton("Background");
	    background.setFont(new Font("mv boli",Font.BOLD,25));
		background.addActionListener(this);

		bar = new JButton("Bar");
		bar.setFont(new Font("mv boli",Font.BOLD,25));
		bar.addActionListener(this);

		xdata = new JButton("X-Axis Data");
		xdata.setFont(new Font("mv boli",Font.BOLD,25));
		xdata.addActionListener(this);

		ydata = new JButton("Y-Axis Scale");
        ydata.setFont(new Font("mv boli",Font.BOLD,25));
        ydata.addActionListener(this);

		background.setForeground(Color.white);
		bar.setForeground(Color.white);
		xdata.setForeground(Color.white);
		ydata.setForeground(Color.white);

		P1.add(background);
		P1.add(bar);
		P1.add(xdata);
		P1.add(ydata);

		add(P1,BorderLayout.CENTER);

		Panel P2 = new Panel();
		P2.setLayout(new FlowLayout());
		P2.setBackground(new Color(200,190,190));

		reset = new JButton("Reset");
		reset.addActionListener(this);

		ok = new JButton("Ok");
        ok.addActionListener(this);

		P2.add(reset);
		P2.add(ok);

		add(P2,BorderLayout.SOUTH);
        saveColors();
        setColors();

		setVisible(true);
	    }
	}
	public void actionPerformed(ActionEvent ae)
	{
        if(ae.getSource()==background)
        {
           this.setVisible(false);
           Graph_panel.background_color = selectOneColor(Graph_panel.background_color);
           background.setBackground(Graph_panel.background_color);
           Graph_panel.refreshBarGraphPanel();
           this.setVisible(true);
		}
		else if(ae.getSource()==bar)
		{
		   this.setVisible(false);
		   Graph_panel.bar_color = selectOneColor(Graph_panel.bar_color);
		   bar.setBackground(Graph_panel.bar_color);
		   Graph_panel.refreshBarGraphPanel();
		   this.setVisible(true);
		}
		else if(ae.getSource()==xdata)
		{
			this.setVisible(false);
			Graph_panel.xdata_color = selectOneColor(Graph_panel.xdata_color);
			xdata.setBackground(Graph_panel.xdata_color);
			Graph_panel.refreshBarGraphPanel();
			this.setVisible(true);
		}
		else if(ae.getSource()==ydata)
		{
			this.setVisible(false);
			Graph_panel.ydata_color = selectOneColor(Graph_panel.ydata_color);
			ydata.setBackground(Graph_panel.ydata_color);
			Graph_panel.refreshBarGraphPanel();
			this.setVisible(true);
		}
		else if(ae.getSource()==reset)
		{
			resetColors();
		}
		else if(ae.getSource()==ok)
		{
			setVisible(false);
			existFLAG=0;
		}
	}
    void saveColors()//use in constructor to save colors for this color_Window [JDialog]
    {
		background_color = Graph_panel.background_color;
		bar_color = Graph_panel.bar_color;
		xdata_color = Graph_panel.xdata_color;
		ydata_color = Graph_panel.ydata_color;
	}
	void setColors()//use in constructor to set colors for this color_Window [JDialog]
	{
		background.setBackground(background_color);
		bar.setBackground(bar_color);
		xdata.setBackground(xdata_color);
		ydata.setBackground(ydata_color);

		reset.setBackground(Color.gray);
		ok.setBackground(Color.gray);

	}
	void resetColors()
	{
		background_color = new Color(250,230,140);
		bar_color = new Color(0,0,250);
		xdata_color = new Color(0,0,0);
		ydata_color = new Color(0,0,0);

		background.setBackground(background_color);
		bar.setBackground(bar_color);
		xdata.setBackground(xdata_color);
		ydata.setBackground(ydata_color);

		reset.setBackground(Color.gray);
		ok.setBackground(Color.gray);

		Graph_panel.background_color = background_color;
		Graph_panel.bar_color = bar_color;
		Graph_panel.xdata_color = xdata_color;
		Graph_panel.ydata_color = ydata_color;


		Graph_panel.refreshBarGraphPanel();

	}
	static void passBarGraphToCW(BarGraph f)//BarGraph F is to pass to this.super(F)
	{
		F = f;
	}
	Color selectOneColor(Color C)
	{
	   Color c = new Color(0);
	   try
	   {
	      c = JColorChooser.showDialog(this,"Select Color",Color.blue);
       }
       catch(Exception e){}
       if(c==null)
       {
		   c = C;
	   }
	   return c;
	}
}
class windowHandler extends WindowAdapter
{
	color_Window CW;
	BarGraph F;
	windowHandler(color_Window cw)
	{
		CW = cw;
	}
	windowHandler(BarGraph f)
	{
		F = f;
	}
	public void windowClosing(WindowEvent we)
	{
		if(we.getSource()==CW)
		{
          CW.existFLAG=0;
		}
		if(we.getSource()==F)
		{
		  if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(F
		    ,"Do you wants to close Bar Graph ?","Bar Graph",JOptionPane.YES_NO_OPTION))
		    {
                F.dispose();
		    }
		}
	}
}

