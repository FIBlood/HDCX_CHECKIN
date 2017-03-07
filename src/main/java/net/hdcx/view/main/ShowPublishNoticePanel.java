package net.hdcx.view.main;

import net.hdcx.bean.Notice;
import net.hdcx.utils.NoticeList;
import net.hdcx.view.main.listener.DeleteBtnListener;
import net.hdcx.view.main.listener.EditBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 公告展示栏
 * Created by Kevin on 2017/2/27.
 */
public class ShowPublishNoticePanel extends JPanel{

	private List<JTextArea> noticeAreaList = new ArrayList<>();
	private List<JLabel> publisherLabelList = new ArrayList<>();
	private List<JLabel> timeLabelList = new ArrayList<>();

	public ShowPublishNoticePanel(){

	}

	public void getData(){
		List<Notice> noticeList = NoticeList.getNoticeList();
		noticeAreaList.clear();
		int length = noticeList.size();
		for(int i = 0; i < length; i++){
			JTextArea noticeArea = new JTextArea();
			noticeAreaList.add(noticeArea);
			noticeAreaList.get(i).setText(noticeList.get(i).getContent());
			noticeAreaList.get(i).setLineWrap(true);
			noticeAreaList.get(i).setEditable(false);
			noticeAreaList.get(i).setFont(new Font("Microsoft Yahei", Font.PLAIN, 18));
		}
		publisherLabelList.clear();
		for(int i = 0; i < length; i++){
			JLabel publisherLabel = new JLabel(noticeList.get(i).getPublisher());
			publisherLabel.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
			publisherLabelList.add(publisherLabel);
		}
		timeLabelList.clear();
		for(int i = 0; i < length; i++){
			JLabel timeLabel = new JLabel(noticeList.get(i).getPublishTime().toString());
			timeLabel.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
			timeLabelList.add(timeLabel);
		}
	}

	public void addToPanel(){
		this.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int length = noticeAreaList.size();
		for(int i = 0; i < length; i++){
			JPanel panel = new JPanel();
			JPanel borderPanel = new JPanel();
			JPanel publisherAndTimePanel = new JPanel();
			JPanel centerBorderPanel = new JPanel();
			JButton editBtn = new JButton("编辑");
			JButton deleteBtn = new JButton("删除");

			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			borderPanel.setLayout(new BorderLayout());
			publisherAndTimePanel.setLayout(new BoxLayout(publisherAndTimePanel, BoxLayout.X_AXIS));
			centerBorderPanel.setLayout(new BorderLayout());

			editBtn.setFont(new Font("Microsoft yahei", Font.PLAIN, 14));
			editBtn.setContentAreaFilled(false);
			editBtn.setMargin(new Insets(0, 0, 0, 0));
			deleteBtn.setFont(new Font("Microsoft yahei", Font.PLAIN, 14));
			deleteBtn.setContentAreaFilled(false);
			deleteBtn.setMargin(new Insets(0, 0, 0, 0));

			borderPanel.add(centerBorderPanel, BorderLayout.CENTER);
			borderPanel.add(noticeAreaList.get(i), BorderLayout.NORTH);
			centerBorderPanel.add(publisherAndTimePanel, BorderLayout.NORTH);
			publisherAndTimePanel.add(Box.createHorizontalGlue());
			publisherAndTimePanel.add(publisherLabelList.get(i));
			publisherAndTimePanel.add(Box.createHorizontalStrut(20));
			publisherAndTimePanel.add(timeLabelList.get(i));
			publisherAndTimePanel.add(Box.createHorizontalStrut(5));
			publisherAndTimePanel.add(editBtn);
			publisherAndTimePanel.add(deleteBtn);
			publisherAndTimePanel.add(Box.createRigidArea(new Dimension(20, 40)));
			panel.add(borderPanel);

			panel.setBorder(new EmptyBorder(0, 10, 0, 10));
			this.add(panel);
			this.add(Box.createVerticalStrut(20));

			//为编辑和删除按钮添加事件监听
			editBtn.addActionListener(new EditBtnListener(noticeAreaList.get(i)));
			deleteBtn.addActionListener(new DeleteBtnListener(this, panel, noticeAreaList.get(i).getText()));
		}
		this.refreshPanel();
	}

	private void refreshPanel(){
		this.validate();
		this.updateUI();
	}
}
