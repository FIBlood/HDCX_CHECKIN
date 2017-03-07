package net.hdcx.view.main.listener.menulistener;

import net.hdcx.view.main.menu.Exporter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 菜单中的导出监听器
 * Created by Kevin on 2017/3/4.
 */
public class ExportListener implements ActionListener {

	private Component parentComponent;

	public ExportListener(Component parentComponent) {
		this.parentComponent = parentComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Exporter(parentComponent);
	}
}
