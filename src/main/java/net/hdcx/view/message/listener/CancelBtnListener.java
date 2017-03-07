package net.hdcx.view.message.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 留言对话框中的取消按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class CancelBtnListener implements ActionListener {

	private JDialog dialog;

	public CancelBtnListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
	}
}
