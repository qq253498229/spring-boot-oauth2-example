# Spring Boot OAuth2 Example

## 主要功能

### 授权服务器（权限中心）

- [x] 注册账号
- [x] 通过授权码的方式登录（单点登录），并获取授权码
- [x] 通过授权码获取token
- [x] 通过密码方式登录，并获取token
- [x] 修改密码
- [x] 注销账号
- [x] 通过check_token接口获取用户权限（JWT模式用的比较少）

### 资源服务器（客户端，负责具体业务）

普通用户

- [ ] 查看个人角色
- [ ] 编辑个人信息

管理员用户

- [x] 查看用户列表
- [x] 查看用户详情
- [ ] 重置用户密码

## 参考文档

https://projects.spring.io/spring-security-oauth/docs/oauth2.html

https://docs.spring.io/spring-security-oauth2-boot/docs/2.6.x/reference/html5/

https://consolelog.gitee.io/docs-oauth2/

https://docs.spring.io/spring-security/reference/index.html
