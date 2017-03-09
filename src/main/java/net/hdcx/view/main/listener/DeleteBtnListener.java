package net.hdcx.view.main.listener;

import net.hdcx.bean.Notice;
import net.hdcx.view.passworddialog.PasswordDialog;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.NoticeList;
import net.hdcx.view.main.ShowPublishNoticePanel;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * 公告展示栏中的删除按钮监听器
 * Created by Kevin on 2017/3/3.
 */
public class DeleteBtnListener implements ActionListener {

	private ShowPublishNoticePanel noticePanel;
	private JPanel deletedPanel;
	private String content;

	public DeleteBtnListener(ShowPublishNoticePanel noticePanel, JPanel deletedPanel, String content) {
		this.noticePanel = noticePanel;
		this.deletedPanel = deletedPanel;
		this.content = content;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PasswordDialog passwordDialog = new PasswordDialog();
		passwordDialog.display();
		if (!passwordDialog.validatePassword()){
			return;
		}
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from notices where content=?";
		try {
			int affectedRows = qr.update(sql, content);
			if (affectedRows > 0){
				JOptionPane.showMessageDialog(null, "删除成功");
				noticePanel.remove(deletedPanel);
				removeNotice(content);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除失败，请联系管理员");
		}
	}

	private void removeNotice(String content){
		List<Notice> noticeList = NoticeList.getNoticeList();
		for (Notice notice : noticeList){
			if (content.equals(notice.getContent())){
				noticeList.remove(notice);
				break;
			}
		}
	}
}
