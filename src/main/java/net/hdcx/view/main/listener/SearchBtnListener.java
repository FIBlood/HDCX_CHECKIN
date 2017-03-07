package net.hdcx.view.main.listener;

import net.hdcx.view.search.SearchDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主面板中的搜索按钮监视器
 * Created by Kevin on 2017/3/1.
 */
public class SearchBtnListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		SearchDialog searchDialog = new SearchDialog();
		searchDialog.display();
	}
}
