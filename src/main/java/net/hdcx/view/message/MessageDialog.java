package net.hdcx.view.message;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.message.listener.SureBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 留言对话框
 * Created by Kevin on 2017/2/26.
 */
public class MessageDialog extends JDialog implements IWindow{

	private JLabel nameLabel = null;
	private JLabel IDLabel = null;
	private JLabel messageLabel = null;
	private static JTextField nameField = null;
	private static JTextField idField = null;
	private JScrollPane messageScroll = null;
	private static JTextArea messageArea = null;
	private Font labelFont = null;
	private JButton sureBtn = null;
	private JButton cancelBtn = null;
	private JPanel centerPanel = null;
	private Image image = null;

	public MessageDialog(){}

	private void init(){
		this.setTitle("创协签到软件");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		nameLabel = new JLabel("姓名：");
		IDLabel = new JLabel("学号：");
		messageLabel = new JLabel("留言：");
		nameField = new JTextField();
		idField = new JTextField();
		messageArea = new JTextArea();
		messageScroll = new JScrollPane(messageArea);
		labelFont = new Font("microsoft yahei", Font.PLAIN, 20);
		sureBtn = new JButton("确定");
		cancelBtn = new JButton("取消");;
		centerPanel = new JPanel();
		image = new ImageIcon(Class.class.getResource("/images/givemessage.jpg")).getImage();
	}

	private void setAttribute(){
		nameLabel.setFont(labelFont);
		IDLabel.setFont(labelFont);
		IDLabel.setForeground(Color.BLACK);
		messageLabel.setFont(labelFont);
		messageLabel.setForeground(Color.BLACK);
		nameField.setFont(labelFont);
		nameField.setColumns(10);
		nameField.setMaximumSize(new Dimension(200,30));
		idField.setFont(labelFont);
		idField.setColumns(10);
		idField.setMaximumSize(new Dimension(200,30));
		messageArea.setColumns(30);
		messageArea.setRows(5);
		sureBtn.setFont(new Font("Microsoft yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		centerPanel.setLayout(new GridLayout(0, 1, 0, 10));
		messageArea.setFont(labelFont);
		messageArea.setLineWrap(true);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.2), 0, 0, 0));
		centerPanel.setOpaque(false);
	}

	private void addComponent(){
		//辅助面板
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel backgroundPanel = new BackgroundPanel(this, image);

		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		panel4.setOpaque(false);
		panel5.setOpaque(false);
		backgroundPanel.setOpaque(false);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(nameLabel);
		panel1.add(nameField);
		panel1.add(Box.createHorizontalGlue());
		panel1.add(Box.createRigidArea(new Dimension(10,60)));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(IDLabel);
		panel2.add(idField);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(Box.createRigidArea(new Dimension(10,60)));


		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(Box.createRigidArea(new Dimension(0,60)));
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.add(messageLabel);
		panel4.add(Box.createVerticalGlue());
		panel3.add(panel4);
		panel3.add(messageScroll);

		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(Box.createHorizontalGlue());
		panel5.add(sureBtn);
		panel5.add(Box.createRigidArea(new Dimension(20, 60)));
		panel5.add(cancelBtn);


		backgroundPanel.add(centerPanel);
		this.getContentPane().add(backgroundPanel);
		centerPanel.add(panel1);
		centerPanel.add(panel2);
		centerPanel.add(panel3);
		centerPanel.add(panel5);
	}

	private void addListener(){
		sureBtn.addActionListener(new SureBtnListener());
		cancelBtn.addActionListener(e -> this.setVisible(false));
	}

	public void display(){
		init();
		setAttribute();
		addComponent();
		addListener();
		this.setSize(image.getWidth(this), image.getHeight(this));
		this.setLocationRelativeTo(null);
		ImageIconUtils.setDefaultImageIcon(this);
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

	public static JTextArea getMessageArea() {
		return messageArea;
	}
}
