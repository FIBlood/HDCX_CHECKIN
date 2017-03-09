package net.hdcx.view.login.listener;

import net.hdcx.view.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * 和“确定”按钮相关的监听器
 *  	1.监听鼠标点击“确定”按钮
 *   	2.监听用户输入完数据后的"Enter"按键监听
 * Created by Kevin on 2017/2/27.
 */
public class SureBtnListener implements ActionListener, KeyListener{

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public SureBtnListener(
			JFrame frame,
			JTextField usernameField,
			JPasswordField passwordField
	) {
		this.frame = frame;
		this.usernameField = usernameField;
		this.passwordField = passwordField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		check(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			check(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	private void check(AWTEvent e){
		String name = usernameField.getText();
		char[] password = passwordField.getPassword();
		String defaultUserName = getDefaultUserName();
		String defaultPassword = getDefaultPassword();
		if(name.equals(defaultUserName) && Arrays.equals(password, defaultPassword.toCharArray())){
			this.display();
		}else{
			JOptionPane.showMessageDialog(frame, "请输入正确的用户名和密码");
		}
	}

	private void display(){
		MainFrame mainFrame = new MainFrame();
		frame.setVisible(false);
		mainFrame.display();
	}

	private String getDefaultUserName(){
		Properties p = new Properties();
		try {
			InputStream is = new FileInputStream("res/properties/admin.properties");
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "读取信息发生错误");
		}
		String userName = p.getProperty("username");
		return userName;
	}

	private String getDefaultPassword(){
		Properties p = new Properties();
		try {
			InputStream is = new FileInputStream("res/properties/admin.properties");
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "读取信息发生错误");
		}
		String defaultPassword = p.getProperty("password");
		return defaultPassword;
	}
}
