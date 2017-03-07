package net.hdcx.view.main.listener.menulistener;

import net.hdcx.view.main.menu.AddPeopleDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 添加人员信息监听器
 * Created by Kevin on 2017/3/5.
 */
public class AddPeopleListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		AddPeopleDialog addPeopleDialog = new AddPeopleDialog(e.getActionCommand());
		addPeopleDialog.display();
	}
}
