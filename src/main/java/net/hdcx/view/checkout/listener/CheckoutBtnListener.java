package net.hdcx.view.checkout.listener;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.MemberList;
import net.hdcx.utils.MinisterList;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * 签退对话框中的签退按钮监听器
 * Created by Kevin on 2017/2/28.
 */
public class CheckoutBtnListener implements ActionListener {

	private JTextField IDField;

	public CheckoutBtnListener(JTextField IDField){
		this.IDField = IDField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int count = 0;

		if(!isCheckin(IDField.getText())){
			JOptionPane.showMessageDialog(null, "此成员还没签到");
			return;
		}

		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String table = null;
		int affectRows = 0;
		for(table = "members"; count < 2; table="ministers", count++){
			String sql = "update "+ table +" set mountOfCheckout=mountOfCheckout+1 where studentId=? ";
			try {
				affectRows = qr.update(sql,IDField.getText());
				if(affectRows > 0){
					JOptionPane.showMessageDialog(null, "签到成功");
					break;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		removeFromList(IDField.getText());
	}

	/**
	 * 检查成员是否已经签到
	 * @param id 学号
	 * @return true:已签到 false:未签到
	 */
	private boolean isCheckin(String id){
		List<Member> memberList = MemberList.getMemberList();
		List<Minister> ministerList = MinisterList.getMinisterList();
		boolean isFind = false;
		for(Member member : memberList){
			if(member.getStudentId().equals(id)){
				isFind = true;
				break;
			}
		}
		if(!isFind){
			for(Minister minister : ministerList){
				if(minister.getStudentId().equals(id)){
					isFind = true;
					break;
				}
			}
		}
		return isFind;
	}

	/**
	 * 将已签退的成员从列表中移除
	 * @param id 学号
	 */
	private void removeFromList(String id){
		List<Member> memberList = MemberList.getMemberList();
		List<Minister> ministerList = MinisterList.getMinisterList();
		for(Member member : memberList){
			if(id.equals(member.getStudentId())){
				MemberList.getMemberList().remove(member);
				return;
			}
		}
		for(Minister minister : ministerList){
			if(id.equals(minister.getStudentId())){
				MinisterList.getMinisterList().remove(minister);
				return;
			}
		}
	}
}
