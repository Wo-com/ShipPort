package tool;

import java.sql.ResultSet;
import consql.Dao;

public class Appoint {
	Dao db = new Dao();
	
	public Appoint(){
		System.out.println("--开始指定位置--");
	}

	public boolean human_appoint(String shipid,String portid){
		try{	
			db.connSQL();		//连接数据库

			//获取船只到达离开时间
			String sql1="select * from ship where id='"+shipid+"';";
			System.out.println(sql1);
			ResultSet rs2=db.selectSQL(sql1);
			String ar = null;
			String lv = null;
			if(rs2.first()) {								//第一行 获取最近停靠时间
    			System.out.println(rs2.getString("leaves"));
    			ar=rs2.getString("arrive");
    			lv=rs2.getString("leaves");
    		}
		//	String sql4="insert into portship(shipid,arrive,leaves,portid) values('"+shipid+"','"+ar+"','"+lv+"','"+portid+"')";
			int ptid=Integer.parseInt(portid);
			update(shipid,ar,lv,ptid);
			
       		System.out.print("手动指定完成");
        }catch(Exception e1){
        	System.out.print("手动指定,出错");
        	db.deconnSQL();		//断开连接
        	return false;
        }
		db.deconnSQL();
		return true;			//断开连接
	}
	
	public boolean auto_appoint(String shipid){
		try{	
			db.connSQL();		//连接数据库  	
        	//获取船只 偏好  到达时间  离开时间
			String sql1="select * from ship where id='"+shipid+"';";
			ResultSet rs1=db.selectSQL(sql1);
			String per = null;
			
			String one_ar = null;
			String one_lv = null;
			if(rs1.first()) {								//只有一行 
	    		per=rs1.getString("perfer");
	    		one_ar=rs1.getString("arrive");
	    		one_lv=rs1.getString("leaves");
	    	}	
			int one_per=Integer.parseInt(per);
			//查询偏好处有没有 时间冲突
			String sql2="select * from portship where portid='"+one_per+"';";//查询已经停靠在 偏好位置的船只	
			System.out.println("查看偏好处有没有空"); 
        	boolean add_one=conflict(shipid,one_ar,one_lv,one_per,sql2);//在偏好位置添加成功则退出
        	if(!add_one){
        		System.out.println("查看偏好处没有空 查看其他位置"); 
        		String sql3="select * from port;";
            	ResultSet rs3=db.selectSQL(sql3);
            	rs3.last() ; int port_row = rs3.getRow(); rs3.beforeFirst();//光标回滚  获取行数  光标回滚
            	int portid=0;
            	int i=0;
            	boolean add_two=false;					//如果在其他位置 可以添加则 添加之后退出
            	while(rs3.next()&&(!add_two)){
            		i++;
            		portid=rs3.getInt("num_add");
            		String sql4="select * from portship where portid='"+portid+"';";//查询已经停靠在 偏好位置的船只	
            		add_two=conflict(shipid,one_ar,one_lv,portid,sql4);
            		if(i==port_row&&(!add_two)){				//查找所有位置没空位时
            			db.deconnSQL();		//断开连接
            			return false;
            		}
            	}
        	}
 
           	System.out.println("自动分配结束");
        }catch(Exception e1){
        	db.deconnSQL();		//断开连接
            System.out.println("自动分配,出错");
        }
		db.deconnSQL();		//断开连接
		return true;
	}
	boolean conflict(String shipid,String one_ar,String one_lv,int one_per,String sql){
		try{
			DateFormat da=new DateFormat();
			String all_ar = null;
			String all_lv = null;
			ResultSet rs2=db.selectSQL(sql);
			if(rs2.next()){ 
				System.out.println("港口不为空"); 
			}else{
				update(shipid,one_ar,one_lv,one_per);
				System.out.println("港口"+one_per+"为空可以添加");
			}
			rs2.last() ;
			int row = rs2.getRow();						//获取行数
			int i=0;									//i每比较一次增加1 当等于row时那么 执行到最后一条数据了
			rs2.beforeFirst();//游标归位
	    	while(rs2.next()) {	//为空不执行  不为空执行
	    		all_ar=rs2.getString("arrive");
	    		all_lv=rs2.getString("leaves");
	    		int num=i+1;
	    		System.out.println("港口"+one_per+"不为空 判定时间是否冲突"+num+"次");//时间段（a-b）时间段(c-d)  不相交条件: (b<c)||(d<a)
	    		int dtime1=da.dateDiff(all_lv,one_ar);	//船只到达时间 与  已经停靠离开时间做时间差
	    		int dtime2=da.dateDiff(one_lv,all_ar);	//已经停靠到达时间做时间差 与 船只离开时间
	    		if((dtime1>0)||(dtime2>0)){
	    			i++;
	    			if(i==row){							//直到最后一条数据都不冲突时  那么可以添加
	    				update(shipid,one_ar,one_lv,one_per);
	        			System.out.println("港口"+one_per+"不为空 时间不冲突");
	    			}
	    		}else{
	    			
	    			System.out.println("港口"+one_per+"不为空 时间冲突");	
	    			return false;
	    		}
	    		
			}
			
		}catch(Exception e){
			db.deconnSQL();		//断开连接
			System.out.println("confict判定错误xxxx");
		}
		return true;
	}
	
	void update(String shipid,String ar,String lv,int per){//执行插入操作（为船只安排泊位）
				
		//	String sql5 = "select * from portship where shipid='"+shipid+"';";

		//把港口编号存入船只表中
		String sql6 = "update ship set site='"+per+"' where id='"+shipid+"';";
    	db.updateSQL(sql6);
    		
    	//插入数据到portship中，作为服务记录
    	//String sql4="insert into portship(shipid,arrive,leaves,portid) values('222','2012-12-12 01:12:11','2012-12-12 01:12:11','5')";
		String sql7="insert into portship(shipid,arrive,leaves,portid) values('"+shipid+"','"+ar+"','"+lv+"','"+per+"')";
		db.insertSQL(sql7);
		

	}
	
}
