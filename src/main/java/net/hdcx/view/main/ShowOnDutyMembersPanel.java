package net.hdcx.view.main;

import net.hdcx.bean.Member;
import net.hdcx.utils.MemberList;
import net.hdcx.view.main.listener.CheckoutBtn_MemberPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * 值班干事名单
 * Created by Kevin on 2017/3/2.
 */
public class ShowOnDutyMembersPanel extends JPanel {
	private static DefaultListModel<String> dlm = new DefaultListModel<>();
	private static JList nameList = new JList(dlm);
	private JScrollPane jsp = new JScrollPane();
	private JButton checkoutBtn = new JButton("签退");

	public ShowOnDutyMembersPanel(){
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

	public static void update(){
		dlm.clear();
		Iterator<Member> it = MemberList.getMemberList().iterator();
		while(it.hasNext()){
			Member member = it.next();
			dlm.addElement(member.getName()+" "+member.getStudentId());
		}
	}

	private void addListener(){
		checkoutBtn.addActionListener(new CheckoutBtn_MemberPanel());
	}


	public static JList getJList(){
		return nameList;
	}

	public static DefaultListModel<String> getDefaultListModel(){
		return dlm;
	}
}
