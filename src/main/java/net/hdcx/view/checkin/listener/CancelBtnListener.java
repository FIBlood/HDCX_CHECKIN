package net.hdcx.view.checkin.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 2017/2/28.
 */
public class CancelBtnListener implements ActionListener {

	private JDialog dialog;

	public CancelBtnListener(JDialog dialog){
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(false);
	}
}
