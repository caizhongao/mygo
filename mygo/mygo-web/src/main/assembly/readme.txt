#不同环境打包
clean package -Denv=dev

clean package -Denv=dev2

clean package -Denv=test

#不同环境运行
tomcat6:run -Denv=dev
tomcat6:run -Denv=dev2

#支付宝测试账号
账号:yklkpb9030@sandbox.com
密码：111111
支付密码：111111

商家账号ixfled0973@sandbox.com
登录密码111111