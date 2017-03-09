package net.hdcx.utils;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * XML操作器
 * Created by Kevin on 2017/3/9.
 */
public class XMLOperator {
	public static void writeMembersXML(){
		try {
			List<Member> memberList = MemberList.getMemberList();
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("OnDutyMembers");
			for (Member member : memberList){
				Element membersElem = rootElement.addElement("members");
				Element studentIdElem = membersElem.addElement("studentId");
				Element nameElem = membersElem.addElement("name");
				studentIdElem.setText(member.getStudentId());
				nameElem.setText(member.getName());
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			OutputStream os = Files.newOutputStream(Paths.get("res/ondutypeople/checkedinMembers.xml"));
			XMLWriter writer = new XMLWriter(os, format);
			writer.write(document);
			writer.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeMinistersXML(){
		try {
			List<Minister> ministerList = MinisterList.getMinisterList();
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("OnDutyMinisters");
			for (Minister minister : ministerList){
				Element membersElem = rootElement.addElement("ministers");
				Element studentIdElem = membersElem.addElement("studentId");
				Element nameElem = membersElem.addElement("name");
				studentIdElem.setText(minister.getStudentId());
				nameElem.setText(minister.getName());
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			OutputStream os = Files.newOutputStream(Paths.get("res/ondutypeople/checkedinMinisters.xml"));
			XMLWriter writer = new XMLWriter(os, format);
			writer.write(document);
			writer.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readMembersXML(){
		List<Member> membersList = MemberList.getMemberList();
		IPeopleService peopleService = new PeopleService();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read("res/ondutypeople/checkedinMembers.xml");
			Element rootElement = document.getRootElement();
			Iterator<Element> elementIterator = rootElement.elementIterator();
			while (elementIterator.hasNext()){
				Element element = elementIterator.next();
				Element studentIdElem = element.element("studentId");
				Member member = peopleService.findMemberById(studentIdElem.getText());
				membersList.add(member);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static void readMinistersXML(){
		List<Minister> ministerList = MinisterList.getMinisterList();
		IPeopleService peopleService = new PeopleService();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read("res/ondutypeople/checkedinMinisters.xml");
			Element rootElement = document.getRootElement();
			Iterator<Element> elementIterator = rootElement.elementIterator();
			while (elementIterator.hasNext()){
				Element element = elementIterator.next();
				Element studentIdElem = element.element("studentId");
				Minister minister = peopleService.findMinisterById(studentIdElem.getText());
				ministerList.add(minister);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
