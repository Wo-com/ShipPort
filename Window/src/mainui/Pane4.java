package mainui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import consql.Dao;
import tool.DateFormat;

public class Pane4 extends JPanel{

	private static final long serialVersionUID = 1L;
	Pane4(){
		System.out.println("面板4被调用");
		initialize();
	}
	private void initialize() {
		Dao db = new Dao();
		db.connSQL();
		DateFormat da=new DateFormat();
      
		JLabel label = new JLabel("起始时间");
		label.setBounds(20, 16, 61, 16);
		this.add(label);
		
		JTextField textField = new JTextField();
		textField.setBounds(93, 11, 130, 26);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("结束时间");
		label_1.setBounds(235, 16, 61, 16);
		this.add(label_1);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(291, 11, 130, 26);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.setBounds(423, 11, 50, 29);
		this.add(btnNewButton);
		
		JButton btn = new JButton("帮助");
		btn.setBounds(473, 11, 50, 29);
		this.add(btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 60, 404, 185);
		this.add(scrollPane);

		btnNewButton.addActionListener(new ActionListener()//查询
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			    	  JPanel panel = new JPanel();
			    	  String text1=textField.getText();
			    	  String text2=textField_1.getText();
			    	  String min_time=null;
			    	  String max_time=null;
			    	  int line_height=35;  									//行高
			    	  int star=100;											//时段图 左距
			    	  
			  		try{//绘制 每条港口信息
			  			String min_sql="select  min(arrive) from portship;";
			  			String max_sql="select  max(leaves) from portship;";
			  			
			  			ResultSet rs_min=db.selectSQL(min_sql);
						if(rs_min.first()) {								//第一行 
							min_time=rs_min.getString(1);
			    			System.out.println("最小时间"+rs_min.getString(1));
			    		}
						ResultSet rs_max=db.selectSQL(max_sql);
						if(rs_max.first()) {	
							max_time=rs_max.getString(1);					//第一行 
			    			System.out.println("最大时间"+rs_max.getString(1));
			    		}
			  			
			  			int  width=da.dateDiff(min_time, max_time)+star;	//绘图窗口宽度
			  			
			  			String sql1 = "select * from port";
			  			ResultSet rs1=db.selectSQL(sql1);
			  			rs1.last() ; int portrow = rs1.getRow(); rs1.beforeFirst();//光标回滚  获取行数  光标回滚
			  		
			  			panel.setPreferredSize(new Dimension(width, portrow*line_height));//设置宽度显示滚动条
			  			panel.setLayout(null);
			  			
			  			while(rs1.next()){
			  				String name=rs1.getString(2);				//取得数据表里面的 name（泊位名）
			  				int line=rs1.getInt(3);				//取得数据表里面的 num_add (行号）	 行号用于绘图
			  				JLabel lblOne = new JLabel(name);
			  				lblOne.setForeground(new Color(0, 0, 0));
			  				lblOne.setBounds(18, line*line_height-10, 61, 16);			//整体往下移动10的距离
			  				panel.add(lblOne);
			  			}
			  			
			  			//绘制空的填充 防止查询为空 遗留数据显示
			  			JEditorPane editorPane1 = new JEditorPane();
		  				editorPane1.setEditable(false);
		  				editorPane1.setBackground(new Color(238,238,238));
		  				editorPane1.setBounds(star,line_height-13, width-star,  portrow*line_height-13);
		  				panel.add(editorPane1,3);
			  			
			  			
			  			String sql3 = null;	
			  			if((text1.length()==0)&&(text2.length()!=0)){//[ ,y] 查询起始到 y时段段
			  				sql3="select * from portship where leaves<='"+text2+"'";
			  				 System.out.println("[ ,y]");
			  				textField.setText(min_time);
			  			}else if((text1.length()!=0)&&(text2.length()==0)){//[x,] 查询x到 结束时段段
			  				sql3="select * from portship where arrive>='"+text1+"'";
			  				 System.out.println("[x, ]");
			  				
			  				textField_1.setText(max_time);	
			  			}else if((text1.length()!=0)&&(text2.length()!=0)){//[x,y] 查询x到 y时段段
			  				sql3="select * from portship where arrive>='"+text1+"'and leaves<='"+text2+"'";
			  				System.out.println("[x,y]");
			  								//设置文本框内容
			  			}else if((text1.length()==0)&&(text2.length()==0)){
			  				sql3="select * from portship";
			  				textField.setText(min_time);					//设置文本框内容
			  				textField_1.setText(max_time);					//设置文本框内容
			  			}
			  			System.out.println("sql  "+sql3);
			  			ResultSet rs3=db.selectSQL(sql3);
			  		
			  			
			  			while(rs3.next()){						//绘制甘特图
			  				
			  				String artime=rs3.getString(3);
			  				String lvtime=rs3.getString(4);
			  				int port=rs3.getInt(5);
			  				int hour=da.dateDiff(artime, lvtime);//长度
			  				int dwstr=da.dateDiff(min_time, artime);
			  				
			  				JEditorPane editorPane = new JEditorPane();
			  				editorPane.setEditable(false);
			  				editorPane.setBackground(new Color(0, 191, 255));
			  				editorPane.setBounds(star+dwstr, port*line_height-13, hour, 20);
			  				panel.add(editorPane,3);//放在后面第一层

			  			}
			  			scrollPane.setViewportView(panel);//绘制完成再添加，不然就会不显示
			  		}catch(Exception sss){
			  	          System.out.println("查询港口名Sql出错");
			  	    }
			           }
			      }
			 );
		
		btn.addActionListener(new ActionListener()//帮助监听
			      {
			      public void actionPerformed(ActionEvent e4)
			           {
			    	  		JOptionPane.showMessageDialog(null,"无输入查询                     [min,max]\n输入起始时间查询            [  x  , max]\n输入结束时间查询            [min,  y  ]\n输入起始和结束时间查询  [  x  ,  y  ]\n","指定位置",JOptionPane.PLAIN_MESSAGE); 
			           }
			      }
			 );
		
		
		int between=15;	
		this.addComponentListener(new ComponentAdapter() {//拖动窗口监听
            public void componentResized(ComponentEvent e) {  
            	int width=MainUI.frame.getWidth();		//获取窗口宽度
            	int height=MainUI.frame.getHeight();	//获取窗口高度
            	scrollPane.setBounds(0, 40, width-23, height-140);
            	
            	label.setBounds(width/between*1, 10, width/between, 20);
            	textField.setBounds(width/between*2, 10, width/between*3, 20);
            	label_1.setBounds(width/between*5, 10, width/between, 20);
            	textField_1.setBounds(width/between*6, 10, width/between*3, 20);
            	btnNewButton.setBounds(width/between*9, 10, width/between*2, 20);
            	btn.setBounds(width/between*11, 10, width/between*2, 20);

            }  
        }); 
		
	}
	
}
