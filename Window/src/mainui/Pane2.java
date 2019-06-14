package mainui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import consql.Dao;


public class Pane2 extends JPanel{

	private static final long serialVersionUID = 1L;
	Pane2(){
		System.out.println("面板2被调用");
		initialize();
	}
	
	private void initialize() {
		Dao db = new Dao();
		db.connSQL();
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(300);
		this.add(splitPane);
		
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerLocation(300);
		//splitPane_1.setEnabled(false);//竖条 不可拖动
		splitPane.setLeftComponent(splitPane_1);
		
		JPanel panel = new JPanel();
		splitPane_1.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblid = new JLabel("船只ID");
		lblid.setBounds(6, 25, 61, 16);
		panel.add(lblid);
		
		JLabel label = new JLabel("船只名称");
		label.setBounds(6, 70, 61, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("到达时间");
		label_1.setBounds(6, 115, 61, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("离开时间");
		label_2.setBounds(6, 160, 61, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("停靠偏好");
		label_3.setBounds(6, 205, 61, 16);
		panel.add(label_3);
		
		JTextField textField = new JTextField();
		textField.setBounds(80, 20, 200, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(80, 65, 200, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(80, 110, 200, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(80, 155, 200, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JTextField textField_4 = new JTextField();
		textField_4.setBounds(80, 200, 200, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("提示：\n1、在查询时，ID文本框为空查询的是全部数据\n\t输入ID时查询的时一条数据 \n\n2、输入时间格式：2019-06-08 01:12:11");
		textPane.setBounds(6, 238, 270, 100);
		panel.add(textPane);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		
		JButton btnNewButton = new JButton("船只添加");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("船只删除");
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("船只修改");
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("船只查询");
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("操作帮助");
		panel_2.add(btnNewButton_4);
		
		btnNewButton.addActionListener(new ActionListener()//添加
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			            try{	
			            
			            	String id = textField.getText();		// 取得用ID
			            	String name = textField_1.getText();	// 取得用name
			            	String arrive = textField_2.getText();	// 取得用arrive_time
			            	String leave = textField_3.getText();		// 取得用leave_time
			            	String perfer = textField_4.getText();	// 取得偏好
			            	
			            	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss[]");	
			            	if((id.length()==0)||(name.length()==0)||(arrive.length()==0)||(leave.length()==0)||(perfer.length()==0)){
			            		JOptionPane.showMessageDialog(null,"插入数据为空","插入数据失败",JOptionPane.PLAIN_MESSAGE); 
						        
			            	}else{
			            		Timestamp ar =  java.sql.Timestamp.valueOf(arrive);
				 	          	Timestamp lv =  java.sql.Timestamp.valueOf(leave);

				            	String sql1 = "insert into ship values('"+id+"','"+name+"','"+ar+"','"+lv+"','"+perfer+"','')";
				            	boolean tf;
				            	tf=db.insertSQL(sql1);
				            	if (tf){
				            		JOptionPane.showMessageDialog(null,"插入id:"+id+" 泊位:"+name+" 成功","插入数据",JOptionPane.PLAIN_MESSAGE); 
				            	}else{
				            		JOptionPane.showMessageDialog(null,"插入id:"+id+" 泊位:"+name+" 失败","插入数据",JOptionPane.PLAIN_MESSAGE); 
				            	}
			            	}
			               		System.out.print("添加");
			                }catch(Exception e1){
			                	System.out.print("确认添加,出错");
			                }
			           }
			      }
			 );
		
		
		btnNewButton_1.addActionListener(new ActionListener()//删除
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			            try{
			            		String id = textField.getText();// 取得用ID

			            		if (id.length()==0){//
			            			JOptionPane.showMessageDialog(null,"输入为空","请输入数据",JOptionPane.PLAIN_MESSAGE); 
			            		}else{
			            			String sql3 = "delete from ship where id='"+id+"';";
				            		boolean dl;
				            		dl=db.deleteSQL(sql3);
				            		if (dl){
				            			JOptionPane.showMessageDialog(null,"删除id:"+id+"成功","删除数据",JOptionPane.PLAIN_MESSAGE); 
				            		}else{
				            			JOptionPane.showMessageDialog(null,"删除id:"+id+"失败","删除数据",JOptionPane.PLAIN_MESSAGE); 
				            		}
			            		}
			               		System.out.print("面板2 删除");
			                }catch(Exception e1){
			                	System.out.print("面板2 删除,出错");
			                }
			           }
			      }
			 );
		btnNewButton_2.addActionListener(new ActionListener()//修改
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			            try{	
			            		String id = textField.getText();			// 取得用ID
			            		String name = textField_1.getText();		// 取得用name
			            		String arrive = textField_2.getText();		// 取得用arrive_time
			            		String leaves = textField_3.getText();		// 取得用leave_time
			            		String perfer = textField_4.getText();		// 取得偏好
			            		
			            		System.out.print("id="+id+"name="+name+"arrive="+arrive+"leave"+leaves+"perfer"+perfer);
			            		
			            		//2012-12-12 01:12:11
			            		
			            		if (name.length()!=0){//修改name
			            			System.out.print("--name修改--");
			            			String sql = "update ship set name='"+name+"' where id='"+id+"';";
			            			boolean na;
			            			na=db.updateSQL(sql);
			            			if (na){
					            		JOptionPane.showMessageDialog(null,"更改名字成功","更新数据",JOptionPane.PLAIN_MESSAGE); 
					            	}else{
					            		JOptionPane.showMessageDialog(null,"更改名字失败","更新数据",JOptionPane.PLAIN_MESSAGE); 
					            	}

			            		}
			            		if (arrive.length()!=0){//修改到达时间
			            			System.out.print("--arrive修改--");
			            			String sql ="update ship set arrive='"+arrive+"' where id='"+id+"';";
			            			db.updateSQL(sql);
			            		}
			          //为什么不用leave字段！ leave字段不能更新数据库
			            		if (leaves.length()!=0){//修改leave时间
			            			System.out.print("--leaves修改--");
			            			String sql ="update ship set leaves='"+leaves+"' where id='"+id+"';";
			            			db.updateSQL(sql);
			            		}
			    
			            		if (perfer.length()!=0){//修改偏好位置
			            			System.out.print("--prefer修改--");
			            			String sql = "update ship set perfer='"+perfer+"' where id='"+id+"';";
			            			db.updateSQL(sql);
			            		}

			               		System.out.print("面板2 修改");
			                }catch(Exception e1){
			                	System.out.print("面板2 修改,出错");
			                }
			           }
			      }
			 );
		btnNewButton_3.addActionListener(new ActionListener()//查询按钮
                {
                    public void actionPerformed(ActionEvent e4)
                    {
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
                   			table.getColumnModel().getColumn(0).setPreferredWidth(30);//设置列宽度比例
                   			table.getColumnModel().getColumn(1).setPreferredWidth(30);
                   			table.getColumnModel().getColumn(2).setPreferredWidth(120);
                   			table.getColumnModel().getColumn(3).setPreferredWidth(120);
                   			table.getColumnModel().getColumn(4).setPreferredWidth(30);
                   			table.getColumnModel().getColumn(5).setPreferredWidth(30);
                   			scrollPane.setViewportView(table);            			
                   			System.out.println("面板2查询ship"+row+"行，完成查询");
                    	}catch(Exception e1){
                    		System.out.println("面板2查询ship,查询出错");
                    	}
                    }
                }
            );
		btnNewButton_4.addActionListener(new ActionListener()//帮助
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			            try{
			            		JOptionPane.showMessageDialog(null,"在修改数据时 ID不能为空，其他数据可以选填","修改提示",JOptionPane.PLAIN_MESSAGE); 
				            
			               		System.out.print("面板2 帮助");
			                }catch(Exception e1){
			                	System.out.print("面板2 帮助,出错");
			                }
			           }
			      }
			 );
		
		this.addComponentListener(new ComponentAdapter() {//拖动窗口监听
            public void componentResized(ComponentEvent e) {  
            	int width=MainUI.frame.getWidth();		//获取窗口宽度
            	int height=MainUI.frame.getHeight();	//获取窗口高度
            	splitPane.setBounds(0, 0, width-20, height);
            	splitPane.setDividerLocation(height-140);	
            }  
        }); 
		
	}
	
}
