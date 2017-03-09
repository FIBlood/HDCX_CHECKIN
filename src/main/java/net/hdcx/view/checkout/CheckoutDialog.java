package net.hdcx.view.checkout;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.checkout.listener.CheckoutBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 签退对话框
 * Created by Kevin on 2017/2/26.
 */
public class CheckoutDialog extends JDialog implements IWindow{
	private JLabel nameLabel = null;
	private JLabel IDLabel = null;
	private static JTextField nameField = null;
	private static JTextField idField = null;
	private JButton signOutBtn = null;
	private JButton cancelBtn = null;
	private Font labelFont = null;
	private JPanel centerPanel = null;
	private Image image = null;

	public CheckoutDialog(){

	}

	private void init(){
		this.setTitle("创协签到软件");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		nameLabel = new JLabel("姓名：");
		IDLabel = new JLabel("学号：");
		nameField = new JTextField();
		idField = new JTextField();
		signOutBtn = new JButton("确定");
		cancelBtn = new JButton("取消");
		labelFont = new Font("microsoft yahei", Font.PLAIN, 20);
		centerPanel = new JPanel();
		image = new ImageIcon("res/images/signoutpanel.jpg").getImage();
	}

	private void setAttribute(){
		nameLabel.setFont(labelFont);
		IDLabel.setFont(labelFont);
		nameField.setColumns(10);
		idField.setColumns(10);
		nameField.setFont(labelFont);
		idField.setFont(labelFont);
		signOutBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		centerPanel.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.3),0,0,0));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);
		nameLabel.setForeground(Color.BLACK);
		IDLabel.setForeground(Color.BLACK);
	}

	public void addComponent(){
		//辅助面板
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel backgroundPanel = new BackgroundPanel(this, image);

		panel0.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		backgroundPanel.setOpaque(false);

		panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		panel0.add(nameLabel);
		panel0.add(nameField);

		panel1.add(IDLabel);
		panel1.add(idField);

		panel2.add(Box.createHorizontalGlue());
		panel2.add(signOutBtn);
		panel2.add(Box.createRigidArea(new Dimension(20, 0)));
		panel2.add(cancelBtn);


		centerPanel.add(panel0);
		centerPanel.add(Box.createVerticalStrut(20));
		centerPanel.add(panel1);
		centerPanel.add(Box.createVerticalStrut(20));
		centerPanel.add(panel2);
		backgroundPanel.add(centerPanel);
		this.getContentPane().add(backgroundPanel);
	}

	private void addListener(){
		signOutBtn.addActionListener(new CheckoutBtnListener());
		cancelBtn.addActionListener(e -> this.setVisible(false));
	}

	public void display(){
		this.init();
		this.setAttribute();
		this.addComponent();
		this.addListener();
		this.setSize(image.getWidth(this), image.getHeight(this));
		ImageIconUtils.setDefaultImageIcon(this);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	private class BackgroundPanel extends JPanel{
		private ImageObserver observer;
		private Image image;
		public BackgroundPanel(ImageObserver observer, Image image){
			this.observer = observer;
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, observer);
		}
	}

	public static JTextField getNameField() {
		return nameField;
	}

	public static JTextField getIdField() {
		return idField;
	}
}
