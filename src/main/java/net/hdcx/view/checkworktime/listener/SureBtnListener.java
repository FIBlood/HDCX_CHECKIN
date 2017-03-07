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

	private JComboBox workWeekBox;
	private JComboBox workTimeBox;
	private JDialog dialog;

	public SureBtnListener(JDialog dialog, JComboBox workWeekBox, JComboBox workTimeBox) {
		this.dialog = dialog;
		this.workWeekBox = workWeekBox;
		this.workTimeBox = workTimeBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CheckResultDialog checkResultDialog = new CheckResultDialog(workWeekBox, workTimeBox);
		dialog.setVisible(false);
		checkResultDialog.display();
	}
}
