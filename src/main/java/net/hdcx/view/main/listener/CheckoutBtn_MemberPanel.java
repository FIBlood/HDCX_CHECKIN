package net.hdcx.view.main.listener;

import net.hdcx.bean.Member;
import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;
import net.hdcx.utils.MemberList;
import net.hdcx.view.main.ShowOnDutyMembersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 右面板中的签退按钮监听器
 * Created by Kevin on 2017/3/2.
 */
public class CheckoutBtn_MemberPanel implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel<String> dlm = ShowOnDutyMembersPanel.getDefaultListModel();
		JList nameList = ShowOnDutyMembersPanel.getJList();
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
		List<Member> memberList = MemberList.getMemberList();
		for(Member member : memberList){
			if(studentId.equals(member.getStudentId())){
				memberList.remove(member);
				break;
			}
		}
	}
}
