# news

### 项目介绍 - Project Introduction

**莫莫砂新闻中心** - Momas News Center

一个简单的新闻网站

### 应用架构 - Application Architecture

**软件**

- LayUI
- Apache-Tomcat-9.0
- MySQL8.0
- Maven 3.5.4
- Maven Wrapper

**接口**

- Servlet API 4.0
- JDBC

**思想/亮点**

- 项目分为DAO层,Service层,web层,是典型的三层架构(MVC模式)
- DAO层使用原生JDBC连接数据库,用最少的依赖做适合的事情,去繁就简,降低学习成本 
- Service层使用接口对各层进行松耦合,由bean工厂负责装配实现类(单例模式,工厂模式,接口隔离原则)
- web层只负责接收请求,不负责任何具体业务逻辑(单一职责原则)
- ~~统一接口规范,响应给前端的永远都有一个固定的标准格式~~
- 统一异常处理,当异常发生时抛到最外层处理和记录日志,并且由最外层负责封装异常并返回正常结果
- RESTful 风格,使用最简单的Servlet实现基本的RESTful风格,最大限度使用HTTP的语义化功能
- 使用注解开发web层,不需要配置web.xml
- 前后端分离

### 各层职责 - Each Layer responsibility

**Servlet**  

servlet 层的职责是接收参数,验证参数,调用 service 层, 最后返回结果

**Service**  

service 层的职责是接收参数,创建对象,执行业务逻辑,调用 dao 层,最后返回结果

**Dao层**  

dao 层的职责是接收参数,连接数据库,执行数据库操作,处理数据库返回结果,最后返回结果

**其他工具**  

工具类负责重复且上下文无关的处理工作,重点在于上下文无关,切不可耦合到一点点业务相关或逻辑相关,只能做简单处理

### 接口列表 - Interface List

接口名 | 接口URL | GET | POST | PUT | DELETE  
--- | --- | --- | --- | ---| --- 
用户 | `/news/user` | 获取用户 | 添加用户 | 修改用户 | 删除用户  
新闻 | `/news/news` | 获取新闻 | 添加新闻 | 修改新闻 | 删除新闻  
评论 | `/news/comment` | 获取评论 | 添加评论 | 修改评论 | 删除评论  
新闻分类 | `/news/category` | 获取分类 | 添加分类 | 修改分类 | 删除分类  
身份验证 | `/news/auth` | 注销登录 | 用户登录 | (不支持) | (不支持)   


### 从源代码启动项目 - Start Project From Sources

> 项目使用[maven](http://maven.apache.org/)管理依赖,启动使用tomcat7插件

1. 在项目根目录下(有pom.xml的目录),执行以下命令:

```
mvn clean tomcat7:run
```
	
2. 打开浏览器,访问`http://localhost/news`以访问首页

> 如果您的80端口被其他进程占用,您可以更改pom.xml配置文件里的端口号再进行启动项目


#### 如何参与贡献 - How To Contribute

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

