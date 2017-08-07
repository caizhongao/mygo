package traceLog;

    /**  
    * @Title: test.java
    * @Package com.cza.web
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月7日下午2:02:41
    * @version V1.0  
    */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
    * @ClassName: test
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月7日下午2:02:41
    *
    */

public class test {
	private static final Logger log = LoggerFactory.getLogger(test.class);
	
	public static void main(String[] args) {
		String sss="123";
		log.info("test:{},{}",sss,sss);
	}
}
