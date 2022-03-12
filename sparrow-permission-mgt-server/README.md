# sparrow-platform

关于平台的详细介绍，请参考平台的wiki https://github.com/fanmeijian/sparrow-platform/wiki

后端使用：
1) 创建数据库 请参考wiki
2) 初始化数据 请参考wiki
3) mvn spring-boot:run

关于认证登录部分，请参考认证服务器
https://github.com/fanmeijian/sparrow-authorizaion-server

数据权限，第一个为id参数，第二个为model的全名，第三个为权限
@PreAuthorize("hasPermission(#id,'com.ywsoft.standalone.framework.entity.SwdRole','READ')")

字段权限：尚未完善，path留空，permission为权限，用在返回单个model的地方上
@DataPermissionInterface(path = "", permission = "READ")

前端请参考项目sparrow-platform-ng https://github.com/fanmeijian/sparrow-platform-ng

接口文档请参考
http://localhost:8091/sparrow-platform/swagger-ui/index.html?configUrl=/sparrow-platform/v3/api-docs/swagger-config#

由于ignite的版本问题，在高于jdk11下，需要增加以下jvm参数：
--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED
--add-exports=java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED
--add-exports=jdk.internal.jvmstat/sun.jvmstat.monitor=ALL-UNNAMED
--add-exports=java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED
--add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED
--illegal-access=permit

1.启动认证服务器
	认证服务器会自动创建用户表，oauth client表和角色表；默认的client id为sparrow，密码为password；默认的用户名为ROOT，密码为password
2.启动sparrow服务器，会自动创建所有的表
3.执行系统初始化：{{host}}/init
4.重启sparrow服务器
