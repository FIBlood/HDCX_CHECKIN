package net.hdcx.service.impl;

import net.hdcx.bean.Message;
import net.hdcx.dao.IMessageDAO;
import net.hdcx.dao.impl.MessageDAO;
import net.hdcx.service.IMessageService;
import net.hdcx.view.message.MessageDialog;

import javax.swing.*;
import java.util.List;

/**
 * 留言服务
 * Created by Kevin on 2017/3/7.
 */
public class MessageService implements IMessageService {

	private IMessageDAO messageDAO = new MessageDAO();

	@Override
	public void message() {
		Message message = new Message();
		message.setStudentId(MessageDialog.getIdField().getText());
		message.setName(MessageDialog.getNameField().getText());
		message.setMessage(MessageDialog.getMessageArea().getText());
		int affectedRows = messageDAO.addMessage(message);
		if (affectedRows > 0){
			JOptionPane.showMessageDialog(null, "留言成功");
			MessageDialog.getIdField().setText("");
			MessageDialog.getNameField().setText("");
			MessageDialog.getMessageArea().setText("");
		}
	}

	@Override
	public void deleteMessage() {

	}

	@Override
	public void changeMessage() {

	}

	@Override
	public List<Object[]> checkMessage() {
		return null;
	}
}
