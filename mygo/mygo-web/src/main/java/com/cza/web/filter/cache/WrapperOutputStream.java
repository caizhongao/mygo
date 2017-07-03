
    /**  
    * @Title: c.java
    * @Package com.cza.web.auth
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月3日下午2:34:57
    * @version V1.0  
    */
    
package com.cza.web.filter.cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class WrapperOutputStream extends ServletOutputStream{
    private ByteArrayOutputStream bos;

    public WrapperOutputStream(ByteArrayOutputStream bos) {
        this.bos = bos;
    }
    @Override
    public void write(int b) throws IOException {
        bos.write(b);
    }
    
    
        /* (非 Javadoc)
        * 
        * 
        * @param b
        * @throws IOException
        * @see java.io.OutputStream#write(byte[])
        */
        
    @Override
    public void write(byte[] b) throws IOException {
    	 bos.write(b);
    }
    
        
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
    	 bos.write(b, off, len);
    }
}
