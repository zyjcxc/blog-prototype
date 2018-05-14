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
    * 建立用户与角色的关系
    ```
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    // 多对多关系，通过hibernet关联，数据表要做映射
    private List<Authority> authorities;
    ```
    * 创建用户时，关联角色
    ```
    /**
     * 新建用户
     */
    @PostMapping
    public ResponseEntity<Response> saveOrUpdate(User user, Long authorityId) {
        // 将权限保存到用户相关表
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        
        ...创建用户代码...
    ```
    * 修改用户时，修改角色
 
# 权限管理

* 需求
    * 权限设置

* 功能
    * 建立角色与资源的关系
    * CSRF问题的解决
    ```
    1.解决token的问题, 不想用的请求如下写法：
    // 禁用 H2 控制台的 CSRF 防护
    http.csrf().ignoringAntMatchers("/h2-console/**");
    2.header.html 头部加入
    <!-- CSRF -->
    <meta name="_csrf" th:content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" th:content="${_csrf.headerName}"/>
    3.但是注意，security框架只针对POST方式提交的表单
    4.其他方式的提交，自己需要手动处理，如下面这样的js：
    // 删除用户
    $("#rightContainer").on("click",".blog-delete-user", function () {
        // 获取 CSRF Token
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/users/" + $(this).attr("userId") ,
            type: 'DELETE',
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            success: function(data){
                if (data.success) {
                    // 从新刷新主界面
                    getUsersByName(0, _pageSize);
                } else {
                    toastr.error(data.message);
                }
            },
            error : function() {
                toastr.error("error!");
            }
        });
    });
    
    ```   
    * 启用方法级别的设置
    ```
    /**
     * 安全配置类
     * @author mengqa
     * @create 2018-05-07 14:15
     **/
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别的安全控制
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
    ```
    * 使用BCrypt加密密码
    ```
     @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 认证信息管理
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }
    ```
    * 用户登录,记录我
    ```
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // 都可以访问
                .antMatchers("/h2-console/**").permitAll() // 都可以访问
                .antMatchers("/admins/**").hasRole("ADMIN") // 需要相应的角色才能访问
                .and()
                .formLogin()   //基于 Form 表单登录验证
                .loginPage("/login").failureUrl("/login-error") // 自定义登录界面
                .and().rememberMe().key(KEY) // 启用 remember me
                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面
        http.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
        http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的H2 控制台的请求
    }
    ```

* API
    * /login: GET 获取登录页面
    * /login: POST  ， 登录
        * 参数：username, password, remember-me
    ```
    // 由spring security框架搞定
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // 都可以访问
                .antMatchers("/h2-console/**").permitAll() // 都可以访问
                .antMatchers("/admins/**").hasRole("ADMIN") // 需要相应的角色才能访问
                .and()
                .formLogin()   //基于 Form 表单登录验证
                .loginPage("/login").failureUrl("/login-error") // 自定义登录界面
                .and().rememberMe().key(KEY) // 启用 remember me
                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面
        http.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
        http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的H2 控制台的请求
    }
    ```
     