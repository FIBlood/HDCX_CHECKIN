package net.hdcx.view.search.listener;

import net.hdcx.view.search.SearchResultDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 搜索对话框中的的搜索按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class SearchBtnListener implements ActionListener {
	private JDialog dialog;

	public SearchBtnListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SearchResultDialog searchResultDialog = new SearchResultDialog();
		dialog.setVisible(false);
		searchResultDialog.display();
	}
}
