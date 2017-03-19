package net.hdcx.utils;

import net.hdcx.bean.Notice;
import net.hdcx.service.INoticeService;
import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.NoticeService;
import net.hdcx.service.impl.PeopleService;
import net.hdcx.view.checkin.CheckinDialog;
import net.hdcx.view.main.RightPanel;
import net.hdcx.view.main.ShowOnDutyMembersPanel;
import net.hdcx.view.main.ShowOnDutyMinistersPanel;
import net.hdcx.view.main.ShowPublishNoticePanel;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 预处理器
 * Created by Kevin on 2017/3/9.
 */
public class Preprocessor {
	public static void getNotices(){
		INoticeService noticeService = new NoticeService();
		List<Map<String, Object>> mapList = noticeService.checkNotices();
		for (Iterator<Map<String, Object>> it = mapList.iterator(); it.hasNext(); ) {
			Map<String, Object> map = it.next();
			String content = (String) map.get("content");
			String publisher = (String) map.get("publisher");
			String publishTime = (String) map.get("publishTime");
			String deadline = (String) map.get("deadline");
			Notice notice = new Notice();
			notice.setContent(content);
			notice.setPublisher(publisher);
			notice.setPublishTime(publishTime);
			notice.setDeadline(deadline);
			NoticeList.getNoticeList().add(notice);
		}
		ShowPublishNoticePanel noticePanel = RightPanel.getNoticePanel();
		noticePanel.update();
		noticePanel.addToPanel();
	}

	public static void getOnDutyPeople(){
		XMLOperator.readMembersXML();
		ShowOnDutyMembersPanel.update();
		XMLOperator.readMinistersXML();
		ShowOnDutyMinistersPanel.update();
	}

	public static void getInitCheckNameList(){
		DefaultListModel<String> dlm = (DefaultListModel<String>) CheckinDialog.getNameList().getModel();
		String workWeek = (String)CheckinDialog.getWorkWeekBox().getSelectedItem();
		String workTime = (String)CheckinDialog.getWorkTimeBox().getSelectedItem();
		IPeopleService peopleService = new PeopleService();
		String sql = "select name, studentId from members where workWeek=? and workTime=?";
		List<Object[]> memberNameList = peopleService.queryMembers(sql, workWeek, workTime);
		sql = "select name, studentId from ministers where workWeek=? and workTime=?";
		List<Object[]> ministerNameList = peopleService.queryMinisters(sql, workWeek, workTime);
		for (Object[] memberName : memberNameList){
			dlm.addElement(memberName[0].toString() + " " + memberName[1].toString());
		}
		for (Object[] ministerName : ministerNameList){
			dlm.addElement(ministerName[0].toString() + " " + ministerName[1].toString());
		}
	}
}
