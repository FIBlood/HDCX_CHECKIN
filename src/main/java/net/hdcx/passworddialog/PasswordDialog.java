package net.hdcx.passworddialog;

import net.hdcx.passworddialog.listener.CancelBtnListener;
import net.hdcx.passworddialog.listener.SureBtnListener;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.PasswordValidator;
import net.hdcx.view.IWindow;

import javax.swing.*;
import java.awt.*;

/**
 * 密码对话框
 * Created by Kevin on 2017/3/2.
 */
public class PasswordDialog extends JDialog{
	private JPanel centerPanel = null;
	private JLabel passwordLabel = null;
	private JPasswordField passwordField = null;
	private JButton sureBtn = null;
	private JButton cancelBtn = null;
	private Font font = null;
	private IWindow window;
	public  boolean isTrue;

	public PasswordDialog(){}

	public PasswordDialog(IWindow window){
		this.window = window;
	}

	private void init(){
		this.setTitle("请输入口令");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		centerPanel = new JPanel();
		passwordLabel = new JLabel("口令：");
		passwordField = new JPasswordField();
		sureBtn = new JButton("确定");
		cancelBtn = new JButton("取消");
		font = new Font("Microsoft Yahei", Font.PLAIN, 16);
	}

	private void setAttribute(){
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		passwordLabel.setFont(font);
		passwordField.setFont(font);
		passwordField.setColumns(10);
		sureBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		cancelBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));

		passwordField.setMaximumSize(new Dimension(150, 25));
	}

	private void addComponent(){
		//辅助面板
		JPanel panel0 = new JPanel();

		panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));

		panel0.add(passwordLabel);
		panel0.add(Box.createRigidArea(new Dimension(10, 0)));
		panel0.add(passwordField);
		panel0.add(Box.createRigidArea(new Dimension(10, 0)));
		panel0.add(sureBtn);
		panel0.add(Box.createRigidArea(new Dimension(10, 0)));
		panel0.add(cancelBtn);

		this.add(centerPanel);
		centerPanel.add(Box.createVerticalStrut(15));
		centerPanel.add(panel0);
	}

	private void addListener(){
		sureBtn.addActionListener(new SureBtnListener(window, this));
		cancelBtn.addActionListener(new CancelBtnListener(this));
	}

	public boolean validatePassword(){
		boolean isTrue = false;
		PasswordValidator validator = new PasswordValidator();
		if (validator.validate(passwordField.getPassword())){
			isTrue = true;
		}
		return isTrue;
	}

	public void display(){
		init();
		setAttribute();
		addComponent();
		addListener();
		this.setSize(400, 100);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
