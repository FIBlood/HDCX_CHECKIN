package net.hdcx.view.main.listener.menulistener;

import net.hdcx.view.main.menu.ManageSettingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 管理设置对话框中的保存按钮监听器
 * Created by Kevin on 2017/3/5.
 */
public class SaveBtnListener implements ActionListener {

	ManageSettingDialog dialog;

	public SaveBtnListener(ManageSettingDialog dialog){
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isOK = true;
		String late = dialog.getLateField().getText();
		String kuang = dialog.getKuangField().getText();
		String[] time = new String[20];
		Properties p = dialog.getP();
		int length = time.length;
		int index = 0;
		for(int i = 0;i<5;i++){
			time[i] = dialog.getStartHourField()[index].getText();
			index++;
		}
		index = 0;
		for(int i = 5;i<10;i++){
			time[i] = dialog.getStartMinuteField()[index].getText();
			index++;
		}
		index = 0;
		for(int i = 10;i<15;i++){
			time[i] = dialog.getEndHourField()[index].getText();
			index++;
		}
		index = 0;
		for(int i = 15;i<length;i++){
			time[i] = dialog.getEndMinuteField()[index].getText();
			index++;
		}

		//判断文本域是否为合法的数字
		int[] numbers = new int[length];

		try {
			for(int i = 0;i<length;i++){
				numbers[i] = Integer.parseInt(time[i]);
			}

			for(int i = 0;i<5;i++){
				if(numbers[i] >= 24 || numbers[i] < 0){
					isOK = false;
				}
			}
			for(int i = 5;i<10;i++){
				if(numbers[i] >=60 || numbers[i] < 0){
					isOK = false;
				}
			}
			for(int i = 10;i<15;i++){
				if(numbers[i] >= 24 || numbers[i] < 0){
					isOK = false;
				}
			}
			for(int i = 15;i<length;i++){
				if(numbers[i] >= 60 || numbers[i] < 0){
					isOK = false;
				}
			}

			int lateTime = Integer.parseInt(late);
			if(lateTime >= 60 || lateTime < 0){
				isOK = false;
			}

			int kuangTime = Integer.parseInt(kuang);
			if(kuangTime >= 60 || kuangTime < 0){
				isOK = false;
			}

		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(dialog, "输入了非法的时间值");
			isOK = false;
		}

		if(isOK){
			p.setProperty("one_start_hour", time[0]);
			p.setProperty("two_start_hour", time[1]);
			p.setProperty("three_start_hour", time[2]);
			p.setProperty("four_start_hour", time[3]);
			p.setProperty("five_start_hour", time[4]);

			p.setProperty("one_start_minute", time[5]);
			p.setProperty("two_start_minute", time[6]);
			p.setProperty("three_start_minute", time[7]);
			p.setProperty("four_start_minute", time[8]);
			p.setProperty("five_start_minute", time[9]);

			p.setProperty("one_end_hour", time[10]);
			p.setProperty("two_end_hour", time[11]);
			p.setProperty("three_end_hour", time[12]);
			p.setProperty("four_end_hour", time[13]);
			p.setProperty("five_end_hour", time[14]);

			p.setProperty("one_end_minute", time[15]);
			p.setProperty("two_end_minute", time[16]);
			p.setProperty("three_end_minute", time[17]);
			p.setProperty("four_end_minute", time[18]);
			p.setProperty("five_end_minute", time[19]);

			p.setProperty("late", late);
			p.setProperty("kuang", kuang);

			try {
				p.store(new FileOutputStream("res/properties/setting.properties"), "time setting");
				JOptionPane.showMessageDialog(dialog, "保存成功");
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(dialog, "保存失败");
			}
		}else{
			JOptionPane.showMessageDialog(dialog, "输入了非法的时间值");
		}
	}
}
