# Spring Boot OAuth2 Example

## 主要功能

### 授权服务器（权限中心）

- [x] 通过授权码的方式登录（单点登录），并获取授权码
- [x] 通过授权码获取token
- [x] 通过密码方式登录，并获取token
- [x] 注册账号
- [x] 修改密码
- [x] 注销账号

### 资源服务器（客户端，负责具体业务）

普通用户

- 查看自己的角色

员工

- 新建订单
- 订单列表（自己创建的）
- 查看上级经理

上级经理

- 维护下级员工
- 查看员工订单

管理员用户

- 用户列表
- 维护用户权限
- 重置任意用户密码
- 锁定用户

## 参考文档

https://projects.spring.io/spring-security-oauth/docs/oauth2.html

https://docs.spring.io/spring-security-oauth2-boot/docs/2.6.x/reference/html5/

https://consolelog.gitee.io/docs-oauth2/

https://docs.spring.io/spring-security/reference/index.html
