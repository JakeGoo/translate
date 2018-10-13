package kk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BaseUI implements ActionListener
{
	public JTextArea input = null, output;
	JButton trans=new JButton("翻译");
	JButton en=new JButton("English");
	JButton ja=new JButton("Japanese");
	JButton fr=new JButton("French");
	JButton ch=new JButton("Chinese");
	JButton about;
	JPanel corePanel;
   	String nowFocusLg = null;
	Map<String, String> keyValue = new HashMap<String, String>();
		
    public BaseUI() throws IOException
    {
		JFrame jf = new JFrame();
		jf.setTitle("人家是女孩子啦~");	
		// 创建图标对象
		ImageIcon ico = new ImageIcon("rec/pbeiicon.jpg");
		ImageIcon bg = new ImageIcon("rec/bg.png");
		// 设置窗口图标
		jf.setIconImage(ico.getImage());	
		jf.setSize(850,720);
		// 禁用窗口大小
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		
		// 创建一个面板对象(东部)
		JPanel jp = new JPanel();
		// 创建一个面板对象(交互显示区域)
		corePanel = new JPanel();
		// 设置面板背景色
		jp.setBackground(new Color(255,228,181));
		// 将此面板以边界布局方式添加到窗口东部
		jf.add(jp,BorderLayout.EAST);
		java.awt.FlowLayout fl=new java.awt.FlowLayout(20,20,55);
		jp.setLayout(fl);
		Dimension di=new Dimension(160,0);
		// 设置面板的合适大小
		jp.setPreferredSize(di);
		
		//设置按钮属性位置
		trans.setFocusPainted(false); 
		trans.setBackground(new Color(255,240,245));
		trans.setForeground(Color.RED); 
		trans.setFont(new Font("黑体",Font.ITALIC,23)); 

		Dimension di1=new Dimension(120,60);
		// 设置此按钮的合适大小
		trans.setPreferredSize(di1);
		jp.add(trans);
				
		en.setBackground(new Color(185,160,220));
		en.setFont(new Font("Arial",Font.ITALIC,20));
		Dimension di2=new Dimension(120,60);
		en.setPreferredSize(di2);
		jp.add(en);
				
		ja.setBackground(Color.GREEN);
		ja.setFont(new Font("Arial",Font.ITALIC,20));
		Dimension di3=new Dimension(120,60);
		ja.setPreferredSize(di3);
		jp.add(ja);
			
		fr.setBackground(Color.cyan);
		fr.setFont(new Font("Arial",Font.ITALIC,20));
		Dimension di4=new Dimension(120,60);
		fr.setPreferredSize(di4);
		jp.add(fr);
				
		ch.setBackground(Color.ORANGE);
		ch.setFont(new Font("Arial",Font.ITALIC,20));
		Dimension di5=new Dimension(120,60);
		ch.setPreferredSize(di5);
		jp.add(ch);
		
		about = new JButton("关于");
		about.setBackground(Color.white);
		// 不聚焦中心
		about.setFocusPainted(false);
		Dimension di6=new Dimension(120,30);
		about.setPreferredSize(di6);
		jp.add(about);		
		
		input = new JTextArea();
		input.setLineWrap(true);
		input.setBounds(50, 40, 590, 280);
		input.setBackground(new Color(176, 196, 222));
		input.setSelectionColor(Color.PINK);
		corePanel.add(input);
		
		output = new JTextArea();
		output.setEditable(false);
		output.setLineWrap(true);
		output.setBounds(50, 370, 590, 280);
		output.setBackground(Color.PINK);
		corePanel.add(output);	
		
		// 设置布局管理为空
		corePanel.setLayout(null);
		JLabel bgLabel = new JLabel(bg);
		bgLabel.setBounds(0, 0, 690, 720);
		corePanel.add(bgLabel);
		
		jf.add(corePanel);
		jf.setVisible(true);
				
		trans.addActionListener(this);
		en.addActionListener(this);
		fr.addActionListener(this);
		ja.addActionListener(this);
		ch.addActionListener(this);
		about.addActionListener(this);
		
		// 放入每个按钮所对应的api语言代号
		keyValue.put("English", "en");
		keyValue.put("Japanese", "jp");
		keyValue.put("French", "fra");
		keyValue.put("Chinese", "zh");
    }
       

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == en || e.getSource() == fr 
    			|| e.getSource() == ja || e.getSource() == ch) {
    		// 获取响应时所对应的api语言代号
    		nowFocusLg = keyValue.get(e.getActionCommand());	
    	}
    	
    	if(e.getSource() == trans) {
    		// 获取待翻译的内容
    		String source = input.getText();
    		output.setText("");
    		try {
    			// 创建对象, 传递代号和文本
				new TransControl(nowFocusLg, source, output);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}  		
    	}    
    	
    	if(e.getSource() == about) {
    		String msg = "         "
    				+ "Zero @版权所有";
    		JOptionPane.showMessageDialog(null, msg, "关于", 1);
    	}
    }
}

