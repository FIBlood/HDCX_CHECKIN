package net.hdcx.view.main.listener;

import net.hdcx.bean.Minister;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.MinisterList;
import net.hdcx.view.main.ShowOnDutyMinistersPanel;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
		int size = dlm.size();
		if (size != 0){
			int index = nameList.getSelectedIndex();
			String studentId = dlm.getElementAt(index).split(" ")[1];
			String sql = "update ministers set mountOfCheckout=mountOfCheckout+1 where studentId=?";
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
		List<Minister> ministerList = MinisterList.getMinisterList();
		for(Minister minister : ministerList){
			if(studentId.equals(minister.getStudentId())){
				ministerList.remove(minister);
				break;
			}
		}
	}
}
