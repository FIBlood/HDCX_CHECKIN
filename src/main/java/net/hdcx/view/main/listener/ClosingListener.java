package net.hdcx.view.main.listener;

import net.hdcx.view.passworddialog.PasswordDialog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 主窗口监听器
 * Created by Kevin on 2017/3/19.
 */
public class ClosingListener implements WindowListener {
	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		PasswordDialog passwordDialog = new PasswordDialog();
		passwordDialog.display();
		if (passwordDialog.validatePassword()){
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
