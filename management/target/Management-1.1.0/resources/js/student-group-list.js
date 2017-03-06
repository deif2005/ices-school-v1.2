$(function() {

    group_list.initial();
});

var group_list = {
    initial : function initial() {
        this.delete();
        this.bindSubmitForm();
        this.bindGroupUpdate();
    },

    delete : function bindDelete(){
        $(".delete-btn").click(function(){

            $.ajax({
                headers : {
                    'Accept' : 'application/json',
                    'Content-Type' : 'application/json'
                },
                type : "GET",
                url : "secure/delete-group-" + $(this).data("id"),
                success : function(message, tst, jqXHR) {
                    if (!util.checkSessionOut(jqXHR))
                        return false;
                    if (message.result == "success") {
                        util.success("删除成功", function(){
                            window.location.reload();
                        });
                    } else {
                        util.error("操作失败请稍后尝试:" + message.result);
                    }

                },
                error : function(jqXHR, textStatus) {
                    util.error("操作失败请稍后尝试");
                }
            });

            return false;

        });
    },

    bindSubmitForm : function bindSubmitForm() {
        var form = $("form#dep-add-form");

        $("#add-group-btn").click(function() {
            var result = group_list.verifyInput();
            if (result) {
                var data = new Object();
                data.groupName = $("#groupName").val();
                jQuery.ajax({
                    headers : {
                        'Accept' : 'application/json',
                        'Content-Type' : 'application/json'
                    },
                    type : "POST",
                    url : "secure/add-group",
                    data : JSON.stringify(data),
                    success : function(message, tst, jqXHR) {
                        if (message.result == "success") {
                            document.location.href = document
                                    .getElementsByTagName('base')[0].href
                                + util.getCurrentRole() + "/common/student-group-list";
                        } else {
                            alert(message.result);
                        }
                    }
                });
            }

            return false;
        });
    },

    bindGroupUpdate : function bindGroupUpdate() {
        $("#edit-group-btn").click(function() {
            var groupName =$("#groupNameEdit").val();
            var groupId =$("#groupId").val()

            var result = group_list.checkUpdate(groupId,groupName);
            if (result) {
                var data = new Object();
                data.groupName = groupName;
                data.groupId=groupId;
                jQuery.ajax({
                    headers : {
                        'Accept' : 'application/json',
                        'Content-Type' : 'application/json'
                    },
                    type : "GET",
                    url:"secure/modify-group-"+ groupId + "-" + groupName,
                    data : JSON.stringify(data),
                    success : function(message, tst, jqXHR) {
                        if (message.result == "success") {
                            document.location.href = document
                                    .getElementsByTagName('base')[0].href
                                + util.getCurrentRole() + "/common/student-group-list";
                        } else {
                            alert(message.result);
                        }
                    }
                });
            }

            return false;
        });
    },

    verifyInput : function verifyInput() {
        $(".form-message").empty();
        var result = true;
        var check_n = this.checkName();
        result = check_n;
        return result;
    },

    checkName : function checkName() {
        var f_name = $("#groupName").val();
        if (f_name == "") {
            $(".form-dep-name .form-message").text("分组名称不能为空");
            return false;
        } else if (f_name.length > 40 || f_name.length < 3) {
            $(".form-dep-name .form-message").text("请保持在3-40个字符以内");
            return false;
        }
        return true;
    },
    checkUpdate:function checkUpdate(id,name){
        if(id=="" ){
            $(".form-dep-name .form-message").text("id丢失");
            return false;
        }
        if (name == "") {
            $(".form-dep-name .form-message").text("分组名称不能为空");
            return false;
        } else if (name.length > 40 || name.length < 3) {
            $(".form-dep-name .form-message").text("请保持在3-40个字符以内");
            return false;
        }
        return true;
    }

};