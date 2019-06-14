package mainui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;


public class MainUI {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("船只停靠管理可视化");
		frame.setBounds(100, 100, 840, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("欢迎使用，本管理系统，没有使用任何框架，界面可随意拖动，界面与数据操作分离方便修改");
		panel.add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		Pane1 panel_1 = new Pane1();
		tabbedPane.addTab("泊位管理", null, panel_1, null);
		panel_1.setLayout(null);//清空布局
		
		
		Pane2 panel_2 = new Pane2();
		tabbedPane.addTab("船只管理", null, panel_2, null);
		panel_2.setLayout(null);//清空布局
		

		Pane3 panel_3 = new Pane3();
		tabbedPane.addTab("停靠指定", null, panel_3, null);
		panel_3.setLayout(null);//清空布局
		

		Pane4 panel_4 = new Pane4();
		tabbedPane.addTab("查看甘特图", null, panel_4, null);
		panel_4.setLayout(null);//清空布局
		
	}

}
