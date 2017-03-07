package net.hdcx.view.checkworktime;

import net.hdcx.utils.DBUtils;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.ScreenUtils;
import net.hdcx.view.IWindow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;
import java.util.List;

/**
 * 查找结果对话框
 * Created by Kevin on 2017/3/2.
 */
public class CheckResultDialog extends JDialog implements IWindow{
	private JTable table;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JComboBox workWeekBox;
	private JComboBox workTimeBox;
	private DefaultTableCellRenderer dtcr;

	public CheckResultDialog(JComboBox workWeekBox, JComboBox workTimeBox){
		this.workWeekBox = workWeekBox;
		this.workTimeBox = workTimeBox;
	}

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
		String workWeek = (String)workWeekBox.getSelectedItem();
		String workTime = (String)workTimeBox.getSelectedItem();
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String tableName = "members";
		for(int count = 0; count < 2; tableName="ministers", count++){
			String sql = "select * from " + tableName + " where workWeek=? and workTime=?";
			try {
				List<Object[]> personList = qr.query(sql, new ArrayListHandler(), workWeek, workTime);
				for (Object[] person : personList){
					Vector row = new Vector();
					for (int i = 0; i < person.length; i++){
						row.add(person[i].toString());
						if(i == 8){
							break;
						}
					}
					dtm.addRow(row);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
