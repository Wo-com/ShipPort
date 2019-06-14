package tool;

import java.text.SimpleDateFormat;


/**
 * 用于计算 时间间隔
 */
public class DateFormat {

    public static void main(String[] args) {
 
        
        
        try {
      //      long min = dateDiff("2014-05-27 13:30:00","2014-05-27 13:00:00");
      //      System.out.println("---------相隔分钟数： "+min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public int dateDiff(String startTime, String endTime) throws Exception {
    	String format="yyyy-MM-dd HH:mm:ss";
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
  //      long ns = 1000;//一秒钟的毫秒数
        long diff;
        //获得两个时间的毫秒时间差异
        diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
       long day = diff/nd;//计算差多少天
        long hour = diff%nd/nh;//计算差多少小时
        long min = diff%nd%nh/nm;//计算差多少分钟
  //      long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
  //     System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
       int bet=(int) (day*24+hour+min/60);
       
        return bet ;
    }
  
}