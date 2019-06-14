package mainui;

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
import javax.swing.JTable;
import javax.swing.JTextField;

import consql.Dao;

public class Pane1 extends JPanel{

	private static final long serialVersionUID = 1L;
	Pane1(){
		System.out.println("面板1被调用");
		initialize();
	}
	
	private void initialize() {
		Dao db = new Dao();
		db.connSQL();
		
		JLabel lblNewLabel_1 = new JLabel("泊位ID");
		lblNewLabel_1.setBounds(81, 32, 61, 16);		//初始位置
		this.add(lblNewLabel_1);
		
		JTextField textField = new JTextField();
		textField.setBounds(190, 27, 130, 26);			//初始位置
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("泊位名称");
		lblNewLabel_2.setBounds(81, 73, 61, 16);		//初始位置
		this.add(lblNewLabel_2);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(190, 65, 130, 26);		//初始位置
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.setBounds(44, 101, 55, 58);		//初始位置
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("查看");
		btnNewButton_1.setBounds(145, 103, 61, 58);		//初始位置
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.setBounds(259, 103, 61, 58);		//初始位置
		this.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("更改");
		btnNewButton_3.setBounds(345, 65, 78, 29);		//初始位置
		this.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("帮助");
		btnNewButton_4.setBounds(362, 101, 61, 58);		//初始位置
		this.add(btnNewButton_4);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 100, 570, 200);
	//	scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	//水平滚动条
	//	scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);		//垂直滚动条
		this.add(scrollPane); 
		
		btnNewButton.addActionListener(new ActionListener()//添加按钮
                {
                    public void actionPerformed(ActionEvent e4)
                    {
                   		try{	
                   			String id = textField.getText();		// 取得用文本框ID
                   			String name = textField_1.getText();		// 取得用name 
                   			if((id.length()==0)||(name.length()==0)){
                   				JOptionPane.showMessageDialog(null,"插入数据为空成功","插入数据失败",JOptionPane.PLAIN_MESSAGE); 
                   			}else{
                   				//获取行数
                   				String sqlline = "select * from port";
                   			
                   				ResultSet rs = db.selectSQL(sqlline);
                   				rs.last() ; int row = rs.getRow()+1; rs.beforeFirst();//光标回滚  获取行数  光标回滚
                   				
                   				String sql1 = "insert into port(id,name,num_add) values("+id+",'"+name+"','"+row+"')";
                       			boolean tf;
    			            	tf=db.insertSQL(sql1);
    			            	if (tf){
    			            		JOptionPane.showMessageDialog(null,"插入id:"+id+" 泊位:"+name+" 成功","插入数据",JOptionPane.PLAIN_MESSAGE); 
    			            	}else{
    			            		JOptionPane.showMessageDialog(null,"插入id:"+id+" 泊位:"+name+" 失败","插入数据",JOptionPane.PLAIN_MESSAGE); 
    			            	}
                   			}          			
                   			System.out.println("添加执行完成");
                    	}catch(Exception e1){
                    		System.out.println("面板1,查询出错");
                    	}
                    }
                }
            );
		
		
		btnNewButton_1.addActionListener(new ActionListener()//查询按钮
                {
                    public void actionPerformed(ActionEvent e4)
                    {
                   		try{	
                   			String id = textField.getText();		// 取得用文本框ID
                   			String[] columnNames = { "Id", "泊位名","泊位编号"}; 
                   			String sql2;
                   			if (id.length()==0){
                   				sql2 = "select * from port";
                   			}else{
                   				sql2 = "select * from port where id='"+id+"'";
                   			}
                   			ResultSet rs = db.selectSQL(sql2);
                   			rs.last() ; int row = rs.getRow(); rs.beforeFirst();//光标回滚  获取行数  光标回滚
                   			String data[][]  =new String[row][3];          			
                   			row=0;
                   			while(rs.next()){
                   				data[row][0]=rs.getString(1);
                   				data[row][1]=rs.getString(2);
                   				int num=rs.getInt(3);
                   				data[row][2]=String.valueOf(num);   
                   				row++;
                   			}          			
                   			JTable table = new JTable(data, columnNames);  
                   			scrollPane.setViewportView(table);            			
                   			System.out.println("查询"+row+"行，完成查询");
                    	}catch(Exception e1){
                    		System.out.println("面板1,查询出错");
                    	}
                    }
                }
            );
		
