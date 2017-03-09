package net.hdcx.service.impl;

import net.hdcx.bean.Notice;
import net.hdcx.dao.INoticesDAO;
import net.hdcx.dao.impl.NoticesDAO;
import net.hdcx.service.INoticeService;
import net.hdcx.utils.NoticeList;
import net.hdcx.view.main.RightPanel;
import net.hdcx.view.notice.NoticeDialog;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 公告服务
 * Created by Kevin on 2017/3/8.
 */
public class NoticeService implements INoticeService {
	private INoticesDAO noticesDAO = new NoticesDAO();

	@Override
	public void publishNotice() {
		String name = NoticeDialog.getNameField().getText();
		String sIndate = NoticeDialog.getIndateField().getText();
		String content = NoticeDialog.getContentArea().getText();
		Notice notice = new Notice();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		int indate = checkIndate(sIndate);
		if(indate == -1){
			return;
		}
		Calendar publishTime = Calendar.getInstance();
		Calendar deadline = (Calendar) publishTime.clone();
		deadline.add(Calendar.DAY_OF_MONTH, indate);
		notice.setPublisher(name);
		notice.setContent(content);
		notice.setPublishTime(sdf.format(publishTime.getTime()));
		notice.setDeadline(sdf.format(deadline.getTime()));
		int affectRows = noticesDAO.addNotice(notice, 1);
		if (affectRows > 0){
			JOptionPane.showMessageDialog(null, "发布成功");
			NoticeList.getNoticeList().add(notice);
			RightPanel.getNoticePanel().update();
			RightPanel.getNoticePanel().addToPanel();
			NoticeDialog.getNameField().setText("");
			NoticeDialog.getIndateField().setText("");
			NoticeDialog.getContentArea().setText("");
		}
	}

	/**
	 * 检查有效期是否输入合法
	 * @return 如果有效期不合法 返回-1， 如果有效期合法，则返回有效期的数字形式
	 */
	private int checkIndate(String sIndate){
		int indate = 0;
		try{
			indate = Integer.parseInt(sIndate);
			if (indate < 0){
				throw new Exception("有效期不能小于零");
			}
		} catch(Exception e1){
			JOptionPane.showMessageDialog(null, "请输入有效数字");
			return -1;
		}
		return indate;
	}

	@Override
	public void removeNotice() {

	}

	@Override
	public void changeContent() {

	}

	@Override
	public List<Map<String, Object>> checkNotices() {
		return noticesDAO.getNotices();
	}
}
