package net.hdcx.view.main.listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 人人网入口按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class RenRenBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		Desktop dp = Desktop.getDesktop();
		URI uri = null;
		try {
			uri = new URI("http://page.renren.com/600008318");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dp.browse(uri);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
