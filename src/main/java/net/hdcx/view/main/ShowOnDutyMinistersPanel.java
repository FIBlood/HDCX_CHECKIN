package net.hdcx.view.main;

import net.hdcx.bean.Minister;
import net.hdcx.utils.MinisterList;
import net.hdcx.view.main.listener.CheckoutBtn_MinisterPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * 值班部长名单
 * Created by Kevin on 2017/3/2.
 */
public class ShowOnDutyMinistersPanel extends JPanel {
	private static DefaultListModel<String> dlm = new DefaultListModel<>();
	private static JList nameList = new JList(dlm);
	private JScrollPane jsp = new JScrollPane();
	private JButton checkoutBtn = new JButton("签退");

	public ShowOnDutyMinistersPanel(){
		this.setLayout(new BorderLayout());
		JPanel panel0 = new JPanel();	//辅助面板
		panel0.add(checkoutBtn);
		this.add(panel0, BorderLayout.SOUTH);
		this.add(jsp, BorderLayout.CENTER);
		jsp.getViewport().add(nameList);
		checkoutBtn.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
		nameList.setFont(new Font("Microsoft Yahei", Font.PLAIN, 20));

		this.addListener();
	}

	public void getData(){
		dlm.clear();
		Iterator<Minister> it = MinisterList.getMinisterList().iterator();
		while(it.hasNext()){
			Minister member = it.next();
			dlm.addElement(member.getName()+" "+member.getStudentId());
		}
		this.refreshPanel();
	}

	private void addListener(){
		checkoutBtn.addActionListener(new CheckoutBtn_MinisterPanel());
	}

	private void refreshPanel(){
		this.validate();
		this.updateUI();
	}

	public static JList getJList(){
		return nameList;
	}

	public static DefaultListModel<String> getDefaultListModel(){
		return dlm;
	}
}
