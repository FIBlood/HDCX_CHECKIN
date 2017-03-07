package net.hdcx.view.main.listener;

import net.hdcx.view.checkin.CheckinDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 2017/2/28.
 */
public class CheckinBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		new CheckinDialog();
	}
}
