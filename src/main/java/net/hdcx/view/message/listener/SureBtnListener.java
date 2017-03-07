package net.hdcx.view.message.listener;

import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 留言对话框中的确定按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class SureBtnListener implements ActionListener {

	private JDialog dialog;
	private JTextField nameField;
	private JTextField idField;
	private JTextArea messageArea;

	public SureBtnListener(JDialog dialog, JTextField nameField, JTextField idField, JTextArea messageArea){
		this.dialog = dialog;
		this.nameField = nameField;
		this.idField = idField;
		this.messageArea = messageArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into message(studentId, name, message, time) values(?, ?, ?, Now())";
		try {
			int affectRows = qr.update(sql, idField.getText(), nameField.getText(), messageArea.getText());
			if (affectRows > 0){
				JOptionPane.showMessageDialog(dialog, "留言成功");
			}
			idField.setText("");
			nameField.setText("");
			messageArea.setText("");
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(dialog, "留言失败，请联系管理员");
		}
	}
}
