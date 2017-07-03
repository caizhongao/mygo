
    /**  
    * @Title: test.java
    * @Package com.cza.web.auth
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年7月3日上午11:46:21
    * @version V1.0  
    */
    
package com.cza.web.filter.cache;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class WrapperResponse extends HttpServletResponseWrapper{
    private PrintWriter printWriter;
    private ServletOutputStream out;
    private ByteArrayOutputStream content;
    public WrapperResponse(HttpServletResponse response) throws IOException {
    	super(response);
        response.setCharacterEncoding("utf-8");
        content=new ByteArrayOutputStream();
        out=new WrapperOutputStream(content);
        printWriter =new PrintWriter(getOutputStream());
        
    }
        
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
    	return out;
    }
        
    @Override
    public PrintWriter getWriter() throws IOException {
    	return printWriter;
    }
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
    }
    public byte[] getContent() throws IOException {
    	flushBuffer();
        return content.toByteArray();
    }
}

