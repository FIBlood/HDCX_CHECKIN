package net.hdcx.view.main.listener.menulistener;

import net.hdcx.view.main.menu.SkimDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 浏览监听器
 * Created by Kevin on 2017/3/5.
 */
public class SkimListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		SkimDialog skimDialog = new SkimDialog(e.getActionCommand());
		skimDialog.display();
	}
}
