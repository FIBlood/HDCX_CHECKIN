package net.hdcx.view.main.listener;

import net.hdcx.view.message.MessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主面板中的留言按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class MessageBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		MessageDialog messageDialog = new MessageDialog();
		messageDialog.display();
	}
}
