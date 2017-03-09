package net.hdcx.view.main.menu;

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
import java.util.List;
import java.util.Vector;

/**
 * 浏览对话框
 * Created by Kevin on 2017/3/5.
 */
public class SkimDialog extends JDialog implements IWindow {
	private String actionCommand = null;
	private JPanel ministerLabelPanel  = null;
	private JPanel memberLabelPanel = null;
	private JScrollPane ministerJSP = null;
	private JScrollPane memberJSP = null;
	private JScrollPane messageJSP = null;
	private JPanel centerPanel = null;
	private JLabel ministerLabel = null;
	private JLabel memberLabel = null;
	private Font font = null;
	private DefaultTableModel ministerDTM = null;
	private DefaultTableModel memberDTM = null;
	private DefaultTableModel messageDTM = null;
	private JTable ministerTable = null;
	private JTable memberTable = null;
	private JTable messageTable = null;
	private DefaultTableCellRenderer dtcr = null;
	private DefaultTableCellRenderer messageDTCR = null;
	private Vector columnName = null;
	private Vector messageTitle = null;

	public SkimDialog(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	private void init(){
		this.setTitle("浏览");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		ministerLabelPanel = new JPanel();
		memberLabelPanel = new JPanel();
		ministerJSP = new JScrollPane();
		memberJSP = new JScrollPane();
		centerPanel = new JPanel();
		ministerLabel = new JLabel("部长");
		memberLabel = new JLabel("干事");
		font = new Font("Microsoft Yahei", Font.PLAIN, 20);
		ministerDTM = new DefaultTableModel();
		memberDTM = new DefaultTableModel();
		ministerTable = new JTable(ministerDTM);
		memberTable = new JTable(memberDTM );
		dtcr = new DefaultTableCellRenderer();
		columnName = new Vector();
		messageDTM = new DefaultTableModel();
		messageTable = new JTable(messageDTM);
		messageTitle = new Vector();
		messageJSP = new JScrollPane();
		messageDTCR = new DefaultTableCellRenderer();
	}

	private void setAttribute(){
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		ministerLabel.setFont(font);
		memberLabel.setFont(font);
		ministerTable.setFont(font);
		memberTable.setFont(font);
		ministerTable.getTableHeader().setFont(font);
		memberTable.getTableHeader().setFont(font);
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		ministerTable.setDefaultRenderer(Object.class, dtcr);
		memberTable.setDefaultRenderer(Object.class, dtcr);
		ministerTable.getTableHeader().setDefaultRenderer(dtcr);
		memberTable.getTableHeader().setDefaultRenderer(dtcr);
		ministerTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		memberTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		memberTable.setRowHeight(40);
		ministerTable.setRowHeight(40);
		ministerTable.setEnabled(false);
		memberTable.setEnabled(false);
		ministerTable.getTableHeader().setReorderingAllowed(false);
		memberTable.getTableHeader().setReorderingAllowed(false);
		messageJSP.getViewport().add(messageTable);
		messageTable.setFont(font);
		messageTable.getTableHeader().setFont(font);
		messageTable.setEnabled(false);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageTable.setRowHeight(100);
		messageDTCR.setVerticalAlignment(SwingConstants.TOP);
		messageTable.setDefaultRenderer(Object.class, messageDTCR);
	}

	private void addCompoennt(){
		ministerLabelPanel.add(ministerLabel);
		memberLabelPanel.add(memberLabel);
		ministerJSP.getViewport().add(ministerTable);
		memberJSP.getViewport().add(memberTable);
		centerPanel.add(ministerLabelPanel);
		centerPanel.add(ministerJSP);
		centerPanel.add(memberLabelPanel);
		centerPanel.add(memberJSP);
		this.getContentPane().add(centerPanel);
	}

	private void addTitle(){
		columnName.add("姓名");
		columnName.add("学号");
		columnName.add("联系电话");
		columnName.add("邮箱");
		columnName.add("所在部门");
		columnName.add("值班周期");
		columnName.add("班次");
		columnName.add("签到次数");
		columnName.add("签退次数");
		messageTitle.add("姓名");
		messageTitle.add("留言");
		messageTitle.add("时间");
		ministerDTM.setColumnIdentifiers(columnName);
		memberDTM.setColumnIdentifiers(columnName);
		messageDTM.setColumnIdentifiers(messageTitle);
		messageTable.getColumnModel().getColumn(1).setPreferredWidth(300);
	}

	public void addData(){
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String table = "members";
		for (int count = 0; count < 2; table="ministers", count++){
			String sql = "select * from " + table + " where dept=?";
			try {
				List<Object[]> resultList = qr.query(sql, new ArrayListHandler(), actionCommand);
				for (int i = 0, size = resultList.size(); i < size; i++){
					if ("members".equals(table)){
						putDataIntoMemberDTM(resultList.get(i));
					}else if ("ministers".equals(table)){
						putDataIntoMinisterDTM(resultList.get(i));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//单独处理留言选项
		if ("留言".equals(actionCommand)){
			centerPanel.removeAll();
			centerPanel.add(messageJSP);
			String sql = "select name, message, date_format(time, '%Y-%m-%d %H:%i') from message";
			try {
				List<Object[]> resultList = qr.query(sql, new ArrayListHandler());
				for (Object[] results : resultList){
					putDataIntoMessageDTM(results);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void putDataIntoMemberDTM(Object[] results){
		Vector vector = new Vector();
		for (int i = 0; i < results.length - 3; i++){
			vector.add(results[i]);
		}
		memberDTM.addRow(vector);
	}

	private void putDataIntoMinisterDTM(Object[] results){
		Vector vector = new Vector();
		for (int i = 0; i < results.length - 3; i++){
			vector.add(results[i]);
		}
		ministerDTM.addRow(vector);
	}

	private void putDataIntoMessageDTM(Object[] results){
		Vector vector = new Vector();
		for (int i = 0; i < results.length; i++){
			vector.add(results[i]);
		}
		messageDTM.addRow(vector);
	}

	public void display(){
		init();
		setAttribute();
		addTitle();
		addCompoennt();
		addData();
		this.setSize((int)(ScreenUtils.WIDTH*0.7), (int)(ScreenUtils.HEIGHT*0.8));
		this.setLocationRelativeTo(null);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setVisible(true);
	}
}
