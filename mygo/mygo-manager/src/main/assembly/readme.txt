#不同环境打包
clean package -Denv=dev

clean package -Denv=dev2

clean package -Denv=test


#不同环境运行
-Dmaven.tomcat.port=8081 clean tomcat6:run -Denv=dev
-Dmaven.tomcat.port=8081 clean tomcat6:run -Denv=dev2