package net.hdcx.view.main.menu;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.main.listener.menulistener.SaveBtnListener;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 管理设置对话框
 * Created by Kevin on 2017/3/5.
 */
public class ManageSettingDialog extends JDialog implements IWindow{
	private JPanel centerPanel;
	private JLabel[] banci;
	private JLabel[] hourLabel;
	private JLabel[] minuteLabel;
	private JLabel[] jiangeLabel;
	private JTextField[] startHourField;
	private JTextField[] startMinuteField;
	private JTextField[] endHourField;
	private JTextField[] endMinuteField;
	private JLabel lateLabel;
	private JLabel kuangLabel;
	private JTextField lateField;
	private JTextField kuangField ;
	private JButton saveBtn;
	private JButton cancelBtn;
	private Font font;
	private Properties p;

	public ManageSettingDialog(){}

	private void init(){
		this.setTitle("人事管理设置");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		centerPanel = new JPanel();
		lateLabel = new JLabel("延迟签到时间：");
		lateField = new JTextField();
		kuangLabel = new JLabel("超过多少分钟即旷班：");
		kuangField = new JTextField();
		saveBtn = new JButton("保存");
		cancelBtn = new JButton("取消");
		font = new Font("Microsoft Yahei", Font.PLAIN, 20);

		banci = new JLabel[5];
		for(int i = 0;i<5;i++){
			banci[i] = new JLabel("第 "+(i+1)+" 班：");
		}
		hourLabel = new JLabel[10];
		for(int i = 0;i<10;i++){
			hourLabel[i] = new JLabel("时");
		}
		minuteLabel = new JLabel[12];
		for(int i = 0;i<12;i++){
			minuteLabel[i] = new JLabel("分");
		}
		jiangeLabel = new JLabel[5];
		for(int i = 0;i<5;i++){
			jiangeLabel[i] = new JLabel("————");
		}
		startHourField = new JTextField[5];
		for(int i = 0;i<5;i++){
			startHourField[i] = new JTextField();
		}
		startMinuteField = new JTextField[5];
		for(int i = 0;i<5;i++){
			startMinuteField[i] = new JTextField();
		}
		endHourField = new JTextField[5];
		for(int i = 0;i<5;i++){
			endHourField[i] = new JTextField();
		}
		endMinuteField = new JTextField[5];
		for(int i = 0;i<5;i++){
			endMinuteField[i] = new JTextField();
		}

		p = new Properties();
		try {
			p.load(new FileInputStream("res/properties/setting.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setAttribute(){
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		lateLabel.setFont(font);
		lateField.setFont(font);
		kuangLabel.setFont(font);
		kuangField.setFont(font);
		saveBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		lateField.setMaximumSize(new Dimension(80, 30));
		kuangField.setMaximumSize(new Dimension(80, 30));

		for(int i = 0;i<5;i++){
			banci[i].setFont(font);
		}
		for(int i = 0;i<10;i++){
			hourLabel[i].setFont(font);
		}
		for(int i = 0;i<12;i++){
			minuteLabel[i].setFont(font);
		}
		for(int i = 0;i<5;i++){
			startHourField[i].setFont(font);
			startHourField[i].setMaximumSize(new Dimension(80, 30));
		}
		for(int i = 0;i<5;i++){
			startMinuteField[i].setFont(font);
			startMinuteField[i].setMaximumSize(new Dimension(80, 30));
		}
		for(int i = 0;i<5;i++){
			endHourField[i].setFont(font);
			endHourField[i].setMaximumSize(new Dimension(80, 30));
		}
		for(int i = 0;i<5;i++){
			endMinuteField[i].setFont(font);
			endMinuteField[i].setMaximumSize(new Dimension(80, 30));
		}

		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void addComponent(){
		//辅助面板
		JPanel[] panels = new JPanel[8];
		for(int i = 0;i<8;i++){
			panels[i] = new JPanel();
			panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.X_AXIS));
		}

		panels[0].add(banci[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(startHourField[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(hourLabel[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(startMinuteField[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(minuteLabel[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(jiangeLabel[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(endHourField[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(hourLabel[1]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(endMinuteField[0]);
		panels[0].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[0].add(minuteLabel[1]);

		panels[1].add(banci[1]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(startHourField[1]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(hourLabel[2]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(startMinuteField[1]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(minuteLabel[2]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(jiangeLabel[1]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(endHourField[1]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(hourLabel[3]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(endMinuteField[1]);
		panels[1].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[1].add(minuteLabel[3]);

		panels[2].add(banci[2]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(startHourField[2]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(hourLabel[4]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(startMinuteField[2]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(minuteLabel[4]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(jiangeLabel[2]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(endHourField[2]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(hourLabel[5]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(endMinuteField[2]);
		panels[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[2].add(minuteLabel[5]);

		panels[3].add(banci[3]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(startHourField[3]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(hourLabel[6]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(startMinuteField[3]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(minuteLabel[6]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(jiangeLabel[3]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(endHourField[3]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(hourLabel[7]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(endMinuteField[3]);
		panels[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[3].add(minuteLabel[7]);

		panels[4].add(banci[4]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(startHourField[4]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(hourLabel[8]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(startMinuteField[4]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(minuteLabel[8]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(jiangeLabel[4]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(endHourField[4]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(hourLabel[9]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(endMinuteField[4]);
		panels[4].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[4].add(minuteLabel[9]);

		panels[5].add(lateLabel);
		panels[5].add(lateField);
		panels[5].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[5].add(minuteLabel[11]);
		panels[5].add(Box.createRigidArea(new Dimension(370,0)));

		panels[6].add(kuangLabel);
		panels[6].add(kuangField);
		panels[6].add(Box.createRigidArea(new Dimension(10, 0)));
		panels[6].add(minuteLabel[10]);
		panels[6].add(Box.createRigidArea(new Dimension(310,0)));

		panels[7].add(saveBtn);
		panels[7].add(Box.createRigidArea(new Dimension(20, 0)));
		panels[7].add(cancelBtn);

		centerPanel.add(Box.createVerticalStrut(60));
		centerPanel.add(panels[0]);
		centerPanel.add(Box.createVerticalStrut(30));
		centerPanel.add(panels[1]);
		centerPanel.add(Box.createVerticalStrut(30));
		centerPanel.add(panels[2]);
		centerPanel.add(Box.createVerticalStrut(30));
		centerPanel.add(panels[3]);
		centerPanel.add(Box.createVerticalStrut(30));
		centerPanel.add(panels[4]);
		centerPanel.add(Box.createVerticalStrut(30));
		centerPanel.add(panels[5]);
		centerPanel.add(Box.createVerticalStrut(30));
		centerPanel.add(panels[6]);
		centerPanel.add(Box.createVerticalStrut(50));
		centerPanel.add(panels[7]);
		this.getContentPane().add(centerPanel);
	}

	private void getDefaultSetting(){
		startHourField[0].setText(p.getProperty("one_start_hour"));
		startMinuteField[0].setText(p.getProperty("one_start_minute"));
		endHourField[0].setText(p.getProperty("one_end_hour"));
		endMinuteField[0].setText(p.getProperty("one_end_minute"));

		startHourField[1].setText(p.getProperty("two_start_hour"));
		startMinuteField[1].setText(p.getProperty("two_start_minute"));
		endHourField[1].setText(p.getProperty("two_end_hour"));
		endMinuteField[1].setText(p.getProperty("two_end_minute"));

		startHourField[2].setText(p.getProperty("three_start_hour"));
		startMinuteField[2].setText(p.getProperty("three_start_minute"));
		endHourField[2].setText(p.getProperty("three_end_hour"));
		endMinuteField[2].setText(p.getProperty("three_end_minute"));

		startHourField[3].setText(p.getProperty("four_start_hour"));
		startMinuteField[3].setText(p.getProperty("four_start_minute"));
		endHourField[3].setText(p.getProperty("four_end_hour"));
		endMinuteField[3].setText(p.getProperty("four_end_minute"));

		startHourField[4].setText(p.getProperty("five_start_hour"));
		startMinuteField[4].setText(p.getProperty("five_start_minute"));
		endHourField[4].setText(p.getProperty("five_end_hour"));
		endMinuteField[4].setText(p.getProperty("five_end_minute"));

		lateField.setText(p.getProperty("late"));
		kuangField.setText(p.getProperty("kuang"));
	}

	private void addListener(){
		saveBtn.addActionListener(new SaveBtnListener(this));
		cancelBtn.addActionListener(e -> this.setVisible(false));
	}

	public void display(){
		init();
		setAttribute();
		getDefaultSetting();
		addComponent();
		addListener();
		this.setSize((int)(ScreenUtils.WIDTH*0.4), (int)(ScreenUtils.HEIGHT*0.6));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setVisible(true);
	}

	public JTextField[] getStartHourField() {
		return startHourField;
	}

	public JTextField[] getStartMinuteField() {
		return startMinuteField;
	}

	public JTextField[] getEndHourField() {
		return endHourField;
	}

	public JTextField[] getEndMinuteField() {
		return endMinuteField;
	}

	public JTextField getLateField() {
		return lateField;
	}

	public JTextField getKuangField() {
		return kuangField;
	}
	public Properties getP() {
		return p;
	}
}
