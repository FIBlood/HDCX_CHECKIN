package net.hdcx.view.search;

import net.hdcx.bean.Member;
import net.hdcx.bean.Minister;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
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
	private String name = null;
	private String id = null;

	public SearchResultDialog(String name, String id) {
		this.name = name;
		this.id = id;
	}

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
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String table = "members";

		for(int count = 0; count < 2; table="ministers", count++){
			String sql = "select * from " + table + " where studentId=? and name=?";
			//查找members表
			if (count == 0){
				member = new Member();
				try {
					member = qr.query(sql, new BeanHandler<Member>(Member.class), id, name);
					if (member != null){break;}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//查找mininster表
			if(count == 1){
				minister = new Minister();
				try {
					minister = qr.query(sql, new BeanHandler<Minister>(Minister.class), id, name);
					if (minister != null){break;}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
