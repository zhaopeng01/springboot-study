Springboot的学习记录
====

### 当前代码库主要用来记录本人在学习SpringBoot的一些思路以及总结
### 本人使用的是IDEA开发工具和JDK8,其他开发工具会在用到时候提出

### study1
一个国际惯例的HelloWorld的实例
只是SpringBoot的一个开始,可以正常运行访问

### study2
主要是对IDEA工具的操作这里不做详细解释,

### study3
是一个SpringBoot对Swagger的一个整合

提供了强大的页面测试功能来调试每个写好的接口
减少手写api文档的时间，
同时又将说明文档整合到代码中

### study4
描述了SpringBoot的多配置文件应用,

在主配置文件中引入其他配置文件,
使用其他配置文件进行单元测试或者版本控制使用 

### study5
整合Log4j记录日志

通过配置使用控制台或者文件输出日志内容。 

### study6
在SpringBoot中发送邮件

在不同的项目中发送邮件成为了或需或不需的一个模块
在这里以一个工具类的形式来将发送邮件放入到项目中

### study7 
在SpringBoot中使用文件上传功能

用了两种上传的方式,
一种简单,一种相比略微复杂一点,
在后期可以自己配置格式,实现其他文件的上传

### study8 
在SpringBoot中整合Thymeleaf模板

使用了两种传递参数跳转到页面的方式,
实现功能一样,就是换了个写法,
Thymeleaf的语法更加接近HTML,并且具有很高的扩展性

### study9
在SpringBoot中整合Mybatis

使用了一个分页插件
在mybatis中用到了简单的增加和查询
查询使用的是分页插件来返回查询数据的

### study10
在SpringBoot中整合Lettuce Redis

一种是通过StringRedisTemplate对key和value都为String类型的数据进行添加,
一种是通过自定义模板对key和value都为Object类型数据接添加使用

### study11
在SpringBoot中整合 定时调度

通过spring-task来进行定时调度的操作,类似于一个轻量级的Quartz,而且使用起来简单方便

### study12
在SpringBoot中增加 数据提交验证

表单的重复提交会导致造成很多脏数据,所以才会有的这个学习的模块

### study13
在SpringBoot中自定义异常信息

在代码中使用自定义的异常类,可以结合自己的项目对异常进行统一的封装处理

### study14
在SpringBoot中整合kafka使用

将本地安装kafka后然后在代码中整合使用kafka,然后结合自己逻辑完成需要的需求

### study15
在SpringBoot中整合ActiveMq使用

将本地安装ActiveMq,然后在代码中整合使用ActiveMq,讲述ActiveMq的点对点模式和发布/订阅模式的使用以及再多个消费者和订阅者模式情况下两种模式的处理方式
