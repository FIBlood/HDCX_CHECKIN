package net.hdcx.view.checkout.listener;

import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;
import net.hdcx.view.main.ShowOnDutyMembersPanel;
import net.hdcx.view.main.ShowOnDutyMinistersPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 签退对话框中的签退按钮监听器
 * Created by Kevin on 2017/2/28.
 */
public class CheckoutBtnListener implements ActionListener {

	IPeopleService peopleService;

	public CheckoutBtnListener(){
		peopleService = new PeopleService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		peopleService.checkout(null);
		ShowOnDutyMinistersPanel.update();
		ShowOnDutyMembersPanel.update();
	}
}
