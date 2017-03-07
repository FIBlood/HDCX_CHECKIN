package net.hdcx.view.main;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kevin on 2017/2/27.
 */
public class TimerPanel extends JPanel{
	private JLabel weekdayLabel = null;
	private JLabel timeLabel = null;
	private JPanel topPanel = null;
	private JPanel bottomPanel = null;
	private Timer timer = null;
	private Font font = null;
	private final int ONE_SECOND = 1000;

	public TimerPanel(){
		this.initialize();
		this.setAttribute();
		this.addComponent();
		this.executeSchedule();
	}

	public void initialize(){
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		timer = new Timer();
		timeLabel = new JLabel("" ,SwingConstants.CENTER);
		weekdayLabel = new JLabel("", SwingConstants.CENTER);
		font = new Font("Microsoft Yahei", Font.PLAIN, 20);
	}

	public void setAttribute(){
		timeLabel.setFont(font);
		weekdayLabel.setFont(font);
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(new Color(255, 255, 255));
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		topPanel.setLayout(new GridLayout(0, 1));
		bottomPanel.setLayout(new GridLayout(0, 1));
	}

	public void executeSchedule(){
		timer.scheduleAtFixedRate(new TimerTask() {
			String weekday = null;
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			@Override
			public void run() {
				if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 2){
					weekday = "星期一";
				}else if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 3){
					weekday = "星期二";
				}else if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 4){
					weekday = "星期三";
				}else if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 5){
					weekday = "星期四";
				}else if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 6){
					weekday = "星期五";
				}else if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 7){
					weekday = "星期六";
				}else{
					weekday = "星期日";
				}
				weekdayLabel.setText(weekday);
				timeLabel.setText(sdf.format(Calendar.getInstance().getTime()));
			}
		}, new Date(), ONE_SECOND);
	}

	public void addComponent(){
		topPanel.add(weekdayLabel);
		bottomPanel.add(timeLabel);
		this.add(topPanel);
		this.add(bottomPanel);
	}
}
