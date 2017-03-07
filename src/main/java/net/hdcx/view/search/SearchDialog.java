package net.hdcx.view.search;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.search.listener.CancelBtnListener;
import net.hdcx.view.search.listener.SearchBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 搜索对话框
 * Created by Kevin on 2017/2/26.
 */
public class SearchDialog extends JDialog implements IWindow{
	private JLabel nameLabel = null;
	private JLabel IDLabel = null;
	private JTextField nameField = null;
	private JTextField idField = null;
	private JButton searchBtn = null;
	private JButton cancelBtn = null;
	private JPanel centerPanel = null;
	private Font labelFont = null;
	private Image image = null;

	public SearchDialog(){}

	private void init(){
		this.setTitle("创协签到软件");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		nameLabel = new JLabel("姓名：");
		IDLabel = new JLabel("学号：");
		nameField = new JTextField();
		idField = new JTextField();
		searchBtn = new JButton("搜索");
		cancelBtn = new JButton("取消");
		centerPanel = new JPanel();
		labelFont = new Font("microsoft yahei", Font.PLAIN, 20);
		image = new ImageIcon("res/images/searchpanel.jpg").getImage();
	}

	private void setAttribute(){
		nameLabel.setFont(labelFont);
		IDLabel.setFont(labelFont);
		nameField.setFont(labelFont);
		idField.setFont(labelFont);
		nameField.setColumns(10);
		idField.setColumns(10);
		nameField.setMaximumSize(new Dimension(200, 30));
		idField.setMaximumSize(new Dimension(200, 30));
		searchBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.3), 0, 0, 0));
		centerPanel.setOpaque(false);
	}

	private void addComponent(){
		//一堆辅助面板
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel backgroundPanel = new BackgroundPanel(this, image);

		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		backgroundPanel.setOpaque(false);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(nameLabel);
		panel1.add(Box.createHorizontalStrut(10));
		panel1.add(nameField);
		panel1.add(Box.createVerticalStrut(50));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(IDLabel);
		panel2.add(Box.createHorizontalStrut(10));
		panel2.add(idField);
		panel2.add(Box.createVerticalStrut(50));

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(Box.createHorizontalGlue());
		panel3.add(searchBtn);
		panel3.add(Box.createHorizontalStrut(20));
		panel3.add(cancelBtn);

		backgroundPanel.add(centerPanel);
		this.add(backgroundPanel);
		centerPanel.add(panel1);
		centerPanel.add(panel2);
		centerPanel.add(panel3);
	}

	private void addListener(){
		searchBtn.addActionListener(new SearchBtnListener(this, nameField, idField));
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
