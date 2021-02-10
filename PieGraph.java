//package pielabelgraph;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
/*
   Invokes all the other classes
   such as PieTabel_panel, Pie_panel and
   acts as main UI window.
*/
public class PieGraph extends JFrame
{

   int width,height;
   JPanel lower_panel;
   PieTable_panel table_panel;
   Pie_panel graph_panel;
   JButton reset_button,draw_button,color_button;
   static PieGraph ownObject;
   public PieGraph()
   {
      super("Pie Graph");

      addWindowListener(new PieWindowHandler(this));
      Image img =Toolkit.getDefaultToolkit().getImage("Dr.png");
      setIconImage(img);
      setLayout(null);
      setResizable(false);
      width = 1024;
      height = 768;
      setLocation(0,0);
      setSize(width,height);
      setVisible(true);

      graph_panel = new Pie_panel((width-300),(height-200));
      add(graph_panel);
      graph_panel.setBounds(0,0,(width-300),(height-200));


      table_panel = new PieTable_panel(300,(height-200),this);
      add(table_panel);
      table_panel.setBounds((width-300),0,300,(height-200));
      table_panel.setBackground(new Color(100,110,200));


      lower_panel = new JPanel();
	  add(lower_panel);
	  lower_panel.setBounds(0,(height-200),width,200);
      lower_panel.setLayout(null);
      lower_panel.setBackground(new Color(150,150,150));
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

         color_Pie.passPieGraphToCW(this);


        reset_button.addActionListener(new UnitButtonsAct(this,table_panel,graph_panel));
        draw_button.addActionListener(new UnitButtonsAct(this,table_panel,graph_panel));
        color_button.addActionListener(new UnitButtonsAct(this));

        ownObject = this;
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
   }
   static PieGraph getObject()
   {
	   return(ownObject);
   }

 }
/*
   This class uses details of Table class,
   processes and calculates all data entered
   in table, and finally plots a graph if
   possible to draw.
*/
class Pie_panel extends JPanel
{
	int drawGraphFLAG;
	int width,height;
	PieTable_panel T;
	int U=0,U5=0,LASTU5=0;
    Unit pielabel[] = new Unit[7];
    JButton pieButtons[] = new JButton[7];
    static Pie_panel GP;//own static object
    static Color background_color,pielabel_color,xdata_color;
    int total=0;

