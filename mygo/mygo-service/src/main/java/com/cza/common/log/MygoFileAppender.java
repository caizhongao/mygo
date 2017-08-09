/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package com.cza.common.log;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.InterruptedIOException;

import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.spi.LoggingEvent;

public class MygoFileAppender extends FileAppender {

  protected long maxFileSize =1024*1024;

  
  
  
  private SimpleDateFormat dateFmt= new SimpleDateFormat("yyyy-MM-dd-HH-mm");
  
  private long nextRollover = 0;

  public
  MygoFileAppender() {
    super();
  }

  public
  MygoFileAppender(Layout layout, String filename, boolean append)
                                      throws IOException {
    super(layout, filename, append);
  }

  public
  MygoFileAppender(Layout layout, String filename) throws IOException {
    super(layout, filename);
  }
  public
  long getMaximumFileSize() {
    return maxFileSize;
  }

  
  private String getFileName(String name,Date date,int index){
	  return name+"-"+dateFmt.format(date) + '.' + index;
  }
  public void checkNewDay(){

	    File target;
	    File file = new File(fileName);
	    //如果最近一次更新不是今天，则需要将当前日志文件清空并且备份日期文件
	    if(!dateFmt.format(new Date(file.lastModified())).equals(dateFmt.format(new Date()))){
	    	if (qw != null) {
		        long size = ((CountingQuietWriter) qw).getCount();
		        LogLog.debug("rolling over count=" + size);
		        if(size<=0){
		        	return;
		        }
		    }
		    int i=1;
		    while(true) {
			  target = new File(getFileName(fileName,new Date(file.lastModified()),i));
			  if (!target.exists()) {
			    LogLog.debug("create new log file:"+target.getName());
			    break;
			  }
			  i++;
		    }
		    this.closeFile(); // keep windows happy.
		    LogLog.debug("Renaming file " + file + " to " + target);
		    boolean renameSucceeded = file.renameTo(target);
		      //
		      //   if file rename failed, reopen file with append = true
		      //
		    if (!renameSucceeded) {
		      try {
		        this.setFile(fileName, true, bufferedIO, bufferSize);
		      }
		      catch(IOException e) {
		          if (e instanceof InterruptedIOException) {
		              Thread.currentThread().interrupt();
		          }
		          LogLog.error("setFile("+fileName+", true) call failed.", e);
		      }
		    }

		    //
		    //   if all renames were successful, then
		    //
		    if (renameSucceeded) {
			    try {
			      // This will also close the file. This is OK since multiple
			      // close operations are safe.
			      this.setFile(fileName, false, bufferedIO, bufferSize);
			      nextRollover = 0;
			    }
			    catch(IOException e) {
			        if (e instanceof InterruptedIOException) {
			            Thread.currentThread().interrupt();
			        }
			        LogLog.error("setFile("+fileName+", false) call failed.", e);
			    }
		    }
	    }
	    
	  
  }
  public void rollOver() {
    File target;
    File file;

    if (qw != null) {
        long size = ((CountingQuietWriter) qw).getCount();
        LogLog.debug("rolling over count=" + size);
        //   if operation fails, do not roll again until
        //      maxFileSize more bytes are written
        nextRollover = size + maxFileSize;
    }
    int i=1;
    while(true) {
	  target = new File(getFileName(fileName,new Date(),i));
	  if (!target.exists()) {
	    LogLog.debug("create new log file:"+target.getName());
	    break;
	  }
	  i++;
    }
    this.closeFile(); // keep windows happy.
    file = new File(fileName);
    LogLog.debug("Renaming file " + file + " to " + target);
    boolean renameSucceeded = file.renameTo(target);
      //
      //   if file rename failed, reopen file with append = true
      //
    if (!renameSucceeded) {
      try {
        this.setFile(fileName, true, bufferedIO, bufferSize);
      }
      catch(IOException e) {
          if (e instanceof InterruptedIOException) {
              Thread.currentThread().interrupt();
          }
          LogLog.error("setFile("+fileName+", true) call failed.", e);
      }
    }

    if (renameSucceeded) {
	    try {
	      // This will also close the file. This is OK since multiple
	      // close operations are safe.
	      this.setFile(fileName, false, bufferedIO, bufferSize);
	      nextRollover = 0;
	    }
	    catch(IOException e) {
	        if (e instanceof InterruptedIOException) {
	            Thread.currentThread().interrupt();
	        }
	        LogLog.error("setFile("+fileName+", false) call failed.", e);
	    }
    }
  }

  public
  synchronized
  void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize)
                                                                 throws IOException {
    super.setFile(fileName, append, this.bufferedIO, this.bufferSize);
    if(append) {
      File f = new File(fileName);
      ((CountingQuietWriter) qw).setCount(f.length());
    }
  }

  public
  void setMaximumFileSize(long maxFileSize) {
    this.maxFileSize = maxFileSize;
  }

  public
  void setMaxFileSize(String value) {
    maxFileSize = OptionConverter.toFileSize(value, maxFileSize + 1);
  }

  protected
  void setQWForFiles(Writer writer) {
     this.qw = new CountingQuietWriter(writer, errorHandler);
  }

  protected
  void subAppend(LoggingEvent event) {
	checkNewDay();
	LoggingEvent fileEvent=new LoggingEvent(event.getFQNOfLoggerClass(), event.getLogger(), event.getLevel(),LogUtil.getLogHeader()+event.getMessage(), event.getThrowableInformation()!=null?event.getThrowableInformation().getThrowable():null);
    super.subAppend(fileEvent);
    if(fileName != null && qw != null) {
        long size = ((CountingQuietWriter) qw).getCount();
        if (size >= maxFileSize && size >= nextRollover) {
            rollOver();
        }
    }
   }
}
