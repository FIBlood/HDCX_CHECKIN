package net.hdcx.view.main.listener;

import net.hdcx.view.checkout.CheckoutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主面板中的签退按钮监听器
 * Created by Kevin on 2017/2/28.
 */
public class CheckoutBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		CheckoutDialog checkoutDialog = new CheckoutDialog();
		checkoutDialog.display();
	}
}
