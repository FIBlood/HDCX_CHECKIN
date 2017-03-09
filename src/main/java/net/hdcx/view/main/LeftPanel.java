package net.hdcx.view.main;

import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.main.listener.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 主面板的左边
 * Created by Kevin on 2017/2/27.
 */
public class LeftPanel extends JPanel {
	private JButton checkinBtn = null;
	private JButton checkoutBtn = null;
	private JButton campusEntryBtn = null;
	private JButton askForLeaveBtn = null;
	private JButton sinaBtn = null;
	private JButton renrenBtn =null;
	private JButton hdcxBtn = null;
	private JButton messageBtn = null;
	private JButton searchBtn = null;
	private JButton publishNoticeBtn = null;
	private JButton checkBtn = null;
	private TimerPanel timerPanel = null;
	private JPanel leftChildPanel = null;

	public LeftPanel(){
		this.init();
		this.setAttribute();
		this.addComponent();
		this.addListener();
	}

	public void init(){
		checkinBtn = new JButton(new ImageIcon("res/images/checkinBtn.jpg"));
		checkinBtn.setToolTipText("签到");
		checkoutBtn = new JButton(new ImageIcon("res/images/checkoutBtn.jpg"));
		checkoutBtn.setToolTipText("签退");
		campusEntryBtn = new JButton(new ImageIcon("res/images/campusentry.jpg"));
		campusEntryBtn.setToolTipText("校园网入口");
		askForLeaveBtn = new JButton(new ImageIcon("res/images/qingjia.jpg"));
		askForLeaveBtn.setToolTipText("请假");
		sinaBtn = new JButton(new ImageIcon("res/images/sina.jpg"));
		sinaBtn.setToolTipText("新浪微博");
		renrenBtn = new JButton(new ImageIcon("res/images/renren.jpg"));
		renrenBtn.setToolTipText("人人网");
		hdcxBtn = new JButton(new ImageIcon("res/images/hdcx.jpg"));
		hdcxBtn.setToolTipText("创协官网");
		messageBtn = new JButton(new ImageIcon("res/images/message.jpg"));
		messageBtn.setToolTipText("留言");
		searchBtn = new JButton(new ImageIcon("res/images/search.jpg"));
		searchBtn.setToolTipText("搜索");
		publishNoticeBtn = new JButton(new ImageIcon("res/images/publish.jpg"));
		publishNoticeBtn.setToolTipText("发布公告");
		checkBtn = new JButton(new ImageIcon("res/images/check.jpg"));
		checkBtn.setToolTipText("查询值班信息");
		timerPanel = new TimerPanel();
		leftChildPanel = new JPanel();
	}

	private void setAttribute(){
		this.setBorder(new EmptyBorder((int)(ScreenUtils.HEIGHT*0.15), 0, 0, 0));
		leftChildPanel.setLayout(new BoxLayout(leftChildPanel, BoxLayout.Y_AXIS));
		leftChildPanel.setOpaque(false);
		checkinBtn.setContentAreaFilled(false);
		checkinBtn.setMargin(new Insets(0, 0, 0, 0));
		checkoutBtn.setContentAreaFilled(false);
		checkoutBtn.setMargin(new Insets(0, 0, 0, 0));
		sinaBtn.setContentAreaFilled(false);
		sinaBtn.setMargin(new Insets(0, 0, 0, 0));
		renrenBtn.setContentAreaFilled(false);
		renrenBtn.setMargin(new Insets(0, 0, 0, 0));
		campusEntryBtn.setContentAreaFilled(false);
		campusEntryBtn.setMargin(new Insets(0, 0, 0, 0));
		askForLeaveBtn.setContentAreaFilled(false);
		askForLeaveBtn.setMargin(new Insets(0, 0, 0, 0));
		hdcxBtn.setContentAreaFilled(false);
		hdcxBtn.setMargin(new Insets(0, 0, 0, 0));
		messageBtn.setContentAreaFilled(false);
		messageBtn.setMargin(new Insets(0, 0, 0, 0));
		messageBtn.setContentAreaFilled(false);
		messageBtn.setMargin(new Insets(0, 0, 0, 0));
		searchBtn.setContentAreaFilled(false);
		searchBtn.setMargin(new Insets(0, 0, 0, 0));
		publishNoticeBtn.setContentAreaFilled(false);
		publishNoticeBtn.setMargin(new Insets(0, 0, 0, 0));
		checkBtn.setContentAreaFilled(false);
		checkBtn.setMargin(new Insets(0, 0, 0, 0));
	}

	private void addComponent(){
		//辅助面板
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		panel4.setOpaque(false);
		panel5.setOpaque(false);

		panel1.add(checkinBtn);
		panel1.add(Box.createHorizontalStrut(5));
		panel1.add(checkoutBtn);
		panel1.add(Box.createHorizontalStrut(10));
		panel1.add(sinaBtn);
		panel2.add(campusEntryBtn);
		panel2.add(Box.createHorizontalStrut(5));
		panel2.add(askForLeaveBtn);
		panel2.add(Box.createHorizontalStrut(10));
		panel2.add(renrenBtn);
		panel3.add(hdcxBtn);
		panel3.add(Box.createHorizontalStrut(10));
		panel3.add(messageBtn);
		panel4.add(searchBtn);
		panel4.add(Box.createHorizontalStrut(10));
		panel4.add(publishNoticeBtn);
		panel5.add(checkBtn);
		panel5.add(Box.createHorizontalStrut(10));
		panel5.add(timerPanel);

		leftChildPanel.add(panel1);
		leftChildPanel.add(Box.createVerticalStrut(10));
		leftChildPanel.add(panel2);
		leftChildPanel.add(Box.createVerticalStrut(10));
		leftChildPanel.add(panel3);
		leftChildPanel.add(Box.createVerticalStrut(10));
		leftChildPanel.add(panel4);
		leftChildPanel.add(Box.createVerticalStrut(10));
		leftChildPanel.add(panel5);

		this.add(leftChildPanel);
	}

	private void addListener(){
		checkinBtn.addActionListener(new CheckinBtnListener());
		checkoutBtn.addActionListener(new CheckoutBtnListener());
		sinaBtn.addActionListener(new SinaBtnListener());
		campusEntryBtn.addActionListener(new CampusEntryBtnListener());
		renrenBtn.addActionListener(new RenRenBtnListener());
		hdcxBtn.addActionListener(new HDCXBtnListener());
		messageBtn.addActionListener(new MessageBtnListener());
		publishNoticeBtn.addActionListener(new PublishNoticeBtnListener());
		searchBtn.addActionListener(new SearchBtnListener());
		checkBtn.addActionListener(new CheckBtnListener());
		askForLeaveBtn.addActionListener(new AskForLeaveBtnListener());
	}


}
