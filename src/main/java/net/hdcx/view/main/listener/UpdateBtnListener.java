package net.hdcx.view.main.listener;

import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 修改公告对话框中的修改按钮监听器
 * Created by Kevin on 2017/3/3.
 */
public class UpdateBtnListener implements ActionListener {

	private JTextArea originTextArea;
	private JTextArea editArea;

	public UpdateBtnListener(JTextArea originTextArea, JTextArea editArea) {
		this.originTextArea = originTextArea;
		this.editArea = editArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String originContent = originTextArea.getText();
		String newContent = editArea.getText();
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "update notices set content=? where content=?";
		try {
			int affectedRows = qr.update(sql, newContent, originContent);
			if (affectedRows > 0){
				JOptionPane.showMessageDialog(null, "修改成功");
				originTextArea.setText(newContent);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败，请联系管理员");
		}
	}
}
