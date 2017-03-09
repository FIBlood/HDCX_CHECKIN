package net.hdcx.view.search;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.service.IPeopleService;
import net.hdcx.service.impl.PeopleService;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * 搜索结果对话框
 * Created by Kevin on 2017/3/1.
 */
public class SearchResultDialog extends JDialog implements IWindow{

	private JScrollPane jsp = null;
	private JTable table = null;
	private DefaultTableModel dtm = null;
	private Vector columnName = null;
	private Vector row = null;
	private DefaultTableCellRenderer dtcr = null;
	private Font font = null;

	public SearchResultDialog() {}

	private void init(){
		this.setTitle("搜索结果");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		dtm = new DefaultTableModel();
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		columnName = new Vector();
		row = new Vector();
		dtcr = new DefaultTableCellRenderer();
		font = new Font("Microsoft Yahei", Font.PLAIN, 20);
	}

	private void setAttribute(){
		table.getTableHeader().setResizingAllowed(true);
		table.getTableHeader().setFont(font);
		table.setFont(font);
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dtcr);
		table.getTableHeader().setDefaultRenderer(dtcr);
		table.setRowHeight(40);
		table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
	}

	private void setTitle(){
		columnName.add("学号");
		columnName.add("姓名");
		columnName.add("联系电话");
		columnName.add("邮箱");
		columnName.add("所在部门");
		columnName.add("值班周期");
		columnName.add("班次");
		columnName.add("签到次数");
		columnName.add("签退次数");
		dtm.setColumnIdentifiers(columnName);
	}

	private void addComponent(){
		this.getContentPane().add(jsp);
	}

	private void getResult(){
		Member member = null;
		Minister minister = null;
		IPeopleService peopleService = new PeopleService();
		member = peopleService.findMemberById(SearchDialog.getIdField().getText());
		if (member == null){
			minister = peopleService.findMinisterById(SearchDialog.getIdField().getText());
		}

		//往表格中添加数据
		Vector rowData = new Vector();
		if (member != null){ //如果是干事
			rowData.add(member.getStudentId());
			rowData.add(member.getName());
			rowData.add(member.getContact());
			rowData.add(member.getEmail());
			rowData.add(member.getDept());
			rowData.add(member.getWorkWeek());
			rowData.add(member.getWorkTime());
			rowData.add(member.getMountOfCheckin());
			rowData.add(member.getMountOfCheckout());
		}else if (minister != null){ //如果是部长
			rowData.add(minister.getStudentId());
			rowData.add(minister.getName());
			rowData.add(minister.getContact());
			rowData.add(minister.getEmail());
			rowData.add(minister.getDept());
			rowData.add(minister.getWorkWeek());
			rowData.add(minister.getWorkTime());
			rowData.add(minister.getMountOfCheckin());
			rowData.add(minister.getMountOfCheckout());
		}
		dtm.addRow(rowData);
	}

	public void display(){
		this.init();
		this.setAttribute();
		this.setTitle();
		this.getResult();
		this.addComponent();
		this.setSize((int)(ScreenUtils.WIDTH*0.9), (int)(ScreenUtils.HEIGHT*0.5));
		this.setLocationRelativeTo(null);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setVisible(true);
	}
}
