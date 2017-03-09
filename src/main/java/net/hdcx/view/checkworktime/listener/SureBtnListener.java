package net.hdcx.view.checkworktime.listener;

import net.hdcx.view.checkworktime.CheckResultDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 查找对话框中的确定按钮监听器
 * Created by Kevin on 2017/3/2.
 */
public class SureBtnListener implements ActionListener {
	private JDialog dialog;

	public SureBtnListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CheckResultDialog checkResultDialog = new CheckResultDialog();
		dialog.setVisible(false);
		checkResultDialog.display();
	}
}
