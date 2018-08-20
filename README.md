# news

> 莫莫砂新闻中心

## 技术选型

- Apache-Tomcat-9.0
- Servlet3.0
- JDBC
- MySQL5.5

## 项目启动 - Start Project

> 项目使用maven管理依赖,启动使用tomcat7插件

1. 在项目根目录下(有pom.xml的目录),执行以下命令:

```
mvn clean tomcat7:run
```
	
2. 打开浏览器,访问`http://localhost/news`以访问首页
3. 如果需要进行开发,请访问`http://localhost/news/hello`以进入`cc.momas.news.web.servlet.HelloServlet`类进行调试
##更新日志
> 1.导入layui框架
- webapp文件下新增CSS文件，font文件，images文件，js文件，lay文件
- 新增后台管理页面（ManagementSystem.html）初步框架

