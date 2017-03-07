package net.hdcx.view.main.menu;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;

import javax.swing.*;
import java.awt.*;

/**
 * “关于”对话框
 * Created by Kevin on 2017/3/5.
 */
public class AboutDialog extends JDialog implements IWindow{
	private JTextPane aboutText = null;

	public AboutDialog(){}

	public void init(){
		this.setTitle("关于");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		aboutText = new JTextPane();
	}

	private void setAttribute(){
		this.setLayout(null);
		aboutText.setText("  黑大创协签到软件：\r\n  当前版本：Release 1.1.0\r\n\r\n  更新日志: 1.1.0版本更改软件架构，改变原始的签到方式\r\n  版本：Beta\r\n  创建于：2016/8/9\r\n  创建人：梁凯文\r\n\r\n  版本：Release:1.0.0\r\n  最后修改于：2017/3/5\r\n  修改人：梁凯文\r\n\r\n  版权所有：黑龙江大学学生创业协会\r\n  网址：http://www.hdcx.net");
		aboutText.setOpaque(false);
		aboutText.setEditable(false);
		aboutText.setFont(new Font("Mirosoft yahei", Font.PLAIN, 14));
		aboutText.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		this.setResizable(false);
	}

	private void addComponent(){
		aboutText.setBounds(30,30,400,300);
		this.add(aboutText);
	}

	public void display(){
		init();
		setAttribute();
		addComponent();
		this.setSize((int)(ScreenUtils.WIDTH*0.25), (int)(ScreenUtils.HEIGHT*0.4));
		this.setLocationRelativeTo(null);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setVisible(true);
	}
}
