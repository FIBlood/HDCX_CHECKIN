package net.hdcx.utils;

import net.hdcx.bean.Notice;

import java.util.ArrayList;
import java.util.List;

/**
 * 公告信息包装器
 * Created by Kevin on 2017/2/27.
 */
public class NoticeList {
	private static List<Notice> noticeList = new ArrayList<>();

	private NoticeList(){}

	public static List<Notice> getNoticeList(){
		return noticeList;
	}
}
