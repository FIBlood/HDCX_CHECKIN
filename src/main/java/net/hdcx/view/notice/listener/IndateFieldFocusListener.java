package net.hdcx.view.notice.listener;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 发布公告对话框中有效期文本框焦点监视器
 * Created by Kevin on 2017/3/1.
 */
public class IndateFieldFocusListener implements FocusListener {

	private JTextField indateField;

	public IndateFieldFocusListener(JTextField indateField) {
		this.indateField = indateField;
	}

	@Override
	public void focusGained(FocusEvent e) {
		//获得焦点
	}

	@Override
	public void focusLost(FocusEvent e) {
		//失去焦点
		if ("".equals(indateField.getText())){
			return;
		}
		try{
			int indate = Integer.parseInt(indateField.getText());
			if (indate < 0){
				throw new Exception("有效期不能小于零");
			}
		} catch(Exception e1){
			JOptionPane.showMessageDialog(null, "请输入有效数字");
		}
	}
}
