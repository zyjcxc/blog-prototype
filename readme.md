# Springboot 博客Master

# Springboot blog系统原型 需求
1. 用户管理
2. 安全设置
3. 博客管理
4. 评论管理
5. 点赞管理
6. 分类管理
7. 标签管理
8. 首页搜索

# 用户管理

* 需求
    * 注册
    * 登录
    * 增删改查

* API
    * /register: GET 获取注册页面
    * /register: POST 注册 ， 成功后返回登录页面
        * 参数：User
    * /user: GET 用户列表
        * async : 是否异步请求页面
        * pageIndex
        * pageSize
        * name : 用户名称的关键字
    * /users/add : GET 获取新增页面
    * /users : POST 保存用户
    * /users/{id} : DELETE 删除用户
    * /users/edit/{id} : GET 获取某个用户的编辑界面

* 修改实体，加字段，Bean属性上加验证
* 新增Controller，新增UserService接口
* 新增users/add, edit, list页面
* 实现了博客系统的用户管理（/admins）

# 角色管理

* 需求
    * 角色授权
    
* 功能
    * 建立权限（角色）的实体
    * 建立用户与权限的关系
    * 创建用户时，关联角色
    * 修改用户时，修改角色
    * 初始化权限（角色）、用户的数据
 
# 权限管理

* 需求
    * 权限设置

* 功能
    * 建立角色与资源的关系
    * CSRF问题的解决
    * 启用方法级别的设置
    * 使用BCrypt加密密码
    * 用户登录
    * 记录我

* API
    * /login: GET 获取登录页面
    * /login: POST  ， 登录
        * 参数：username, password, remember-me
 
     