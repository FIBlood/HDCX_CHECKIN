package net.hdcx.view.checkin.listener;

import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 签到按钮监听器
 * Created by Kevin on 2017/2/28.
 */
public class CheckinBtnListener implements ActionListener{
	private JList<String> nameList;
	private JComboBox<String> workTimeBox;
	private IPeopleService peopleService;

	public CheckinBtnListener(JList<String> nameList, JComboBox<String> workTimeBox){
		this.nameList = nameList;
		this.workTimeBox = workTimeBox;
		peopleService = new PeopleService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		peopleService.checkin();
		//从列表中删除已签到的人员
		DefaultListModel<String> defaultListModel = (DefaultListModel<String>) nameList.getModel();
		defaultListModel.remove(nameList.getSelectedIndex());
	}
}
