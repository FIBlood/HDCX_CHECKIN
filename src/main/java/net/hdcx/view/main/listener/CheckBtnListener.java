package net.hdcx.view.main.listener;

import net.hdcx.view.checkworktime.CheckWorkTimeDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主面板中的查找按钮监听器（根据班次和值班周期查找）
 * Created by Kevin on 2017/3/2.
 */
public class CheckBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		CheckWorkTimeDialog checkPanel = new CheckWorkTimeDialog();
		checkPanel.display();
	}
}