		btnNewButton_2.addActionListener(new ActionListener()//监听删除泊位
				  {
				  public void actionPerformed(ActionEvent e4)
				       {
				        try{
				        	String id = textField.getText();// 取得用ID
				        	String sql3 = "delete from port where id='"+id+"';";
				        	boolean dl;
				        	dl=db.deleteSQL(sql3);
				        	if (dl){
			            		JOptionPane.showMessageDialog(null,"删除id:"+id+"成功","删除数据",JOptionPane.PLAIN_MESSAGE); 
			            	}else{
			            		JOptionPane.showMessageDialog(null,"删除id:"+id+"失败","删除数据",JOptionPane.PLAIN_MESSAGE); 
			            	}
				        	System.out.println("面板1 删除完成");
				           }catch(Exception e1){
				                System.out.println("面板1 删除出错");
				           }
				       }
				  }
			);
		btnNewButton_3.addActionListener(new ActionListener()//	数据修改
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			            try{	
			            		String id = textField.getText();			// 取得用ID
			            		String name = textField_1.getText();		// 取得用name
			            		
			            		if (name.length()!=0){						//修改name
			            			String sql = "update port set name='"+name+"' where id='"+id+"';";
			            			boolean na;
			            			na=db.updateSQL(sql);
			            			if (na){
					            		JOptionPane.showMessageDialog(null,"更改名字成功","更新数据",JOptionPane.PLAIN_MESSAGE); 
					            	}else{
					            		JOptionPane.showMessageDialog(null,"更改名字失败","更新数据",JOptionPane.PLAIN_MESSAGE); 
					            	}
			            		}
			               		System.out.print("面板1 更新完成");
			                }catch(Exception e1){
			                	System.out.print("面板1 更新出错");
			                }
			           }
			      }
			 );
		
		btnNewButton_4.addActionListener(new ActionListener()//	提示帮助
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			            try{	
			            		
					            JOptionPane.showMessageDialog(null,"在查询时，ID文本框为空查询的是全部数据\n输入ID时查询的时一条数据","查询提示",JOptionPane.PLAIN_MESSAGE); 
			               		System.out.print("面板1 帮助完成");
			                }catch(Exception e1){
			                	System.out.print("面板1 帮助出错");
			                }
			           }
			      }
			 );
		
		
		int between=11;									//定义分段
		this.addComponentListener(new ComponentAdapter() {//拖动窗口监听
            public void componentResized(ComponentEvent e) {  
            	int width=MainUI.frame.getWidth();		//获取窗口宽度
            	int height=MainUI.frame.getHeight();	//获取窗口高度
            	
            	
            	lblNewLabel_1.setBounds(width/between*2, 10, width/between, 20);
            	textField.setBounds(width/between*3, 10, width/between*2, 20);
            	lblNewLabel_2.setBounds(width/between*6, 10, width/between, 20);
            	textField_1.setBounds(width/between*7, 10, width/between*2, 20);
            	
            	btnNewButton.setBounds(width/between*1, 40, 60, 20);
            	btnNewButton_1.setBounds(width/between*3,40, 60, 20);
            	btnNewButton_2.setBounds(width/between*5, 40, 60, 20);
            	btnNewButton_3.setBounds(width/between*7, 40, 60, 20);
            	btnNewButton_4.setBounds(width/between*9, 40, 60, 20);
            	
            	scrollPane.setBounds(0, 70, width-30,height-170);
            	
            	
            }  
        }); 

	}
	
}
