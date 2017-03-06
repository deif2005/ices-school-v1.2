/**
 * Created by wuliangpu on 2016/12/10.
 */
$(function() {
    new_list.initial();
});

var new_list = {
    initial : function initial() {
        this.bindDelete();
        this.showNew();
        this.updateNew();
    },
    bindDelete : function bindDelete() {
        $(".delete-btn").click(function () {

            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "GET",
                url: "/admin/system/news-delete/" + $(this).data("id"),
                success: function (message, tst, jqXHR) {
                    if (!util.checkSessionOut(jqXHR))
                        return false;
                    if (message.result == "success") {
                        util.success("删除成功", function () {
                            window.location.reload();
                        });
                    } else {
                        util.error("操作失败请稍后尝试:" + message.result);
                    }

                },
                error: function (jqXHR, textStatus) {
                    util.error("操作失败请稍后尝试");
                }
            });

            return false;
        });
    },

    showNew:function showNew(){
        $(".r-update-btn").click(function() {
            $("#update-news-modal").modal({
                backdrop : true,
                keyboard : true
            });
            var depId = $(this).data("depid");
            $("#newsId").val(depId);
            $("#new-update-form #name-update").val($(this).parent().parent().find(".r-name").text().trim());
            $("#new-update-form #news-content-update").val($(this).parent().parent().find(".r-content").text().trim());
        });
    },

    updateNew : function updateNew() {
        $("#update-news-btn").click(function() {
            var result = new_list.verifyInput();
            if (result) {
                var data = new Object();
                data.newsId=$("#newsId").val();
                data.title = $("#name-update").val();
                data.content = $("#news-content-update").val();

                var action = $(this).data("action");
                jQuery.ajax({
                    headers : {
                        'Accept' : 'application/json',
                        'Content-Type' : 'application/json'
                    },
                    type : "POST",
                    url : action,
                    data : JSON.stringify(data),
                    success : function(message, tst, jqXHR) {
                        if (message.result == "success") {
                            util.success("修改成功！", function() {
                                window.location.reload();

                            });

                        } else {
                            if (message.result == "duplicate-username") {
                                $(".form-username .form-message").text(message.messageInfo);
                            } else if (message.result == "captch-error") {

                            } else if (message.result == "duplicate-email") {
                                $(".form-email .form-message").text(message.messageInfo);
                            } else {
                                alert(message.result);
                            }
                        }
                    }
                });
            }

            return false;
        });
    },
    verifyInput : function verifyInput() {
        $("#update-message").empty();
        var result = true;
        var newsId=$("#newsId").val();
        if(newsId==""){
            alert("丢失newsId");
            return false;
        }
        var check_t = this.checkNewsTitle();
        var check_c = this.checkContent();

        result = check_t && check_c;
        return result;
    },

    checkNewsTitle : function checkNewsTitle() {
        var title = $("#name-update").val();
        if (title == "") {
            $("#title-message").text("公告标题不能为空");
            return false;
        } else if (title.length > 50 || title.length < 4) {
            $("#title-message").text("请保持在4-50个字符以内");
            return false;
        } else {
            var re = /[\+|\-|\\|\/||&|!|~|@|#|\$|%|\^|\*|\(|\)|=|\?|´|"|<|>|\.|,|:|;|\]|\[|\{|\}|\|]+/;
            if (re.test(title)) {
                $("#title-message").text("只能是数字字母或者下划线的组合");
                return false;
            } else
                return true;

        }
        return true;
    },
    checkContent : function checkContent() {
        var content = $("#news-content-update").val();
        if (content == "") {
            $("#content-message").text("内容不能为空");
            return false;
        } else if (content.length > 600 || content.length < 1) {
            $("#content-message").text("请保持在1-600个字符以内");
            return false;
        }
        return true;
    }
}