package net.hdcx.view.askforleave.listener;

import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 请假按钮监听器
 * Created by Kevin on 2017/3/3.
 */
public class AskForLeaveBtnListener implements ActionListener {

	private JTextField nameField;
	private JTextField idField;

	public AskForLeaveBtnListener(JTextField nameField, JTextField idField) {
		this.nameField = nameField;
		this.idField = idField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String id = idField.getText();
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String table = "members";
		for (int count = 0; count < 2; table="ministers", count++){
			String sql = "update " + table + " set mountOfAskForLeave=mountOfAskForLeave+1 where studentId=? and name=?";
			try {
				int affectedRows = qr.update(sql, id, name);
				if (affectedRows > 0){
					JOptionPane.showMessageDialog(null, "请假成功");
					nameField.setText("");
					idField.setText("");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "请假失败，请联系管理员");
			}
		}
	}
}
