package net.hdcx.service.impl;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.dao.IMembersDAO;
import net.hdcx.dao.IMinistersDAO;
import net.hdcx.dao.impl.MembersDAO;
import net.hdcx.dao.impl.MinistersDAO;
import net.hdcx.service.IPeopleService;

/**
 * 人事管理功能接口实现
 * Created by Kevin on 2017/3/7.
 */
public class PeopleService implements IPeopleService{

	private IMembersDAO membersDAO = new MembersDAO();
	private IMinistersDAO ministersDAO = new MinistersDAO();

	@Override
	public void checkin(Object o) {
		Object people = null;
		if (o instanceof Member){
			people = (Member)o;
		} else if (o instanceof Minister){
			people = (Minister)o;
		}
	}

	@Override
	public void checkout(Object o) {

	}

	@Override
	public void askForLeave(Object o) {

	}

	@Override
	public void recordLate(Object o) {

	}

	@Override
	public void recordAbsent(Object o) {

	}

	@Override
	public void addPeople(Object o) {

	}
}
