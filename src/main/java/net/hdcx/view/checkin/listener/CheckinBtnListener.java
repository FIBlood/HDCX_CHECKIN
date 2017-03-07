package net.hdcx.view.checkin.listener;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.MemberList;
import net.hdcx.utils.MinisterList;
import net.hdcx.view.main.RightPanel;
import net.hdcx.view.main.ShowOnDutyMembersPanel;
import net.hdcx.view.main.ShowOnDutyMinistersPanel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

/**
 * 签到按钮监听器
 * Created by Kevin on 2017/2/28.
 */
public class CheckinBtnListener implements ActionListener{
	private ShowOnDutyMembersPanel membersPanel;
	private ShowOnDutyMinistersPanel ministersPanel;
	private JList<String> nameList;
	private JComboBox<String> workTimeBox;

	public CheckinBtnListener(JList<String> nameList, JComboBox<String> workTimeBox){
		this.nameList = nameList;
		this.workTimeBox = workTimeBox;
		this.membersPanel = RightPanel.getMemberPanel();
		this.ministersPanel = RightPanel.getMinisterPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Calendar checkinTime = Calendar.getInstance();
		Calendar workTime = Calendar.getInstance();
		Calendar absentTime = Calendar.getInstance();
		Properties prop = new Properties();
		String name = (String)nameList.getSelectedValue().split(" ")[0];
		String studentId = (String)nameList.getSelectedValue().split(" ")[1];
		try {
			InputStream is = new FileInputStream("res/properties/setting.properties");
			prop.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int hour = 0;
		int minute = 0;
		int late = Integer.parseInt(prop.getProperty("late"));
		int kuang = Integer.parseInt(prop.getProperty("kuang"));
		String start_hour = null;
		String start_minute = null;
		String chosenWorkTime = (String)workTimeBox.getSelectedItem();
		if(chosenWorkTime.equals("第一班")){
			start_hour = prop.getProperty("one_start_hour");
			start_minute = prop.getProperty("one_start_minute");
		}else if(chosenWorkTime.equals("第二班")){
			start_hour = prop.getProperty("two_start_hour");
			start_minute = prop.getProperty("two_start_minute");
		}else if(chosenWorkTime.equals("第三班")){
			start_hour = prop.getProperty("three_start_hour");
			start_minute = prop.getProperty("three_start_minute");
		}else if(chosenWorkTime.equals("第四班")){
			start_hour = prop.getProperty("four_start_hour");
			start_minute = prop.getProperty("four_start_minute");
		}else if(chosenWorkTime.equals("第五班")){
			start_hour = prop.getProperty("five_start_hour");
			start_minute = prop.getProperty("five_start_minute");
		}

		hour = Integer.parseInt(start_hour);
		minute = Integer.parseInt(start_minute);
		workTime.set(Calendar.HOUR_OF_DAY, hour);
		workTime.set(Calendar.MINUTE, minute+late);
		workTime.set(Calendar.SECOND, 0);
		absentTime.set(Calendar.HOUR_OF_DAY, hour);
		absentTime.set(Calendar.MINUTE, minute+kuang);
		absentTime.set(Calendar.SECOND, 0);

		//是否迟到
		byte isLate = 0;
		if(checkinTime.compareTo(workTime) > 0 && checkinTime.compareTo(absentTime) < 0){
			isLate = 1;
		}
		int count = 0 ;
		int affectRows = 0;
		String table = null;
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		//遍历ministers,members两个表查找签到人员
		for(table = "members"; count < 2; table="ministers", count++){
			String sql = "update "+ table +" set mountOfCheckin=mountOfCheckin+1, mountOfLate=mountOfLate+? where studentId=? and name=?";
			try {
				affectRows = qr.update(sql,isLate, studentId, name);
				if(affectRows > 0){
					JOptionPane.showMessageDialog(null, "签到成功");
					DefaultListModel<String> defaultListModel = (DefaultListModel<String>) nameList.getModel();
					defaultListModel.removeElementAt(nameList.getSelectedIndex());
					break;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
		}
		if(count == 2){
			JOptionPane.showMessageDialog(null, "无此记录");
			return;
		}

		//将已签到的人员信息存放在相应的包装器中
		if("members".equals(table)){//如果是干事签到
			String sql = "select * from members where studentId=?";
			Member member = new Member();
			try {
				member = qr.query(sql, new BeanHandler<Member>(Member.class), studentId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			MemberList.getMemberList().add(member);
		}else if("ministers".equals(table)){//如果是部长签到
			String sql = "select * from ministers where studentId=?";
			Minister minister = new Minister();
			try {
				minister = qr.query(sql, new BeanHandler<Minister>(Minister.class), studentId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			MinisterList.getMinisterList().add(minister);
		}
		membersPanel.getData();
		ministersPanel.getData();
	}
}
