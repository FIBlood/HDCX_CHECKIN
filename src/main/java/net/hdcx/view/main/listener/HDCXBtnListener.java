package net.hdcx.view.main.listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 主面板中的黑大创协官网按钮监听器
 * Created by Kevin on 2017/3/2.
 */
public class HDCXBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Desktop dp = Desktop.getDesktop();
		URI uri = null;
		try {
			uri = new URI("http://hdcx.net");
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		try {
			dp.browse(uri);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
