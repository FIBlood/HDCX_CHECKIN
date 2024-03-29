package net.hdcx;

import net.hdcx.view.login.LoginFrame;

import javax.swing.*;

/**
 * 黑龙江大学学生创业协会---签到系统
 * @author 梁凯文  QQ: 401106891
 * @version 1.1.0
 * @since jdk 1.8
 * 最后修改于 2016/8/10
 * 最后修改人 梁凯文
 *  */
public class Checkin {
	//设置界面观感
	static{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.display();
		});
	}
}
