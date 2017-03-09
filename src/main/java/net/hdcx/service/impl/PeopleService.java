package net.hdcx.service.impl;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.dao.IMembersDAO;
import net.hdcx.dao.IMinistersDAO;
import net.hdcx.dao.impl.MembersDAO;
import net.hdcx.dao.impl.MinistersDAO;
import net.hdcx.service.IPeopleService;
import net.hdcx.utils.MemberList;
import net.hdcx.utils.MinisterList;
import net.hdcx.utils.XMLOperator;
import net.hdcx.view.askforleave.AskForLeaveDialog;
import net.hdcx.view.checkin.CheckinDialog;
import net.hdcx.view.checkout.CheckoutDialog;
import net.hdcx.view.main.RightPanel;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * 人事管理功能接口实现
 * Created by Kevin on 2017/3/7.
 */
public class PeopleService implements IPeopleService{

	private IMembersDAO membersDAO = new MembersDAO();
	private IMinistersDAO ministersDAO = new MinistersDAO();

	@Override
	public void checkin() {
		String studentId = CheckinDialog.getNameList().getSelectedValue().split(" ")[1];
		String name = CheckinDialog.getNameList().getSelectedValue().split(" ")[0];
		String choseWorkTime = (String)CheckinDialog.getWorkTimeBox().getSelectedItem();
		byte isLate = isLate(choseWorkTime);//是否迟到  1--迟到   0--没有迟到
		boolean isFind = false;//是否找到相应的签到人
		boolean isMemberCheckin = false;//是否是干事签到
		boolean isMinisterCheckin = false;//是否是部长签到
		String sql = "update members set mountOfCheckin=mountOfCheckin+1, mountOfLate=mountOfLate+? where studentId=? and name=?";
		if (membersDAO.update(sql, isLate, studentId, name) > 0){
			isMemberCheckin = true;
			isFind = true;
		}else{
			sql = "update ministers set mountOfCheckin=mountOfCheckin+1, mountOfLate=mountOfLate+? where studentId=? and name=?";
			if (ministersDAO.update(sql, isLate, studentId, name) > 0){
				isMinisterCheckin = true;
				isFind = true;
			}else{
				JOptionPane.showMessageDialog(null, "无此记录");
			}
		}
		if(isFind){
			JOptionPane.showMessageDialog(null, "签到成功");
		}

		//将已签到的人员信息存放在相应的包装器中
		if(isMemberCheckin){//如果是干事签到
			Member member = membersDAO.findMember(studentId);
			MemberList.getMemberList().add(member);
			//将签到信息存在checkedinMembers.xml文件中
			XMLOperator.writeMembersXML();
		}else if(isMinisterCheckin){//如果是部长签到
			Minister minister = ministersDAO.findMinister(studentId);
			MinisterList.getMinisterList().add(minister);
			//将签到信息存在checkedinMinisters.xml文件中
			XMLOperator.writeMinistersXML();
		}
		RightPanel.getMemberPanel().update();
		RightPanel.getMinisterPanel().update();
	}

	/**
	 * 根据选择的班次查找配置文件是否迟到
	 * @param choseWorkTime 选择的班次
	 * @return 1--迟到  0--没有迟到
	 */
	private byte isLate(String choseWorkTime){
		Calendar checkinTime = Calendar.getInstance();
		Calendar workTime = Calendar.getInstance();
		Calendar absentTime = Calendar.getInstance();
		Properties prop = new Properties();
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
		if(choseWorkTime.equals("第一班")){
			start_hour = prop.getProperty("one_start_hour");
			start_minute = prop.getProperty("one_start_minute");
		}else if(choseWorkTime.equals("第二班")){
			start_hour = prop.getProperty("two_start_hour");
			start_minute = prop.getProperty("two_start_minute");
		}else if(choseWorkTime.equals("第三班")){
			start_hour = prop.getProperty("three_start_hour");
			start_minute = prop.getProperty("three_start_minute");
		}else if(choseWorkTime.equals("第四班")){
			start_hour = prop.getProperty("four_start_hour");
			start_minute = prop.getProperty("four_start_minute");
		}else if(choseWorkTime.equals("第五班")){
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
		return isLate;
	}

	@Override
	public void checkout(String id) {
		String studentId = null;
		if (id == null){
			studentId = CheckoutDialog.getIdField().getText();
		}else{
			studentId = id;
		}
		boolean isFind = false;//是否找到结果
		boolean isMinisterCheckout = false;
		boolean isMembercheckout = false;
		if(!isCheckin(studentId)){
			JOptionPane.showMessageDialog(null, "此成员还没签到");
			return;
		}
		if (membersDAO.update("mountOfCheckout", studentId) > 0){
			isFind = true;
			isMembercheckout = true;
		}else {
			if (ministersDAO.update("mountOfCheckout", studentId) > 0){
				isFind = true;
				isMinisterCheckout = true;
			}else {
				JOptionPane.showMessageDialog(null, "无此记录");
			}
		}
		if (isFind){
			JOptionPane.showMessageDialog(null, "签退成功");
			removeFromList(studentId);
		}
		if (isMembercheckout){
			XMLOperator.writeMembersXML();
		}else if(isMinisterCheckout){
			XMLOperator.writeMinistersXML();
		}

	}

	/**
	 * 检查成员是否已经签到
	 * @param id 学号
	 * @return true:已签到 false:未签到
	 */
	private boolean isCheckin(String id){
		List<Member> memberList = MemberList.getMemberList();
		List<Minister> ministerList = MinisterList.getMinisterList();
		boolean isFind = false;
		for(Member member : memberList){
			if(member.getStudentId().equals(id)){
				isFind = true;
				break;
			}
		}
		if(!isFind){
			for(Minister minister : ministerList){
				if(minister.getStudentId().equals(id)){
					isFind = true;
					break;
				}
			}
		}
		return isFind;
	}

	/**
	 * 将已签退的成员从列表中移除
	 * @param id 学号
	 */
	private void removeFromList(String id){
		List<Member> memberList = MemberList.getMemberList();
		List<Minister> ministerList = MinisterList.getMinisterList();
		for(Member member : memberList){
			if(id.equals(member.getStudentId())){
				MemberList.getMemberList().remove(member);
				return;
			}
		}
		for(Minister minister : ministerList){
			if(id.equals(minister.getStudentId())){
				MinisterList.getMinisterList().remove(minister);
				return;
			}
		}
	}

	@Override
	public void askForLeave() {
		String studentId = AskForLeaveDialog.getIdField().getText();
		boolean isFind = false;
		if (membersDAO.update("mountOfAskForLearve", studentId) > 0){
			isFind = true;
		}else {
			if (ministersDAO.update("mountOfAskForLeave", studentId) > 0){
				isFind = true;
			}else {
				JOptionPane.showMessageDialog(null, "无此记录");
			}
		}
		if (isFind){
			JOptionPane.showMessageDialog(null, "请假成功");
			AskForLeaveDialog.getIdField().setText("");
			AskForLeaveDialog.getNameField().setText("");
		}
	}

	@Override
	public void recordAbsent(Object o) {

	}

	@Override
	public void addPeople(Object o) {

	}

	@Override
	public List<Object[]> queryMembers(String sql, Object... params) {
		return membersDAO.query(sql, params);
	}

	@Override
	public List<Object[]> queryMinisters(String sql, Object... params) {
		return ministersDAO.query(sql, params);
	}

	@Override
	public Minister findMinisterById(String studentId) {
		return ministersDAO.findMinister(studentId);
	}

	@Override
	public Member findMemberById(String studentId) {
		return membersDAO.findMember(studentId);
	}
}
