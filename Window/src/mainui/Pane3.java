package mainui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import consql.Dao;
import tool.Appoint;




public class Pane3 extends JPanel{

	private static final long serialVersionUID = 1L;
	Pane3(){
		System.out.println("面板3被调用");
		initialize();
	}
	
	private void initialize() {
		Dao db = new Dao();
		db.connSQL();
		
	
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(80);//水平条
		this.add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane.setRightComponent(splitPane_1);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblid = new JLabel("船只ID");
		lblid.setBounds(6, 19, 61, 16);
		panel.add(lblid);
		
		JLabel lblid_1 = new JLabel("泊位号");
		lblid_1.setBounds(237, 19, 61, 16);
		panel.add(lblid_1);
		
		JTextField textField = new JTextField();
		textField.setBounds(74, 14, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(310, 14, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("查询船只");
		button.setBounds(6, 58, 102, 29);
		panel.add(button);
		
		JButton button_1 = new JButton("查询港口");
		button_1.setBounds(120, 58, 117, 29);
		panel.add(button_1);
		
		JButton button_2 = new JButton("手动指定");
		button_2.setBounds(247, 58, 117, 29);
		panel.add(button_2);
		
		JButton button_3 = new JButton("自动指定");
		button_3.setBounds(366, 58, 117, 29);
		panel.add(button_3);
		
		JButton button_4 = new JButton("指定帮助");
		button_3.setBounds(366, 58, 117, 29);
		panel.add(button_4);
		
		JScrollPane scrollPane = new JScrollPane();//添加带滚动条的容器在左下
		splitPane_1.setLeftComponent(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();//添加带滚动条的容器在右下
		splitPane_1.setRightComponent(scrollPane_1);
		
		button.addActionListener(new ActionListener() {//查询船只
			public void actionPerformed(ActionEvent e) {
				try{	
           			String id = textField.getText();			// 取得用ID
            		
           			String[] columnNames = { "Id", "泊位名","到达时间","离开时间","偏好位置","停靠位置"}; 
           			String sql2;
           			if (id.length()==0){
           				sql2 = "select * from ship";
           			}else{
           				sql2 = "select * from ship where id='"+id+"'";
           			}
           			ResultSet rs = db.selectSQL(sql2);
           			rs.last() ; int row = rs.getRow(); rs.beforeFirst();//光标回滚  获取行数  光标回滚
           			String data[][]  =new String[row][6];          			
           			row=0;
           			while(rs.next()){
           				data[row][0]=rs.getString(1);
           				data[row][1]=rs.getString(2);
           				data[row][2]=rs.getString(3);
           				data[row][3]=rs.getString(4);
           				data[row][4]=rs.getString(5);
           				data[row][5]=rs.getString(6);
           				row++;
           			}          			
           			JTable table = new JTable(data, columnNames);  
           			table.getColumnModel().getColumn(0).setPreferredWidth(25);//设置列宽度比例
           			table.getColumnModel().getColumn(1).setPreferredWidth(25);
           			table.getColumnModel().getColumn(2).setPreferredWidth(120);
           			table.getColumnModel().getColumn(3).setPreferredWidth(120);
           			table.getColumnModel().getColumn(4).setPreferredWidth(20);
           			table.getColumnModel().getColumn(5).setPreferredWidth(20);
           			scrollPane.setViewportView(table);            			//将表格添加到容器		
           			System.out.println("面板3查询ship"+row+"行，完成查询");
            	}catch(Exception e1){
            		System.out.println("面板3查询ship,查询出错");
            	}
				
			}
		});
		
		button_1.addActionListener(new ActionListener() {//查询港口
			public void actionPerformed(ActionEvent e) {
				try{	
           			String id = textField_1.getText();			// 取得用ID
            		
           			String[] columnNames = { "泊位名","泊位号"}; 
           			String sql2;
           			if (id.length()==0){
           				sql2 = "select * from port";
           			}else{
           				sql2 = "select * from ship where id='"+id+"'";
           			}
           			ResultSet rs = db.selectSQL(sql2);
           			rs.last() ; int row = rs.getRow(); rs.beforeFirst();//光标回滚  获取行数  光标回滚
           			String data[][]  =new String[row][2];          			
           			row=0;
           			while(rs.next()){
           				data[row][0]=rs.getString(2);
           				int num=rs.getInt(3);
           				data[row][1]=String.valueOf(num);   
           				row++;
           			}           			
           			JTable table = new JTable(data, columnNames);  	
           			scrollPane_1.setViewportView(table); //将表格添加到容器	
           			System.out.println("面板3查询port"+row+"行，完成查询");
            	}catch(Exception e1){
            		System.out.println("面板3查询port,查询出错");
            	}
				
			}
		});
		button_2.addActionListener(new ActionListener()//手动指定
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			    	  		String shipid = textField.getText();			// 取得用ID
			    	  		String portid = textField_1.getText();	
			    	  		if ((shipid.length()==0)||(portid.length()==0)){//
			    	  			JOptionPane.showMessageDialog(null,"输入为空","请输入ID",JOptionPane.PLAIN_MESSAGE); 
		            		}else{
		            			Appoint hand=new Appoint();
				            	boolean tf= hand.human_appoint(shipid, portid);
				            	if (tf){
				            		JOptionPane.showMessageDialog(null,"指定位置:"+shipid+"成功","指定位置",JOptionPane.PLAIN_MESSAGE); 
				            	}else{
				            		JOptionPane.showMessageDialog(null,"指定位置:"+shipid+"失败","指定位置",JOptionPane.PLAIN_MESSAGE); 
	            				}
		            		}
			           }
			      }
			 );
		
		button_3.addActionListener(new ActionListener()//自动指定
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			    	  		String shipid = textField.getText();			// 取得用ID
			    	  		if (shipid.length()==0){//
			    	  			JOptionPane.showMessageDialog(null,"输入为空","请输入ID",JOptionPane.PLAIN_MESSAGE); 
		            		}else{
		            			Appoint hand=new Appoint();
				            	boolean tf= hand.auto_appoint(shipid);
				            	if (tf){
				            		JOptionPane.showMessageDialog(null,"指定位置:"+shipid+"成功","指定位置",JOptionPane.PLAIN_MESSAGE); 
				            	}else{
				            		JOptionPane.showMessageDialog(null,"指定位置:"+shipid+"失败","指定位置",JOptionPane.PLAIN_MESSAGE); 
				            	}
		            		}
			           }
			      }
			 );
		
