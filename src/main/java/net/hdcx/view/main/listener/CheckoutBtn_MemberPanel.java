package net.hdcx.view.main.listener;

import net.hdcx.bean.Member;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.MemberList;
import net.hdcx.view.main.ShowOnDutyMembersPanel;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
		int size = dlm.size();
		if (size != 0){
			int index = nameList.getSelectedIndex();
			String studentId = dlm.getElementAt(index).split(" ")[1];
			String sql = "update members set mountOfCheckout=mountOfCheckout+1 where studentId=?";
			int count = 0;
			QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
			try {
				count = qr.update(sql, studentId);
				if (count > 0){
					JOptionPane.showMessageDialog(null, "签退成功");
					remove(studentId);
					dlm.remove(index);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "签退失败，请联系管理员");
			}
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
