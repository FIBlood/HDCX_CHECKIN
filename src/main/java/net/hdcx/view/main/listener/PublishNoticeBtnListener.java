package net.hdcx.view.main.listener;

import net.hdcx.view.notice.NoticeDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主面板中的发布公告按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class PublishNoticeBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		NoticeDialog noticeDialog = new NoticeDialog();
		noticeDialog.display();
	}
}
