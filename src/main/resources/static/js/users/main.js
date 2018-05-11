/**
 * Bolg main JS.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {

    var _pageSize;

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        getUsersByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });

    // 搜索事件
    $("#searchNameBtn").click(function () {
        getUsersByName(0, _pageSize);
    });

    // 获取添加用户的界面
    $("#addUser").click(function () {
        $.ajax({
            url : "/users/add",
            success : function(data) {
                $("#userFormContainer").html(data);
            }
        })
    });

    // 获取编辑页面
    $("#rightContainer").on("click", ".blog-edit-user", function () {
        $.ajax({
            url : "/users/modify/" + $(this).attr("userId"),
            success : function(data) {
                $("#userFormContainer").html(data);
            }
        })

    });

    // 保存提交
    $("#submitEdit").click(function () {
        $.ajax({
            url : "/users",
            type : 'post',
            data : $('#userForm').serialize(),
            success : function(data) {
                $('#userForm')[0].reset();
                if (data.success) {
                    getUsersByName(0, _pageSize);
                } else {
                    toastr.error(data.message);
                }
            },
            error: function () {
                toastr.error("error!");
            }

        });
    });

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
});

// 根据用户名搜索
function getUsersByName(pageIndex, pageSize) {
    $.ajax({
        url: "/users",
        contentType : 'application/json',
        data:{
            "async":true,
            "pageIndex":pageIndex,
            "pageSize":pageSize,
            "name":$("#searchName").val()
        },
        success: function(data){
            $("#mainContainer").html(data);
        },
        error : function() {
            toastr.error("error!");
        }
    });
}