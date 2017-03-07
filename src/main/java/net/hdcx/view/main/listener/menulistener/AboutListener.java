package net.hdcx.view.main.listener.menulistener;

import net.hdcx.view.main.menu.AboutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 关于菜单监听器
 * Created by Kevin on 2017/3/5.
 */
public class AboutListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		AboutDialog aboutDialog = new AboutDialog();
		aboutDialog.display();
	}
}
