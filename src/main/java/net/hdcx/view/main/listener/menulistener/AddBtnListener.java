package net.hdcx.view.main.listener.menulistener;

import net.hdcx.utils.DBUtils;
import net.hdcx.view.main.menu.AddPeopleDialog;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 添加人员信息对话框中的添加按钮监听器
 * Created by Kevin on 2017/3/5.
 */
public class AddBtnListener implements ActionListener {

	private AddPeopleDialog addPeopleDialog;
	private String table;

	public AddBtnListener(AddPeopleDialog addPeopleDialog, String table) {
		this.addPeopleDialog = addPeopleDialog;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into " + table + "(studentId, name, contact, email, dept, workWeek, workTime) values(?, ?, ?, ?, ?, ?, ?)";
		try {
			int affectedRows = qr.update(
					sql,
					addPeopleDialog.getIdField().getText(),
					addPeopleDialog.getNameField().getText(),
					addPeopleDialog.getContactField().getText(),
					addPeopleDialog.getEmailField().getText(),
					addPeopleDialog.getDeptField().getText(),
					addPeopleDialog.getWorkWeekField().getText(),
					addPeopleDialog.getWorkTimeField().getText()
			);
			if (affectedRows > 0){
				JOptionPane.showMessageDialog(addPeopleDialog, "信息录入成功");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(addPeopleDialog, "信息录入失败，请联系管理员");
		}
	}
}
