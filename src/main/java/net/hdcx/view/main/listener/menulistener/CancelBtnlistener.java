package net.hdcx.view.main.listener.menulistener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 信息录入对话框中的取消按钮监听器
 * Created by Kevin on 2017/3/5.
 */
public class CancelBtnlistener implements ActionListener {

	private Window window;

	public CancelBtnlistener(Window window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.setVisible(false);
	}
}
