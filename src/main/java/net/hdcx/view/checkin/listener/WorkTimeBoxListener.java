package net.hdcx.view.checkin.listener;

import net.hdcx.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

/**
 * 签到对话框中的班次下拉列表监听器
 * Created by Kevin on 2017/3/4.
 */
public class WorkTimeBoxListener implements ItemListener {

	private JComboBox<String> workTimeBox;
	private DefaultListModel<String> dlm;
	private JComboBox<String> workWeekBox;

	public WorkTimeBoxListener(JComboBox<String> workTimeBox, JComboBox<String> workWeekBox, DefaultListModel<String> dlm) {
		this.workTimeBox = workTimeBox;
		this.workWeekBox = workWeekBox;
		this.dlm = dlm;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED){
			QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
			String table = "members";
			dlm.clear();
			for (int count = 0; count < 2; table="ministers", count++){
				String sql = "select name, studentId from " + table + " where workTime=? and workWeek=?";
				try {
					List<Object[]> resultList = qr.query(sql, new ArrayListHandler(), e.getItem(), (String)workWeekBox.getSelectedItem());
					for (Object[] result : resultList){
						dlm.addElement(result[0] + " " + result[1]);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