		button_4.addActionListener(new ActionListener()//帮助
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			    	  		
				            JOptionPane.showMessageDialog(null,"手动指定位置，要输入船只ID和泊位号，手动指定可能导致时间冲突。\n 自动指定输入ID即可 自动指定 不会出现时间冲突","指定位置",JOptionPane.PLAIN_MESSAGE); 
				           
			           }
			      }
			 );
		

		int between=11;	
		this.addComponentListener(new ComponentAdapter() {//拖动窗口监听
            public void componentResized(ComponentEvent e) {  
            	int width=MainUI.frame.getWidth();		//获取窗口宽度
            	int height=MainUI.frame.getHeight();	//获取窗口高度
            	splitPane.setBounds(0, 0, width-20, height);
            	

            	lblid.setBounds(width/between*2, 10, width/between, 20);
            	textField.setBounds(width/between*3, 10, width/between*2, 20);
            	lblid_1.setBounds(width/between*6, 10, width/between, 20);
            	textField_1.setBounds(width/between*7, 10, width/between*2, 20);
            	
            	button.setBounds(width/between*1, 40, 60, 20);
            	button_1.setBounds(width/between*3,40, 60, 20);
            	button_2.setBounds(width/between*5, 40, 60, 20);
            	button_3.setBounds(width/between*7, 40, 60, 20);
            	button_4.setBounds(width/between*9, 40, 60, 20);
       
            	splitPane_1.setDividerLocation(width/11*7);	//垂直条  黄金比例
            	splitPane.setBounds(0, 0, width-20, height-90);
            }  
            
        }); 
		
	}
	
}
