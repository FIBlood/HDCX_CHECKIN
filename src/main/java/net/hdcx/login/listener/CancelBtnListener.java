package net.hdcx.login.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * “取消”按钮监听器
 * Created by Kevin on 2017/2/27.
 */
public class CancelBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
