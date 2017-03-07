package net.hdcx.view.search.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 搜索对话框的取消按钮
 * Created by Kevin on 2017/3/1.
 */
public class CancelBtnListener implements ActionListener {

	JDialog dialog;

	public CancelBtnListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
	}
}
