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

	private JTextField nameField;
	private JTextField idField;
	private JDialog dialog;

	public SearchBtnListener(JDialog dialog, JTextField nameField, JTextField idField) {
		this.dialog = dialog;
		this.nameField = nameField;
		this.idField = idField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String id = idField.getText();
		SearchResultDialog searchResultDialog = new SearchResultDialog(name, id);
		dialog.setVisible(false);
		searchResultDialog.display();
	}
}
