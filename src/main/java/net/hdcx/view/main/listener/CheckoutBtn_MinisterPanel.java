package net.hdcx.view.main.listener;

import net.hdcx.bean.Minister;
import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;
import net.hdcx.utils.MinisterList;
import net.hdcx.view.main.ShowOnDutyMinistersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 值班部长面板签退按钮监听器
 * Created by Kevin on 2017/3/2.
 */
public class CheckoutBtn_MinisterPanel implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel<String> dlm = ShowOnDutyMinistersPanel.getDefaultListModel();
		JList nameList = ShowOnDutyMinistersPanel.getJList();
		IPeopleService peopleService = new PeopleService();
		int size = dlm.size();
		if (size != 0){
			int index = nameList.getSelectedIndex();
			String studentId = dlm.getElementAt(index).split(" ")[1];
			peopleService.checkout(studentId);
			dlm.remove(index);
		}
	}

	private void remove(String studentId){
		List<Minister> ministerList = MinisterList.getMinisterList();
		for(Minister minister : ministerList){
			if(studentId.equals(minister.getStudentId())){
				ministerList.remove(minister);
				break;
			}
		}
	}
}
