package net.hdcx.view.checkin;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.Preprocessor;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.checkin.listener.CheckinBtnListener;
import net.hdcx.view.checkin.listener.WorkTimeBoxListener;
import net.hdcx.view.checkin.listener.WorkWeekBoxListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 签到对话框
 * Created by Kevin on 2017/2/26.
 */
public class CheckinDialog extends JDialog implements IWindow {
	private final String[] WORKTIME = {"第一班", "第二班", "第三班", "第四班", "第五班"};
	private final String[] WORKWEEK = {"星期一", "星期二", "星期三", "星期四", "星期五"};
	private JLabel nameLabel = null;
	private JLabel idLabel = null;
	private JLabel workTimeLabel = null;
	private JButton checkinBtn = null;
	private JButton cancelBtn = null;
	private Font labelFont = null;
	private JPanel centerPanel = null;
	private Image image = null;
	private static JComboBox<String> workTimeBox = null;
	private DefaultListModel<String> dlm;
	private static JList<String> nameList;
	private static JComboBox<String> workWeekBox;

	public CheckinDialog(){
		this.init();
		this.setAttribute();
		this.addComponent();
		this.addListener();
		Preprocessor.getInitCheckNameList();
		this.display();
	}

	public void init(){
		this.setTitle("创协签到软件");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		nameLabel = new JLabel("姓名：");
		idLabel = new JLabel("学号：");
		workTimeLabel = new JLabel("请选择班次：");
		checkinBtn = new JButton("签到");
		cancelBtn = new JButton("取消");
		labelFont = new Font("microsoft yahei", Font.PLAIN, 20);
		centerPanel = new JPanel();
		image = new ImageIcon(Class.class.getResource("/images/signinpanel.jpg")).getImage();
		workTimeBox = new JComboBox<>(WORKTIME);
		dlm = new DefaultListModel<>();
		nameList = new JList<>(dlm);
		workWeekBox = new JComboBox<>(WORKWEEK);
	}


	public void setAttribute(){
		nameLabel.setFont(labelFont);
		idLabel.setFont(labelFont);
		workTimeLabel.setFont(labelFont);
		checkinBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("microsoft yahei", Font.PLAIN, 16));
		centerPanel.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.25),0,0,0));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);
		nameLabel.setForeground(Color.BLACK);
		idLabel.setForeground(Color.BLACK);
		workTimeBox.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		nameList.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		workWeekBox.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
	}

	public void addComponent(){
		//辅助面板
		JPanel backgroundPanel = new BackgroundPanel(this, image);
		JPanel panel0 = new JPanel();
		panel0.setOpaque(false);
		panel0.setLayout(new GridLayout(0, 2));
		JPanel rigthPanel = new JPanel();
		rigthPanel.setOpaque(false);
		rigthPanel.setLayout(new BoxLayout(rigthPanel, BoxLayout.Y_AXIS));
		JPanel leftPanel = new JPanel();
		JScrollPane jsp = new JScrollPane(nameList);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(jsp);
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(Box.createHorizontalStrut(40));
		panel2.add(workTimeBox);
		panel2.add(Box.createHorizontalStrut(20));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.setOpaque(false);
		JPanel panel4 = new JPanel();
		panel4.setOpaque(false);
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(Box.createHorizontalStrut(40));
		panel4.add(workWeekBox);
		panel4.add(Box.createHorizontalStrut(20));
		panel3.add(panel4);
		panel3.add(Box.createVerticalStrut(30));
		panel3.add(panel2);
		rigthPanel.add(panel3);
		rigthPanel.add(Box.createVerticalStrut(160));
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(Box.createHorizontalStrut(40));
		panel1.add(checkinBtn);
		panel1.add(Box.createHorizontalStrut(40));
		panel1.add(cancelBtn);
		rigthPanel.add(panel1);
		panel0.add(leftPanel);
		panel0.add(rigthPanel);
		centerPanel.add(panel0);
		backgroundPanel.setOpaque(false);
		backgroundPanel.add(centerPanel);
		this.add(backgroundPanel);
	}

	public void addListener(){
		checkinBtn.addActionListener(new CheckinBtnListener(nameList, workTimeBox));
		workTimeBox.addItemListener(new WorkTimeBoxListener(workTimeBox, workWeekBox, dlm));
		workWeekBox.addItemListener(new WorkWeekBoxListener(workTimeBox, workWeekBox, dlm));
		cancelBtn.addActionListener(e -> this.setVisible(false));

	}

	public void display(){
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

	public static JList<String> getNameList() {
		return nameList;
	}

	public static JComboBox<String> getWorkWeekBox() {
		return workWeekBox;
	}

	public static JComboBox<String> getWorkTimeBox() {
		return workTimeBox;
	}
}
