package net.hdcx.view.checkworktime;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.checkworktime.listener.SureBtnListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 根据班次和值班周期查找人员信息的对话框
 * Created by Kevin on 2017/2/26.
 */
public class CheckWorkTimeDialog extends JDialog implements IWindow{
	private ArrayList<String> ministerNameList = null;
	private ArrayList<String> memberNameList = null;
	private Vector columnName = null;
	private Font font = null;
	private JLabel weekdayLabel = null;
	private JLabel banciLabel = null;
	private JButton sureBtn = null;
	private JButton cancelBtn = null;
	private static JComboBox workWeekBox = null;
	private static JComboBox workTimeBox = null;

	public CheckWorkTimeDialog(){}

	private void init(){
		this.setTitle("查询值班信息");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		ministerNameList = new ArrayList<>();
		memberNameList = new ArrayList<>();
		columnName = new Vector();
		font = new Font("Microsoft Yahei", Font.PLAIN, 20);
		weekdayLabel = new JLabel("值班周期：");
		banciLabel = new JLabel("班次：");
		sureBtn = new JButton("确定");
		cancelBtn = new JButton("取消");
		Object[] weekday = {"星期一","星期二","星期三","星期四","星期五"};
		Object[] banci = {"第一班","第二班","第三班","第四班","第五班"};
		workWeekBox = new JComboBox(weekday);
		workTimeBox = new JComboBox(banci);
	}

	private void setAttribute(){
		weekdayLabel.setFont(font);
		banciLabel.setFont(font);
		sureBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		workWeekBox.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		workTimeBox.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		workWeekBox.setMaximumSize(new Dimension(100, 30));
		workTimeBox.setMaximumSize(new Dimension(100, 30));
	}

	private void addComponent(){
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel1.add(weekdayLabel);
		panel1.add(workWeekBox);
		panel2.add(banciLabel);
		panel2.add(Box.createHorizontalStrut(40));
		panel2.add(workTimeBox);
		panel3.add(sureBtn);
		panel3.add(Box.createHorizontalStrut(20));
		panel3.add(cancelBtn);
		panel0.add(Box.createVerticalStrut(20));
		panel0.add(panel1);
		panel0.add(panel2);
		panel0.add(panel3);
		this.getContentPane().add(panel0);
	}


	private void addListener(){
		sureBtn.addActionListener(new SureBtnListener(this));
		cancelBtn.addActionListener(e -> this.setVisible(false));
	}

	public void display(){
		init();
		setAttribute();
		addListener();
		addComponent();
		this.setSize((int)(ScreenUtils.WIDTH*0.2), (int)(ScreenUtils.HEIGHT*0.2));
		this.setLocationRelativeTo(null);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static JComboBox getWorkWeekBox() {
		return workWeekBox;
	}

	public static JComboBox getWorkTimeBox() {
		return workTimeBox;
	}
}
