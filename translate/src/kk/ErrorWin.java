package kk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 * 错误提示弹窗类
 */

public class ErrorWin extends JWindow implements Runnable{	
	private static final long serialVersionUID = 1L;
	private static final int winW = 200;
	private static final int winH = 80;
	private static final int xSub = 100;
	private static final int ySub = 40;
	JLabel jl;	
	JPanel jp;	
	
	public ErrorWin(String msg){
		// 获取默认工具包
		Toolkit tool = Toolkit.getDefaultToolkit();
		// 设置顶层显示
		this.setAlwaysOnTop(true);
		this.setSize(winW, winH);
		// 右移一位, 计算屏幕中心坐标
		int x = ((int) tool.getScreenSize().getWidth() >> 1) - xSub;
		int y = ((int) tool.getScreenSize().getHeight() >> 1) - ySub;
		// 设置居中显示
		this.setLocation(x, y);
		// 设置面板边界布局
		jp = new JPanel(new BorderLayout());
		// 创建一个标签对象
		jl = new JLabel(msg);
		jp.add(jl, BorderLayout.CENTER);
		this.add(jp);
		// 设置label文字居中
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		// 设置前景色
		jl.setForeground(Color.BLUE);
		// 设置背景色
		jp.setBackground(Color.orange);	
		// 开启线程(显示弹窗)
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			// 让弹窗显示5秒
			this.setVisible(true);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		// 释放弹窗
		this.dispose();
	}	
}

