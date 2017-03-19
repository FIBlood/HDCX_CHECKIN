package net.hdcx.view.main.menu;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.main.listener.menulistener.AddBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

/**
 * 添加人员信息对话框
 * Created by Kevin on 2017/3/5.
 */
public class AddPeopleDialog extends JDialog implements IWindow{
	private JLabel idLabel = null;
	private JLabel nameLabel = null;
	private JLabel contactLabel = null;
	private JLabel emailLabel = null;
	private JLabel deptLabel = null;
	private JLabel workWeekLabel = null;
	private JLabel workTimeLabel = null;
	private JTextField idField = null;
	private JTextField nameField = null;
	private JTextField contactField = null;
	private JTextField emailField = null;
	private JTextField deptField = null;
	private JTextField workWeekField = null;
	private JTextField workTimeField = null;
	private JButton addBtn = null;
	private JButton cancelBtn = null;
	private Font font = null;
	private BackgroundPanel backgroundPanel;
	private String actionCommand = null;
	private Image image = null;
	private Map<String, String> actionMap;

	public AddPeopleDialog(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	public void init(){
		this.setTitle("录入信息");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		idLabel = new JLabel("学号：");
		idField = new JTextField();
		nameLabel = new JLabel("姓名：");
		nameField = new JTextField();
		contactLabel = new JLabel("联系电话：");
		contactField = new JTextField();
		emailLabel = new JLabel("邮件：");
		emailField = new JTextField();
		deptLabel = new JLabel("所在部门：");
		deptField = new JTextField();
		workWeekLabel = new JLabel("值班周期：");
		workWeekField = new JTextField();
		workTimeLabel = new JLabel("班次：");
		workTimeField = new JTextField();
		addBtn = new JButton("录入");
		cancelBtn = new JButton("取消");
		font = new Font("Microsoft yahei", Font.PLAIN, 20);
		image = new ImageIcon(Class.class.getResource("/images/addmemberpanel.jpg")).getImage();
		backgroundPanel = new BackgroundPanel(this, image);
		actionMap = new HashMap<>();
		actionMap.put("部员录入", "members");
		actionMap.put("部长录入", "ministers");
	}

	public void setAttribute(){
		idLabel.setFont(font);
		nameLabel.setFont(font);
		contactLabel.setFont(font);
		emailLabel.setFont(font);
		deptLabel.setFont(font);
		workWeekLabel.setFont(font);
		workTimeLabel.setFont(font);
		addBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));

		idField.setColumns(10);
		nameField.setColumns(10);
		contactField.setColumns(10);
		emailField.setColumns(10);
		deptField.setColumns(10);
		workWeekField.setColumns(10);
		workTimeField.setColumns(10);

		idField.setFont(font);
		nameField.setFont(font);
		contactField.setFont(font);
		emailField.setFont(font);
		deptField.setFont(font);
		workWeekField.setFont(font);
		workTimeField.setFont(font);

		backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

		idField.setMaximumSize(new Dimension(200, 30));
		nameField.setMaximumSize(new Dimension(200, 30));
		contactField.setMaximumSize(new Dimension(200, 30));
		emailField.setMaximumSize(new Dimension(200, 30));
		deptField.setMaximumSize(new Dimension(200, 30));
		workWeekField.setMaximumSize(new Dimension(200, 30));
		workTimeField.setMaximumSize(new Dimension(200, 30));

		backgroundPanel.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.28), 0, 0, 0));
	}

	public void addComponent(){
		//辅助面板
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel10 = new JPanel();

		panel0.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel4.setOpaque(false);
		panel6.setOpaque(false);
		panel7.setOpaque(false);
		panel8.setOpaque(false);
		panel10.setOpaque(false);

		panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));

		panel0.add(idLabel);
		panel0.add(Box.createRigidArea(new Dimension(40, 50)));
		panel0.add(idField);

		panel1.add(nameLabel);
		panel1.add(Box.createRigidArea(new Dimension(40, 50)));
		panel1.add(nameField);

		panel2.add(contactLabel);
		panel2.add(Box.createRigidArea(new Dimension(0, 50)));
		panel2.add(contactField);

		panel4.add(emailLabel);
		panel4.add(Box.createRigidArea(new Dimension(40, 50)));
		panel4.add(emailField);

		panel6.add(deptLabel);
		panel6.add(Box.createRigidArea(new Dimension(0, 50)));
		panel6.add(deptField);

		panel7.add(workWeekLabel);
		panel7.add(Box.createRigidArea(new Dimension(0, 50)));
		panel7.add(workWeekField);

		panel8.add(workTimeLabel);
		panel8.add(Box.createRigidArea(new Dimension(40, 50)));
		panel8.add(workTimeField);

		panel10.add(addBtn);
		panel10.add(Box.createRigidArea(new Dimension(20, 50)));
		panel10.add(cancelBtn);

		backgroundPanel.add(panel0);
		backgroundPanel.add(panel1);
		backgroundPanel.add(panel2);
		backgroundPanel.add(panel4);
		backgroundPanel.add(panel6);
		backgroundPanel.add(panel7);
		backgroundPanel.add(panel8);
		backgroundPanel.add(Box.createVerticalStrut(60));
		backgroundPanel.add(panel10);

		this.getContentPane().add(backgroundPanel);
	}

	private void addListener(){
		addBtn.addActionListener(new AddBtnListener(this, actionMap.get(actionCommand)));
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

	public JTextField getIdField() {
		return idField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getContactField() {
		return contactField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getDeptField() {
		return deptField;
	}

	public JTextField getWorkWeekField() {
		return workWeekField;
	}

	public JTextField getWorkTimeField() {
		return workTimeField;
	}
}
