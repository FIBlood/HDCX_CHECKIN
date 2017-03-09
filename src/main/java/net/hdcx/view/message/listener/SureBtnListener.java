package net.hdcx.view.message.listener;

import net.hdcx.service.IMessageService;
import net.hdcx.service.impl.MessageService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 留言对话框中的确定按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class SureBtnListener implements ActionListener {

	private IMessageService messageService = new MessageService();

	public SureBtnListener(){
		messageService = new MessageService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		messageService.message();
	}
}
