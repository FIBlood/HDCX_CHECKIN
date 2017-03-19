package net.hdcx.view.login;

import net.hdcx.view.login.listener.SureBtnListener;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;

import javax.swing.*;
import java.awt.*;

/**
 * 登录界面
 * Created by Kevin on 2017/2/27.
 */
public class LoginFrame extends JFrame {
	private JTextField usernameField = null;
	private JPasswordField passwordField = null;
	private JLabel usernameLabel = null;
	private JLabel passwordLabel = null;
	private JButton sureBtn = null;
	private JButton cancelBtn = null;
	private Image image = null;
	private Font labelFont = null;
	private JPanel centerPanel = null;

	public LoginFrame(){
		this.setTitle("黑大创协签到系统");
	}

	public LoginFrame(String title){
		super(title);
	}

	private void init(){
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		usernameLabel = new JLabel("管理员：");
		passwordLabel = new JLabel("密码：");
		sureBtn = new JButton("确定");
		labelFont = new Font("Microsoft Yahei",Font.PLAIN,20);
		cancelBtn = new JButton("取消");
		image = new ImageIcon(Class.class.getResource("/images/welcome.jpg")).getImage();
		centerPanel = new JPanel();
	}

	private void setAttribute(){
		usernameField.setColumns(10);
		passwordField.setColumns(10);
		usernameField.setFont(labelFont);
		passwordField.setFont(labelFont);
		usernameLabel.setFont(labelFont);
		passwordLabel.setFont(labelFont);
		sureBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);
		usernameLabel.setForeground(Color.BLACK);
		passwordLabel.setForeground(Color.BLACK);
	}

	private void addComponent(){
		//辅助面板
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel backgroundPanel = new BackgroundPanel(image);

		panel0.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		backgroundPanel.setOpaque(false);

		panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		panel0.add(usernameLabel);
		panel0.add(usernameField);

		panel1.add(passwordLabel);
		panel1.add(Box.createRigidArea(new Dimension(20, 0)));
		panel1.add(passwordField);

		panel2.add(Box.createHorizontalGlue());
		panel2.add(sureBtn);
		panel2.add(Box.createRigidArea(new Dimension(20, 0)));
		panel2.add(cancelBtn);

		this.getContentPane().add(backgroundPanel);
		backgroundPanel.add(centerPanel);
		centerPanel.add(Box.createVerticalStrut((int)(ScreenUtils.HEIGHT*0.25)));
		centerPanel.add(panel0);
		centerPanel.add(Box.createVerticalStrut(20));
		centerPanel.add(panel1);
		centerPanel.add(Box.createVerticalStrut(20));
		centerPanel.add(panel2);
	}

	private void addListener(){
		SureBtnListener sureBtnListener = new SureBtnListener(this, usernameField, passwordField);
		sureBtn.addActionListener(sureBtnListener);
		usernameField.addKeyListener(sureBtnListener);
		passwordField.addKeyListener(sureBtnListener);
		cancelBtn.addActionListener(e -> System.exit(0));
	}

	public void display(){
		init();
		setAttribute();
		addComponent();
		addListener();
		this.setSize(image.getWidth(this), image.getHeight(this));
		this.setLocationRelativeTo(null);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private class BackgroundPanel extends JPanel{
		private Image image;

		public BackgroundPanel(Image image){
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g){
			g.drawImage(image,0,0,this);
		}

	}
}
