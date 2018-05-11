package com.mqa.demo.blog.controller;

import com.mqa.demo.blog.domain.User;
import com.mqa.demo.blog.service.IUserService;
import com.mqa.demo.blog.util.ConstraintViolationExcepitonHanlder;
import com.mqa.demo.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;


/**
 * @author mengqa
 * @create 2018-05-04 14:58
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户列表
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model, @RequestParam(value = "name", required = false, defaultValue = "") String name,
                             @RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        model.addAttribute("userList", page.getContent());
        model.addAttribute("page", page);

        return new ModelAndView(async ? "users/list :: #mainContainerRepleace" : "users/list", "userModel", model);
    }

    /**
     * 根据id查询用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 获取创建表单
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("users/add", "userModel", model);
    }

    /**
     * 新建用户
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(User user) {
        try {
            user = userService.saveUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExcepitonHanlder.getMessage(e), null));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", user));
    }

    /**
     * 删除用户
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body(new Response(false, e.getMessage(), null));
        }
        return  ResponseEntity.ok().body( new Response(true, "处理成功", null));
    }

    /**
     * 修改用户的界面
     * @return
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return new ModelAndView("users/add", "userModel", model);
    }


}
