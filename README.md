
# 海底捞工程文档

### haidilao-core
为核心的DAO、Service 相关类 该项目依赖于 **haidilao-framework**

### haidilao-framework
为**haidilao-core**和**haidilao-api**以及**haidilao-web**提供相关utils

### haidilao-api
对外提供 **RESTful** 接口（给App和小程序以及h5），该项目依赖于 **haidilao-core**


# 使用
## 安装依赖
```bash

mvn install

```

# 打包
## mvn打包
目前使用的是mvn进行打包，生产环境开发环境配置切换使用的是portable-config-maven-plugin插件

# 启动
## 使用springboot启动服务
直接启动haidilao-api项目
com.haidilao.api.HaidilaoApiApplication

## 使用tomcat8.x以上
注释掉haidilao-api项目中pom文件的
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
```
再通过根路径的的打包脚本进行打包

## 使用tomcat8.x一下 以及 taobao-tomcat
注释掉haidilao-api项目中pom文件的
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
```
再通过根路径的的打包脚本进行打包

最后还需要替换tomcat/lib路径下的
el-api.jar文件
因为springboot有el-api.3.0.0.jar版本有相关依赖
所以要更新tomcat/lib下的包版本

