package net.hdcx.passworddialog.listener;

import net.hdcx.passworddialog.PasswordDialog;
import net.hdcx.view.IWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 密码对话框中的确定按钮监听器
 * Created by Kevin on 2017/3/2.
 */
public class SureBtnListener implements ActionListener {
	private PasswordDialog passwordDialog;
	private IWindow window;

	public SureBtnListener(IWindow window, PasswordDialog passwordDialog){
		this.window = window;
		this.passwordDialog = passwordDialog;
	}

	public SureBtnListener(){}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(passwordDialog.validatePassword()){
			//完成相应的功能
			passwordDialog.setVisible(false);
			passwordDialog.isTrue = true;
			if (window != null){
				window.display();
			}
		}else{
			JOptionPane.showMessageDialog(null, "口令错误，请重新输入");
		}
	}
}
