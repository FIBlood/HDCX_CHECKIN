package net.hdcx.view.main.listener.menulistener;

import net.hdcx.passworddialog.PasswordDialog;
import net.hdcx.view.main.menu.ManageSettingDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 管理设置监听器
 * Created by Kevin on 2017/3/5.
 */
public class ManageSettingListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ManageSettingDialog manageSettingDialog = new ManageSettingDialog();
		PasswordDialog passwordDialog = new PasswordDialog(manageSettingDialog);
		passwordDialog.display();
	}
}
