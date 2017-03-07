package net.hdcx.view.notice;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.notice.listener.CancelBtnListener;
import net.hdcx.view.notice.listener.IndateFieldFocusListener;
import net.hdcx.view.notice.listener.PublishBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 发布公告对话框
 * Created by Kevin on 2017/2/26.
 */
public class NoticeDialog extends JDialog implements IWindow{
	private JLabel nameLabel = null;
	private JLabel publishLabel = null;
	private JLabel indateLabel = null;
	private JTextField indateField = null;
	private JTextField nameField = null;
	private JScrollPane contentScroll = null;
	private JTextArea contentArea = null;
	private JButton publishBtn = null;
	private JButton cancelBtn = null;
	private JPanel centerPanel = null;
	private Font labelFont = null;
	private Image image = null;

	public NoticeDialog(){}

	private void init(){
		this.setTitle("创协签到软件");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		nameLabel = new JLabel("发布者：");
		publishLabel = new JLabel("公告：");
		indateLabel = new JLabel("有效期：");
		nameField = new JTextField();
		indateField = new JTextField();
		contentArea = new JTextArea();
		contentScroll = new JScrollPane(contentArea);
		publishBtn = new JButton("发布");
		cancelBtn = new JButton("取消");
		centerPanel = new JPanel();
		labelFont = new Font("microsoft yahei",Font.PLAIN,20);
		image = new ImageIcon("res/images/publishpanel.jpg").getImage();
	}

	private void setAttribute(){
		nameLabel.setFont(labelFont);
		publishLabel.setFont(labelFont);
		indateLabel.setFont(labelFont);
		nameField.setFont(labelFont);
		indateField.setFont(labelFont);
		indateField.setColumns(3);
		nameField.setColumns(10);
		contentArea.setFont(labelFont);
		contentArea.setColumns(30);
		contentArea.setRows(5);
		publishBtn.setFont(new Font("Microsoft Yahei",Font.PLAIN,16));
		cancelBtn.setFont(new Font("Microsoft Yahei",Font.PLAIN,16));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		nameField.setMaximumSize(new Dimension(200, 30));
		indateField.setMaximumSize(new Dimension(50, 30));
		contentArea.setLineWrap(true);
		centerPanel.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.25), 0, 0, 0));
		centerPanel.setOpaque(false);
		publishLabel.setVerticalAlignment(SwingConstants.TOP);
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
//		panel1.add(Box.createHorizontalGlue());
		panel1.add(Box.createHorizontalStrut(30));
		panel1.add(indateLabel);
		panel1.add(indateField);
		//单位
		JLabel date = new JLabel("天");
		date.setFont(labelFont);
		panel1.add(Box.createHorizontalStrut(10));
		panel1.add(date);
		panel1.add(Box.createHorizontalGlue());
		panel1.add(Box.createRigidArea(new Dimension(10,60)));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(publishLabel);
		panel2.add(Box.createVerticalGlue());

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(contentScroll);

		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(panel2);
		panel4.add(Box.createHorizontalStrut(20));
		panel4.add(panel3);

		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
		panel5.add(Box.createHorizontalGlue());
		panel5.add(publishBtn);
		panel5.add(Box.createHorizontalStrut(20));
		panel5.add(cancelBtn);

		backgroundPanel.add(centerPanel);
		this.add(backgroundPanel);
		centerPanel.add(panel1);
		centerPanel.add(panel4);
		centerPanel.add(Box.createVerticalStrut(20));
		centerPanel.add(panel5);
	}

	private void addListener(){
		publishBtn.addActionListener(new PublishBtnListener(nameField, indateField, contentArea));
		indateField.addFocusListener(new IndateFieldFocusListener(indateField));
		cancelBtn.addActionListener(new CancelBtnListener(this));
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
}
