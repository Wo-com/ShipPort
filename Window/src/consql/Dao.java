package consql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Dao {
    private Connection conn = null;
    PreparedStatement statement = null;

    // connect to MySQL
    public void connSQL() {
        String url = "jdbc:mysql://localhost:3306/ShipPort?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "zgx1734475W"; // 加载驱动程序以连接数据库 
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver" ); 
            conn = DriverManager.getConnection( url,username, password ); 
            }
        //捕获加载驱动程序异常
         catch ( ClassNotFoundException cnfex ) {
             System.err.println(
             "装载 JDBC/ODBC 驱动程序失败。" );
             cnfex.printStackTrace(); 
         } 
         //捕获连接数据库异常
         catch ( SQLException sqlex ) {
             System.err.println( "无法连接数据库" );
             sqlex.printStackTrace(); 
         }
    }

    // 断开连接 MySQL
    public void deconnSQL() {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("关闭数据库问题 ：");
            e.printStackTrace();
        }
    }

    // 查询语句
    public ResultSet selectSQL(String sql) {
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 插入语句
    public boolean insertSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    //删除语句
    public boolean deleteSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("删除数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("删除时出错：");
            e.printStackTrace();
        }
        return false;
    }
    //更新语句
    public boolean updateSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("更新数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("更新时出错：");
            e.printStackTrace();
        }
        return false;
    }
    
    
    public static void main(String args[]) {

        Dao db = new Dao();
        db.connSQL();
//        String sql1 = "select * from port";
 //       String sql2 = "insert into port(id,name) values("+2222222+",'mmmmm')";
 //       String sql3 = "delete from port where id='20190101'";
//        String sql4 = "update ship set name='bbbb' where id='3453';";
        
       // String sql5="insert into portship(shipid,arrive,leaves,portid) values('222','2012-12-12 01:12:11','2012-12-12 01:12:11','5')";
    //    String sql5="select * from ship where id='2222';";
//        String sql6="select recent form port where id='333';";
        
        String min_time="select * from portship where leaves<='2019-06-15 01:12:11';";
		ResultSet rs_min=db.selectSQL(min_time);	
		try{
		while(rs_min.next()) {								//第一行 获取最近停靠时间
			System.out.println(rs_min.getString(1));
		}
		}catch(Exception e){
			System.out.println("查询出现max min");
		}
        System.out.println("查询完成");
    
       
        db.deconnSQL();//关闭连接
    }
}