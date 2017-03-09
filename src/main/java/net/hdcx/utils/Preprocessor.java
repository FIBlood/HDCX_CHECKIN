package net.hdcx.utils;

import net.hdcx.bean.Notice;
import net.hdcx.service.INoticeService;
import net.hdcx.service.impl.NoticeService;
import net.hdcx.view.main.RightPanel;
import net.hdcx.view.main.ShowOnDutyMembersPanel;
import net.hdcx.view.main.ShowOnDutyMinistersPanel;
import net.hdcx.view.main.ShowPublishNoticePanel;

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
}
