/**
 * Bolg main JS.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {

    $(".blog-menu .list-group-item").click(function () {

        $(".blog-menu .list-group-item").removeClass("active");
        $(this).addClass("active");
        var url = $(this).attr("url");
        $.ajax({
            url : url,
            success : function(data) {
                $("#rightContainer").html(data);
            }
        })

    });

    $(".blog-menu .list-group-item:first").trigger("click");

});