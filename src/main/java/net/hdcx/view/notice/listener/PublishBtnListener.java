package net.hdcx.view.notice.listener;

import net.hdcx.bean.Notice;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.NoticeList;
import net.hdcx.view.main.RightPanel;
import net.hdcx.view.main.ShowPublishNoticePanel;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 发布公告对话框中的发布按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class PublishBtnListener implements ActionListener{

	private JTextField nameField;
	private JTextField indateField;
	private JTextArea contentArea;
	private ShowPublishNoticePanel noticePanel;

	public PublishBtnListener(JTextField nameField, JTextField indateField, JTextArea contentArea) {
		this.nameField = nameField;
		this.indateField = indateField;
		this.contentArea = contentArea;
		noticePanel = RightPanel.getNoticePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		Notice notice = new Notice();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		int indate = checkIndate();
		if(indate == -1){
			return;
		}
		Calendar publishTime = Calendar.getInstance();
		Calendar deadline = (Calendar) publishTime.clone();
		deadline.add(Calendar.DAY_OF_MONTH, indate);
		String sql = "insert into notices values(?, ?, now(), date_add(now(), interval ? day));";
		try {
			int affectRows = qr.update(sql, nameField.getText(), contentArea.getText(), indate);
			if (affectRows > 0){
				JOptionPane.showMessageDialog(null, "发布成功");
				notice.setPublisher(nameField.getText());
				notice.setContent(contentArea.getText());
				notice.setPublishTime(sdf.format(publishTime.getTime()));
				notice.setDeadline(sdf.format(deadline.getTime()));
				NoticeList.getNoticeList().add(notice);
				noticePanel.getData();
				noticePanel.addToPanel();
			}
			nameField.setText("");
			indateField.setText("");
			contentArea.setText("");
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "发布失败，请联系管理员");
		}
	}

	/**
	 * 检查有效期是否输入合法
	 * @return 如果有效期不合法 返回-1， 如果有效期合法，则返回有效期的数字形式
	 */
	private int checkIndate(){
		int indate = 0;
		try{
			indate = Integer.parseInt(indateField.getText());
			if (indate < 0){
				throw new Exception("有效期不能小于零");
			}
		} catch(Exception e1){
			JOptionPane.showMessageDialog(null, "请输入有效数字");
			return -1;
		}
		return indate;
	}
}
