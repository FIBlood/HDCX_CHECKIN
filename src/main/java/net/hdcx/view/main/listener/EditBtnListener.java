package net.hdcx.view.main.listener;

import net.hdcx.passworddialog.PasswordDialog;
import net.hdcx.view.main.EditDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 公告展示中的编辑按钮
 * Created by Kevin on 2017/3/3.
 */
public class EditBtnListener implements ActionListener {

	private JTextArea originTextArea;

	public EditBtnListener(JTextArea originTextArea) {
		this.originTextArea = originTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EditDialog editDialog = new EditDialog(originTextArea);
		PasswordDialog passwordDialog = new PasswordDialog(editDialog);
		passwordDialog.display();
	}
}
