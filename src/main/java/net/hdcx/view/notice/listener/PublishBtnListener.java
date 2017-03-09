package net.hdcx.view.notice.listener;

import net.hdcx.service.INoticeService;
import net.hdcx.service.impl.NoticeService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 发布公告对话框中的发布按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class PublishBtnListener implements ActionListener{
	private INoticeService noticeService;

	public PublishBtnListener() {
		noticeService = new NoticeService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		noticeService.publishNotice();
	}
}
