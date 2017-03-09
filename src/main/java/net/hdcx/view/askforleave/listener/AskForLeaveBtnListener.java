package net.hdcx.view.askforleave.listener;

import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 请假按钮监听器
 * Created by Kevin on 2017/3/3.
 */
public class AskForLeaveBtnListener implements ActionListener {

	IPeopleService peopleService;

	public AskForLeaveBtnListener() {
		peopleService = new PeopleService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		peopleService.askForLeave();
	}
}
