package net.hdcx.view.main.listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 新浪微博按钮监听器
 * Created by Kevin on 2017/3/1.
 */
public class SinaBtnListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		URI uri = null;
		try {
			uri = new URI("http://weibo.com/heidachuangxie");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Desktop dp = Desktop.getDesktop();
		try {
			dp.browse(uri);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
