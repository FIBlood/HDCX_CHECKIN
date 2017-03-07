package net.hdcx.utils;

import net.hdcx.bean.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * 成员包装器
 * Created by Kevin on 2017/2/27.
 */
public class MemberList {
	private static List<Member> memberList = new ArrayList<>();

	private MemberList(){}

	public static List<Member> getMemberList(){
		return memberList;
	}
}
