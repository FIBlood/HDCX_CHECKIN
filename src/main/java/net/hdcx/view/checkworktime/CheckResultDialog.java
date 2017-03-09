package net.hdcx.view.checkworktime;

import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * 查找结果对话框
 * Created by Kevin on 2017/3/2.
 */
public class CheckResultDialog extends JDialog implements IWindow{
	private JTable table;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private DefaultTableCellRenderer dtcr;

	public CheckResultDialog(){}

	private void init(){
		this.setTitle("查找结果");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		dtm = new DefaultTableModel();
		table = new JTable(dtm);
		dtcr = new DefaultTableCellRenderer();
		jsp = new JScrollPane(table);
		setTitle();
	}

	private void setAttribute(){
		table.setFont(new Font("Microsoft Yahei", Font.PLAIN, 20));
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setRowHeight(40);
		table.setDefaultRenderer(Object.class, dtcr);
		table.setEnabled(false);
		table.getTableHeader().setFont(new Font("Microsoft Yahei", Font.PLAIN, 20));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setDefaultRenderer(dtcr);
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	private void addComponent(){
		this.add(jsp);
	}

	private void setTitle(){
		Vector titleVector = new Vector();
		titleVector.add("学号");
		titleVector.add("姓名");
		titleVector.add("联系电话");
		titleVector.add("邮箱");
		titleVector.add("所在部门");
		titleVector.add("值班周期");
		titleVector.add("班次");
		titleVector.add("签到次数");
		titleVector.add("签退次数");
		dtm.setColumnIdentifiers(titleVector);
	}

	private void getData(){
		String workWeek = (String)CheckWorkTimeDialog.getWorkWeekBox().getSelectedItem();
		String workTime = (String)CheckWorkTimeDialog.getWorkTimeBox().getSelectedItem();
		IPeopleService peopleService = new PeopleService();
		String sql = "select * from members where workWeek=? and workTime=?";
		List<Object[]> resultList = peopleService.queryMembers(sql, workWeek, workTime);
		addToDTM(resultList);
		sql = "select * from ministers where workWeek=? and workTime=?";
		resultList = peopleService.queryMinisters(sql, workWeek, workTime);
		addToDTM(resultList);
	}

	private void addToDTM(List<Object[]> resultList){
		for (Object[] results : resultList){
			Vector row = new Vector();
			for (int i = 0; i < results.length; i++){
				row.add(results[i].toString());
				if (i == 8){
					break;
				}
			}
			dtm.addRow(row);
		}
	}

	public void display(){
		init();
		setAttribute();
		addComponent();
		getData();
		this.setSize((int)(ScreenUtils.WIDTH*0.9), (int)(ScreenUtils.HEIGHT*0.6));
		ImageIconUtils.setDefaultImageIcon(this);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
