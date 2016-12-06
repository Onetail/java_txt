import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import Extra.Font_color;



public class Main extends JFrame{

	private JLabel label;
	private JPanel panel;
	private JTextArea text;
	private JScrollPane sp;
	private JButton[] bt;
	private JRadioButton[] rb;
	private JPanel newpanel;
	private JMenu demo,demo2;
	String[] demo2_str = {"錯誤回報","作者郵件"};
	private JMenuItem[] demo2_item = new JMenuItem[demo2_str.length];
	
	Container con = getContentPane();
	JFileChooser chooser = new JFileChooser();
	
//	private JComboBox cb;
	private boolean panel_tf = false;
	
	File file;
	String[] str = {"新開","讀檔","存檔","另存","替代","背景"};
	String[] str_backcolor = {"白(預設)","藍海","火紅","蒼暗"};
	private Font_color font = new Font_color();
	private JMenuItem[] item =new JMenuItem[str.length]; 
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.setSize(960,840);
		main.setLocationRelativeTo(null);
	}
	
	public Main()
	{
		super("檔案修改器 ax1.1版");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bt = new JButton[str.length];
		label = new JLabel("請選擇檔案");
		panel = new JPanel();
		text = new JTextArea();
		sp = new JScrollPane(text);
		demo2 = new JMenu("Debug");
		demo = new JMenu("File");
		JMenuBar bar = new JMenuBar();
		bar.add(demo);
		bar.add(demo2);
		// menu demo 
		for(int z = 0 ; z < demo2_str.length;z++)
		{
			demo2_item[z] = new JMenuItem(demo2_str[z]);
			demo2_item[z].addActionListener(new SimpleActionListener());
			demo2_item[z].setFont(font.f[4]);
			demo2.add(demo2_item[z]);
			demo2.addSeparator();
			
		}
		
		for(int i = 0;i < str.length ; i++)
		{

			item[i] = new JMenuItem(str[i]);
			item[i].addActionListener(new SimpleActionListener());
			item[i].setFont(font.f[4]);
			demo.add(item[i]);
			demo.add(new JSeparator());
		}
		demo.setFont(font.f[5]);
		setJMenuBar(bar);
		// menu demo2
		
		
		
		
		label.setFont(font.f[2]);
		text.setFont(font.f[3]);
		
		rb = new JRadioButton[str_backcolor.length];
		
		newpanel = new JPanel(new GridLayout(5,1));
		for(int j = 0; j < rb.length;j++)
		{
			rb[j] = new JRadioButton();
			rb[j].setText(str_backcolor[j]);
			rb[j].addActionListener(new radiocontrol());
			rb[j].setFont(font.f[0]);
			newpanel.add(rb[j]);
		}
		rb[0].setSelected(true);
		newpanel.setVisible(false);
		add(newpanel,BorderLayout.EAST);

		
//		addWindowListener(new SampleWindowListener());
		
		add(label,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		
		
		setVisible(true);
		
	}
	class SimpleActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try
			{
				if(e.getSource()==item[0])
				// 新開檔案 空白
				{
					text.setText("");
					file = null;
				//-------------------------
				}else if(e.getSource()== item[1])
				{
				// 讀取檔案
					int res = chooser.showOpenDialog(con);
					if(res == JFileChooser.APPROVE_OPTION)
					{
						file = chooser.getSelectedFile();
						BufferedReader buf = new BufferedReader(new FileReader(file));
						text.read(buf, null);
						buf.close();    
					}
				}
				// -------------------------
				else if(e.getSource() == item[2])
				{
				// 儲存檔案
//					file = chooser.getSelectedFile();
					if(file != null)
					{
						int x = JOptionPane.showConfirmDialog(null,"你確定要儲存嗎???????? ", "標題",JOptionPane.YES_NO_OPTION,1);
						if(x == JOptionPane.YES_OPTION)
						{
							BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
							text.write(bufw);
							bufw.close();
							int z = JOptionPane.showConfirmDialog(null,"Success!", "Excellent",JOptionPane.OK_OPTION,1);
						}else if(x == JOptionPane.NO_OPTION)
						{
							return;
						}
						
					}else
					{
						if(text.getText().equals(""))
						{
							text.setText("你傻嗎?");
							return;
						}else
						{
							int res = chooser.showSaveDialog(con);
							if(res == JFileChooser.APPROVE_OPTION)
							{
								file = chooser.getSelectedFile();
								BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
								text.write(bufw);
								bufw.close();
							}
						}
						
					}
				// ---------------------------------
				}else if(e.getSource() == item[3])
				{
				// 另存新檔
					int res = chooser.showSaveDialog(con);
					if(res == JFileChooser.APPROVE_OPTION)
					{
						file = chooser.getSelectedFile();
						BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
						text.write(bufw);
						bufw.close();
					}
				}
				// 替代文字
				else if(e.getSource() == item[4])
				{
					
					
				}
				// -----------------------------------
				else if(e.getSource() == item[5])
				// 背景顏色替換
				{
					if(!panel_tf)
					{
						
						newpanel.setVisible(true);
						panel_tf = true;
					}else
					{
						newpanel.setVisible(false);
//						newpanel.removeAll();
//						remove(newpanel);
						panel_tf = false;
					}
					
					revalidate();
					repaint();
				}
				// -----------------------------------
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
		
	}
	class SampleWindowListener extends WindowAdapter
	{
		public void windowClosing(WindowAdapter e)
		{
			System.exit(0);
		}
	}
	class radiocontrol implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == rb[0])
			{
				clearB();
				rb[0].setSelected(true);
				getContentPane().setBackground(font.color[0]);
				panel.setBackground(font.color[1]);
				text.setBackground(font.color[2]);
				rb[0].setBackground(font.color[1]);
				rb[1].setBackground(font.color[1]);
				rb[2].setBackground(font.color[1]);
				rb[3].setBackground(font.color[1]);
				newpanel.setBackground(font.color[1]);
			}else if(e.getSource() == rb[1])
			{
				clearB();
				rb[1].setSelected(true);
				getContentPane().setBackground(font.color[3]);
				panel.setBackground(font.color[4]);
				text.setBackground(font.color[5]);
				rb[0].setBackground(font.color[4]);
				rb[1].setBackground(font.color[4]);
				rb[2].setBackground(font.color[4]);
				rb[3].setBackground(font.color[4]);
				newpanel.setBackground(font.color[4]);
			}else if(e.getSource() == rb[2])
			{
				clearB();
				rb[2].setSelected(true);
				getContentPane().setBackground(font.color[6]);
				panel.setBackground(font.color[7]);
				text.setBackground(font.color[8]);
				rb[0].setBackground(font.color[7]);
				rb[1].setBackground(font.color[7]);
				rb[2].setBackground(font.color[7]);
				rb[3].setBackground(font.color[7]);
				newpanel.setBackground(font.color[7]);
			}else if(e.getSource() == rb[3])
			{
				clearB();
				rb[3].setSelected(true);
				getContentPane().setBackground(font.color[9]);
				panel.setBackground(font.color[10]);
				text.setBackground(font.color[11]);
				text.setForeground(Color.white);
				label.setForeground(font.color[12]);
				rb[0].setBackground(font.color[10]);
				rb[1].setBackground(font.color[10]);
				rb[2].setBackground(font.color[10]);
				rb[3].setBackground(font.color[10]);
				newpanel.setBackground(font.color[10]);
			}
		}
		void clearB()
		{
			rb[0].setSelected(false);
			rb[1].setSelected(false);
			rb[2].setSelected(false);
			rb[3].setSelected(false);
			text.setForeground(Color.black);
			label.setForeground(Color.BLACK);
		}
		
	}


}
