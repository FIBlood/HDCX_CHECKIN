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
 * 签到对话框中的值班周期下拉列表监听器
 * Created by Kevin on 2017/3/4.
 */
public class WorkWeekBoxListener implements ItemListener {

	private JComboBox<String> workTimeBox;
	private JComboBox<String> workWeekBox;
	private DefaultListModel<String> dlm;

	public WorkWeekBoxListener(JComboBox<String> workTimeBox, JComboBox<String> workWeekBox, DefaultListModel<String> dlm) {
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
					List<Object[]> resultList = qr.query(sql, new ArrayListHandler(), (String)workTimeBox.getSelectedItem(), e.getItem());
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