	Pie_panel(int w,int h)
	{

       setLayout(null);
       drawGraphFLAG = 0;

       GP=this;//to invoke repaint from static void refreshPieGraph() method

       width = w;//724
       height = h;//568
       setLayout(null);

       background_color = (new Color(205,205,255));
       pielabel_color = (new Color(100,0,0));
       xdata_color = (new Color(0,0,200));

       Color c = new Color(255,255,255);
       for(int i=0;i<7;i++)
       {

		   pieButtons[i] = new JButton();
		   pieButtons[i].setBackground(c);
		   pieButtons[i].addActionListener(new xdataColorAct(pieButtons[i]));
		   c = c.darker();
	   }

	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

        int tempY=(height-72)-8,count=0,ic=0;
        setBackground(background_color);
        for(count=0;count<10;count++)
        {
		   g.drawRect(0+count,0+count,width-(2*count),height-(2*count));
		}
		for(count=0;count<7;count++)
		{
			pieButtons[count].setVisible(false);
		}

        /*
          DRAWING PIE CHART FROM HERE
        */
        if(drawGraphFLAG==1)
        {
             int i;
             g.setFont(new Font("Gisha",Font.BOLD,20));
             g.setColor(Color.black);
             if(T.jtfX.getText().trim().length()>13)
             {
                g.drawString(T.jtfX.getText().trim().substring(0,12)+"...",500,60);
		     }
		     else
		     {
			    g.drawString(T.jtfX.getText().trim(),500,60);
			 }
			 g.setColor(pielabel_color);
			 if(T.jtfY.getText().trim().length()>25)
			 {
				g.drawString("Pie-Chart Representation of "+T.jtfY.getText().trim().substring(0,24)+"...",40,height-30);
			 }
			 else
			 {
				g.drawString("Pie-Chart Representation of "+T.jtfY.getText().trim(),40,height-30);
			 }
             g.setFont(new Font("Arial",Font.BOLD,15));
             g.setColor(xdata_color);
             int ty = 120;
             for(i=0;i<7;i++)
             {

                   if(pielabel[i].existFLAG==1)
                   {
                        pieButtons[i].setVisible(true);
                        add(pieButtons[i]);
                        g.drawString(T.jtfx[i].getText().trim().length()>10?
                                     T.jtfx[i].getText().trim().substring(0,10)+"...":
                                     T.jtfx[i].getText().trim(),560,ty);
				   }
				   ty += 50;
			 }
			 for(i=0;i<7;i++)
			 {
				 if(pielabel[i].existFLAG==1)
				 {
                    g.setColor(pieButtons[i].getBackground());
                    g.fillArc(33,83,394,394,pielabel[i].startangle,pielabel[i].endangle);
				 }
			 }

		}

    }
    void resetGraph()
    {
          drawGraphFLAG = 0;
          Color c = new Color(255,255,255);
          T.setTotal(0);
          for(int i=0;i<7;i++)
          {
			pieButtons[i].setBackground(c);
			c = c.darker();
		  }
          repaint();
	}
    void drawGraph(PieTable_panel t)
    {
       total=0;
       int i;
		T = t;
       refreshTable();
       refreshTable();

       color_Pie.passPieTable_panel(T);

       drawGraphFLAG = 1;
       int ty=100;
       for(i=0;i<7;i++)
       {
		   pielabel[i] = new Unit();

		   if(T.getjtfy(T.jtfy[i])!=0)
		   {
               pielabel[i].existFLAG=1;
               pielabel[i].startangle = 0 ;
               pielabel[i].endangle = 0;
               pielabel[i].value = T.getjtfy(T.jtfy[i]);
               total += pielabel[i].value;
               pieButtons[i].setBounds(500,ty,50,30);
		   }
		   ty += 50;
	   }
	   T.setTotal(total);
	   int angle=0;
	   int m,ang=0,prevStartAng=0;
	   for(i=0;i<7;i++)
	   {
            if(pielabel[i].existFLAG==1)
            {
				m = (pielabel[i].value*360);
				angle = m/total;

				pielabel[i].startangle = prevStartAng + ang;
				prevStartAng = pielabel[i].startangle;
				ang = ++angle;

				pielabel[i].endangle = angle;
			}
	   }

        repaint();
	}
	void refreshTable()
	{
		for(int i=0;i<7;i++)
		{
			if(T.getjtfy(T.jtfy[i])==0 || T.jtfx[i].getText().trim().equals(""))
			{
               T.jtfx[i].setText(" ");
               T.jtfy[i].setText(" ");

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
	static void refreshPieGraphPanel()
	{
		GP.repaint();
	}
}
/*  This class holds details of each Unit
    and helps  Graph 'drawGraph()' method
    to plot Pie Graph.
*/
class Unit
{
	int existFLAG;
    int value,startangle,endangle;
	Unit()
	{
		existFLAG = 0;
	}
}
/*
    This class contains all table panel
    module.
*/
class PieTable_panel extends JPanel
{

	PieGraph F;
	int width,height, rows;
    JTextField jtfX,jtfY;
    public JTextField jtfx[] = new JTextField[7];
    public JTextField jtfy[] = new JTextField[7];
    static int invalidFLAG;
    int total=0;
	PieTable_panel(int width,int height,PieGraph f)
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

        g.setFont(new Font("System",Font.BOLD,16));
        g.setColor(Color.black);
        g.drawLine(0,77,width,77);
        g.drawLine(0,80,width,80);
        g.setColor(new Color(50,50,85));
        g.drawString("Parameters",30,100);
        g.drawString("Data Values",180,100);
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

        if(total!=0)
        {
			g.drawString("Total ",20,490);
			g.drawLine((width/2),458,(width/2),510);
			g.drawString(""+total,(width/2)+15,490);
			g.drawLine(0,510,width,510);
			g.drawLine(0,513,width,513);
		}
	}
	void setTotal(int tot)
	{
		total = tot;
		repaint();
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
class UnitButtonsAct implements ActionListener
{
    PieGraph F;
    PieTable_panel T;
    Pie_panel G;
    color_Pie CW;
    UnitButtonsAct(PieGraph f,PieTable_panel t,Pie_panel g)
    {
		F = f;
		T = t;
		G = g;
	}
	UnitButtonsAct(PieGraph f)
	{
		F = f;
	}
	UnitButtonsAct(Pie_panel g)
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
            CW = new color_Pie();
		}

	}
}
class color_Pie extends JDialog implements ActionListener
{
    static int existFLAG;
	JButton background,pielabel,xdata,other;//Panel P1 buttons
	JButton reset,ok;//Panel P2 buttons
	Color background_color,pielabel_color,xdata_color;
	static PieGraph F;
	static PieTable_panel T;

	color_Pie()
	{
		super(F);
		if(existFLAG==0)
		{
		existFLAG=1;
		addWindowListener(new PieWindowHandler(this));
		setLayout(new BorderLayout());
		setLocation(100,100);
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Panel P1 = new Panel();
		P1.setLayout(new GridLayout(2,2));

		background = new JButton("Background");
	    background.setFont(new Font("mv boli",Font.BOLD,25));

		pielabel = new JButton("Pie Label");
		pielabel.setFont(new Font("mv boli",Font.BOLD,25));

		xdata = new JButton("Parameters");
		xdata.setFont(new Font("mv boli",Font.BOLD,25));

		other = new JButton("Other");
		other.setFont(new Font("mv boli",Font.BOLD,25));

		background.setForeground(Color.white);
		pielabel.setForeground(Color.white);
		xdata.setForeground(Color.white);

		P1.add(background);
		P1.add(pielabel);
		P1.add(xdata);
        P1.add(other);

		add(P1,BorderLayout.CENTER);

		Panel P2 = new Panel();
		P2.setLayout(new FlowLayout());
		P2.setBackground(new Color(200,190,190));

		reset = new JButton("Reset");

		ok = new JButton("Ok");

		P2.add(reset);
		P2.add(ok);

		add(P2,BorderLayout.SOUTH);

        background.addActionListener(this);
        pielabel.addActionListener(this);
        xdata.addActionListener(this);
        other.addActionListener(this);
        reset.addActionListener(this);
        ok.addActionListener(this);
		setVisible(true);

	      saveColors();
		  setColors();

	    }
	}
	public void actionPerformed(ActionEvent ae)
	{
        if(ae.getSource()==background)
        {
           this.setVisible(false);
           Pie_panel.background_color = selectOneColor(Pie_panel.background_color);
           background.setBackground(Pie_panel.background_color);
           Pie_panel.refreshPieGraphPanel();
           this.setVisible(true);
		}
		else if(ae.getSource()==pielabel)
		{
		   this.setVisible(false);
		   Pie_panel.pielabel_color = selectOneColor(Pie_panel.pielabel_color);
		   pielabel.setBackground(Pie_panel.pielabel_color);
		   Pie_panel.refreshPieGraphPanel();
		   this.setVisible(true);
		}
		else if(ae.getSource()==xdata)
		{
			this.setVisible(false);
			Pie_panel.xdata_color = selectOneColor(Pie_panel.xdata_color);
			xdata.setBackground(Pie_panel.xdata_color);
			Pie_panel.refreshPieGraphPanel();
			this.setVisible(true);
		}
		else if(ae.getSource()==other)
		{
			String s1,s2;
			s1 = "Colors in the Pie Chart can be changed by\nclicking the color boxes of parameters.";
            s2 = "\n\nBefore this, You must enter data.";
			JOptionPane.showMessageDialog(null,s1+s2);
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
    void saveColors()//use in constructor to save colors for this color_Pie [JDialog]
    {
		background_color = Pie_panel.background_color;
		pielabel_color = Pie_panel.pielabel_color;
		xdata_color = Pie_panel.xdata_color;
	}
	void setColors()//use in constructor to set colors for this color_Pie [JDialog]
	{
		background.setBackground(background_color);
		pielabel.setBackground(pielabel_color);
		xdata.setBackground(xdata_color);
        other.setBackground(null);
		reset.setBackground(Color.gray);
		ok.setBackground(Color.gray);

	}
	void resetColors()
	{
		background_color = new Color(205,205,255);
		pielabel_color = new Color(100,0,0);
		xdata_color = new Color(0,0,200);

		background.setBackground(background_color);
		pielabel.setBackground(pielabel_color);
		xdata.setBackground(xdata_color);

		reset.setBackground(Color.gray);
		ok.setBackground(Color.gray);

		Pie_panel.background_color = background_color;
		Pie_panel.pielabel_color = pielabel_color;
		Pie_panel.xdata_color = xdata_color;

		Pie_panel.refreshPieGraphPanel();

	}
	static void passPieGraphToCW(PieGraph f)//PieGraph F is to pass to this.super(F)
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
	static void passPieTable_panel(PieTable_panel t)
	{
		T = t;
	}
}
class PieWindowHandler extends WindowAdapter
{
	color_Pie CW;
	PieGraph F;
	PieWindowHandler(color_Pie cw)
	{
		CW = cw;
	}
	PieWindowHandler(PieGraph f)
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
		    ,"Do you wants to close Unit Graph ?","Unit Graph",JOptionPane.YES_NO_OPTION))
		    {
                F.dispose();
		    }
		}
	}
}
class xdataColorAct implements ActionListener
{
    JButton B;
    xdataColorAct(JButton b)
    {
		B = b;
	}
	public void actionPerformed(ActionEvent ae)
	{
        if(ae.getSource()==B)
        {
          B.setBackground(selectOneColor(B.getBackground()));
          Pie_panel.refreshPieGraphPanel();
		}
	}
	Color selectOneColor(Color C)
	{
	   Color c = new Color(0);
	   try
	   {
	      c = JColorChooser.showDialog(PieGraph.getObject(),"Select Color",Color.blue);
       }
       catch(Exception e){}
       if(c==null)
       {
		   c = C;
	   }
	   return c;
	}

}
