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
    