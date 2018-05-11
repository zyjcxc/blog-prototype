package com.mqa.demo.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Blog控制器
 *
 * @author mengqa
 * @create 2018-05-07 14:22
 **/
@Controller
@RequestMapping("/blogs")
public class BlogController {

    /**
     * 博客列表
     * @param order 排序
     * @param keyword 标签, 关键字
     * @return 博客列表主页
     */
    @GetMapping
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
                            @RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println("order:" + order + ", keyword:" + keyword);
        return "redirect:/index?order=" + order  + "&keyword=" + keyword;
    }

}

