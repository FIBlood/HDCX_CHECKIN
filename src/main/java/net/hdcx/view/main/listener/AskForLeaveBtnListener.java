package net.hdcx.view.main.listener;

import net.hdcx.view.askforleave.AskForLeaveDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主面板中的请假按钮监听器
 * Created by Kevin on 2017/3/3.
 */
public class AskForLeaveBtnListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		AskForLeaveDialog askForLeaveDialog = new AskForLeaveDialog();
		askForLeaveDialog.display();
	}
}
