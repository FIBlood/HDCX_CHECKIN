package net.hdcx.view.main;

import net.hdcx.bean.Notice;
import net.hdcx.utils.DBUtils;
import net.hdcx.utils.ImageIconUtils;
import net.hdcx.utils.NoticeList;
import net.hdcx.view.IWindow;
import net.hdcx.view.main.listener.*;
import net.hdcx.view.main.listener.menulistener.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 主面板
 * Created by Kevin on 2017/2/27.
 */
public class MainFrame extends JFrame implements IWindow {

	private JPanel centerPanel;
	private JMenuBar menuBar = null;
	private JMenu[] menus = {
			new JMenu("文件(F)"),
			new JMenu("签到(Q)"),
			new JMenu("浏览(C)"),
			new JMenu("录入信息(L)"),
			new JMenu("添加(A)"),
			new JMenu("查询值班信息(Z)"),
			new JMenu("设置(S)"),
			new JMenu("帮助(H)")
	};
	private JMenuItem[] menuItem0 = {
			new JMenuItem("导出成员信息到Excel")
	};
	private JMenuItem[] menuItem1 = {
			new JMenuItem("签到"),
			new JMenuItem("签退")
	};
	private JMenuItem[] menuItem2 = {
			new JMenuItem("常务办公室"),
			new JMenuItem("人事办公室"),
			new JMenuItem("园区办公室"),
			new JMenuItem("新闻办公室"),
			new JMenuItem("公关部"),
			new JMenuItem("宣传部"),
			new JMenuItem("论坛部"),
			new JMenuItem("创业竞赛部"),
			new JMenuItem("实践服务部"),
			new JMenuItem("科技实践部"),
			new JMenuItem("公益项目部"),
			new JMenuItem("主持团"),
			new JMenuItem("留言")
	};
	private JMenuItem[] menuItem3 = {
			new JMenuItem("部员录入"),
			new JMenuItem("部长录入")
	};
	private JMenuItem[] menuItem4 = {
			new JMenuItem("公告")
	};
	private JMenuItem[] menuItem5 = {
			new JMenuItem("查询值班情况")
	};
	private JMenuItem[] menuItem6 = {
			new JMenuItem("人事管理设置")
	};
	private JMenuItem[] menuItem7 = {
			new JMenuItem("关于签到软件")
	};
	private LeftPanel leftPanel = null;
	private RightPanel rightPanel = null;
	private Font menuBarFont = new Font("Microsoft Yahei", Font.PLAIN, 14);
	private Font menuItemFont = new Font("Microsoft Yahei", Font.PLAIN, 12);
	private Image image = null;

	public MainFrame() {
		this.setTitle("创协签到软件");
	}

	public MainFrame(String title) {
		super(title);
	}

	private void init() {
		menuBar = new JMenuBar();
		leftPanel = new LeftPanel();
		rightPanel = new RightPanel();
		image = new ImageIcon("res/images/background.jpg").getImage();
		centerPanel = new BackgroundPanel(this, image);
	}

	private void setAttribute() {
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new GridLayout(0, 2, 0, 0));
		leftPanel.setOpaque(false);
		rightPanel.setOpaque(false);
	}

	private void addComponent() {
		for (int i = 0; i < menus.length; i++) {
			menuBar.add(menus[i]);
			menus[i].setFont(menuBarFont);
		}

		for (int i = 0; i < menuItem0.length; i++) {
			menus[0].add(menuItem0[i]);
			menuItem0[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem1.length; i++) {
			menus[1].add(menuItem1[i]);
			menuItem1[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem2.length; i++) {
			menus[2].add(menuItem2[i]);
			menuItem2[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem3.length; i++) {
			menus[3].add(menuItem3[i]);
			menuItem3[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem4.length; i++) {
			menus[4].add(menuItem4[i]);
			menuItem4[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem5.length; i++) {
			menus[5].add(menuItem5[i]);
			menuItem5[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem6.length; i++) {
			menus[6].add(menuItem6[i]);
			menuItem6[i].setFont(menuItemFont);
		}
		for (int i = 0; i < menuItem7.length; i++) {
			menus[7].add(menuItem7[i]);
			menuItem7[i].setFont(menuItemFont);
		}
		this.getContentPane().add(menuBar, BorderLayout.NORTH);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
	}

	private void addListener() {
		menuItem0[0].addActionListener(new ExportListener(this));
		menuItem1[0].addActionListener(new CheckinBtnListener());
		menuItem1[1].addActionListener(new CheckoutBtnListener());
		for (int i = 0, length = menuItem2.length; i < length; i++) {
			menuItem2[i].addActionListener(new SkimListener());
		}
		menuItem3[0].addActionListener(new AddPeopleListener());
		menuItem3[1].addActionListener(new AddPeopleListener());
		menuItem4[0].addActionListener(new PublishNoticeBtnListener());
		menuItem5[0].addActionListener(new CheckBtnListener());
		menuItem6[0].addActionListener(new ManageSettingListener());
		menuItem7[0].addActionListener(new AboutListener());
	}

	private void setMenuMnemonic() {
		menus[0].setMnemonic(KeyEvent.VK_F);
		menus[1].setMnemonic(KeyEvent.VK_Q);
		menus[2].setMnemonic(KeyEvent.VK_C);
		menus[3].setMnemonic(KeyEvent.VK_L);
		menus[4].setMnemonic(KeyEvent.VK_A);
		menus[5].setMnemonic(KeyEvent.VK_Z);
		menus[6].setMnemonic(KeyEvent.VK_S);
		menus[7].setMnemonic(KeyEvent.VK_H);
	}

	private void getData() {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select content, publisher,date_format(publishTime,'%Y/%m/%d %H:%i') as publishTime, date_format(deadline,'%Y/%m/%d %H:%i') as deadline from notices where now()<deadline";
		try {
			List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
			for (Iterator<Map<String, Object>> it = mapList.iterator(); it.hasNext(); ) {
				Map<String, Object> map = it.next();
				String content = (String) map.get("content");
				String publisher = (String) map.get("publisher");
				String publishTime = (String) map.get("publishTime");
				String deadline = (String) map.get("deadline");
				Notice notice = new Notice();
				notice.setContent(content);
				notice.setPublisher(publisher);
				notice.setPublishTime(publishTime);
				notice.setDeadline(deadline);
				NoticeList.getNoticeList().add(notice);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			JOptionPane.showMessageDialog(this, "获取公告数据失败");
		}
		ShowPublishNoticePanel noticePanel = RightPanel.getNoticePanel();
		noticePanel.getData();
		noticePanel.addToPanel();
	}

	public void display() {
		init();
		setAttribute();
		addComponent();
		setMenuMnemonic();
		addListener();
		getData();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIconUtils.setDefaultImageIcon(this);
		this.setSize((int) (image.getWidth(this) * 0.6), (int) (image.getHeight(this) * 0.8));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private class BackgroundPanel extends JPanel {
		private ImageObserver observer;
		private Image image;

		public BackgroundPanel(ImageObserver observer, Image image) {
			this.observer = observer;
			this.image = image;
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, observer);
		}

	}
}
