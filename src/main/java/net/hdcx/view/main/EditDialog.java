package net.hdcx.view.main;

import net.hdcx.utils.ImageIconUtils;
import net.hdcx.view.IWindow;
import net.hdcx.view.main.listener.UpdateBtnListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 编辑公告对话框
 * Created by Kevin on 2017/3/3.
 */
public class EditDialog extends JDialog implements IWindow {

	private JPanel bottomPanel;
	private JTextArea editArea;
	private JScrollPane jsp;
	private JButton updateBtn;
	private JButton cancelBtn;
	private JTextArea originTextArea;

	public EditDialog(JTextArea originTextArea){
		this.originTextArea = originTextArea;
	}

	private void init(){
		this.setTitle("编辑");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		bottomPanel = new JPanel();
		editArea = new JTextArea(originTextArea.getText());
		jsp = new JScrollPane(editArea);
		updateBtn = new JButton("修改");
		cancelBtn = new JButton("取消");
	}

	private void setAttribute(){
		editArea.setLineWrap(true);
		editArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		updateBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		cancelBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		editArea.setFont(new Font("Microsoft Yahei", Font.PLAIN, 20));
		jsp.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	private void addComponent(){
		this.add(jsp, BorderLayout.CENTER);
		bottomPanel.add(updateBtn);
		bottomPanel.add(cancelBtn);
		this.add(bottomPanel,BorderLayout.SOUTH);
	}

	private void addListener(){
		updateBtn.addActionListener(new UpdateBtnListener(originTextArea, editArea));
		cancelBtn.addActionListener(e -> this.setVisible(false));
	}

	@Override
	public void display() {
		init();
		setAttribute();
		addComponent();
		addListener();
		ImageIconUtils.setDefaultImageIcon(this);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}
