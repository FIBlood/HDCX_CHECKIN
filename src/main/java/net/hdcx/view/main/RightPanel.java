package net.hdcx.view.main;

import javax.swing.*;
import java.awt.*;

/**
 * 主面板中的右面板
 * Created by Kevin on 2017/2/27.
 */
public class RightPanel extends JPanel {
	private JScrollPane noticeScrollPane = null;
	private JScrollPane ministerScrollPane = null;
	private JScrollPane memberScrollPane = null;
	private JLabel noticeLabel = null;
	private JLabel ministerLabel = null;
	private JLabel memberLabel = null;
	private static ShowPublishNoticePanel publishNoticePanel = null;
	private static ShowOnDutyMinistersPanel onDutyMinistersPanel = null;
	private static ShowOnDutyMembersPanel onDutyMembersPanel = null;
	private Font labelFont = null;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel noticePanel = null;
	private JPanel ministerPanel = null;
	private JPanel memberPanel = null;

	public RightPanel(){
		this.init();
		this.setAttribute();
		this.addComponent();
	}

	private void init(){
		noticeScrollPane = new JScrollPane();
		ministerScrollPane = new JScrollPane();
		memberScrollPane = new JScrollPane();
		noticeLabel = new JLabel("公告",SwingConstants.LEFT);
		ministerLabel = new JLabel("值班部长",SwingConstants.LEFT);
		memberLabel = new JLabel("值班干事",SwingConstants.LEFT);
		publishNoticePanel = new ShowPublishNoticePanel();
		onDutyMinistersPanel = new ShowOnDutyMinistersPanel();
		onDutyMembersPanel = new ShowOnDutyMembersPanel();
		labelFont = new Font("Microsoft Yahei", Font.PLAIN, 20);
		noticePanel = new JPanel();
		ministerPanel = new JPanel();
		memberPanel = new JPanel();
	}

	private void setAttribute(){
		this.setLayout(new GridLayout(0, 2));
		this.setOpaque(false);
		noticeLabel.setFont(labelFont);
		ministerLabel.setFont(labelFont);
		memberLabel.setFont(labelFont);
		noticePanel.setLayout(new BoxLayout(noticePanel, BoxLayout.Y_AXIS));
		ministerPanel.setLayout(new BoxLayout(ministerPanel, BoxLayout.Y_AXIS));
		memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
		noticePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ministerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		memberPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	private void addComponent(){
		//辅助面板
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel10 = new JPanel();
		JPanel panel11 = new JPanel();
		JPanel panel12 = new JPanel();
		JPanel panel13 = new JPanel();
		JPanel panel14 = new JPanel();
		JPanel panel15 = new JPanel();
		JPanel panel16 = new JPanel();

		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		panel4.setOpaque(false);
		panel5.setOpaque(false);
		panel6.setOpaque(false);
		panel7.setOpaque(false);
		panel8.setOpaque(false);
		panel9.setOpaque(false);
		panel10.setOpaque(false);
		panel11.setOpaque(false);
		panel12.setOpaque(false);
		panel13.setOpaque(false);
		panel14.setOpaque(false);
		panel15.setOpaque(false);
		panel16.setOpaque(false);

		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new GridLayout(0, 2));
		panel3.setLayout(new BorderLayout());
		panel4.setLayout(new BorderLayout());
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
		panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
		panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));
		panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));
		panel11.setLayout(new BoxLayout(panel11, BoxLayout.X_AXIS));
		panel12.setLayout(new BoxLayout(panel12, BoxLayout.X_AXIS));
		panel13.setLayout(new BoxLayout(panel13, BoxLayout.Y_AXIS));
		panel14.setLayout(new BoxLayout(panel14, BoxLayout.Y_AXIS));
		panel15.setLayout(new BoxLayout(panel15, BoxLayout.X_AXIS));
		panel16.setLayout(new BoxLayout(panel16, BoxLayout.X_AXIS));

		panel5.add(Box.createVerticalStrut((int)(d.height*0.16)));
		panel6.add(Box.createVerticalStrut((int)(d.height*0.3)));
		panel7.add(Box.createHorizontalStrut(5));
		panel8.add(Box.createHorizontalStrut(5));
		panel1.add(panel5, BorderLayout.NORTH);
		panel1.add(panel6, BorderLayout.SOUTH);
		panel1.add(panel7, BorderLayout.EAST);
		panel1.add(panel8, BorderLayout.WEST);
		panel1.add(noticePanel,BorderLayout.CENTER);
		panel9.add(Box.createVerticalStrut((int)(d.height*0.16)));
		panel10.add(Box.createVerticalStrut((int)(d.height*0.3)));
		panel11.add(Box.createHorizontalStrut(5));
		panel12.add(Box.createHorizontalStrut(5));
		panel3.add(panel9, BorderLayout.NORTH);
		panel3.add(panel10, BorderLayout.SOUTH);
		panel3.add(panel11, BorderLayout.EAST);
		panel3.add(panel12, BorderLayout.WEST);
		panel2.add(panel3);
		panel13.add(Box.createVerticalStrut((int)(d.height*0.16)));
		panel14.add(Box.createVerticalStrut((int)(d.height*0.3)));
		panel15.add(Box.createHorizontalStrut(5));
		panel16.add(Box.createHorizontalStrut(5));
		panel4.add(panel13, BorderLayout.NORTH);
		panel4.add(panel14, BorderLayout.SOUTH);
		panel4.add(panel15, BorderLayout.EAST);
		panel4.add(panel16, BorderLayout.WEST);
		panel2.add(panel4);
		panel3.add(ministerPanel, BorderLayout.CENTER);
		panel4.add(memberPanel, BorderLayout.CENTER);
		noticePanel.add(noticeLabel);
		noticePanel.add(publishNoticePanel);
		noticePanel.add(noticeScrollPane);
		noticeScrollPane.getViewport().add(publishNoticePanel);
		ministerPanel.add(ministerLabel);
		ministerPanel.add(onDutyMinistersPanel);
		memberPanel.add(memberLabel);
		memberPanel.add(onDutyMembersPanel);
		this.add(panel1);
		this.add(panel2);
	}

	public static ShowPublishNoticePanel getNoticePanel(){
		return publishNoticePanel;
	}

	public static ShowOnDutyMinistersPanel getMinisterPanel(){
		return onDutyMinistersPanel;
	}

	public static ShowOnDutyMembersPanel getMemberPanel(){
		return onDutyMembersPanel;
	}
}
