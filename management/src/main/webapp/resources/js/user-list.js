$(function() {

    $("#btn-search").click(function () {
        var groupId = $(this).data("id");
        var searchStr = $("#txt-search").val();
        var authority = $("#authority-type").val();
        document.location.href = document.getElementsByTagName('base')[0].href
            + util.getCurrentRole() + "/user/student-list?searchStr=" + searchStr + "&groupId=" + groupId;
    });

    $(".r-update-btn").click(function () {
        $(document.getElementById("update-user-modal")).modal({
            backdrop: true,
            keyboard: true
        });
        var depId = $(this).data("depid");
        $("#user-update-form #id-update").val($(this).parent().parent().find(".r-id").text().trim());
        $("#user-update-form #name-update").val($(this).parent().parent().find(".r-name").text().trim());
        $("#user-update-form #truename-update").val($(this).parent().parent().find(".r-truename").text().trim());
        $("#user-update-form #national-id-update").val($(this).parent().parent().find(".r-national-id").text().trim());
        $("#user-update-form #phone-update").val($(this).parent().parent().find(".r-phone").text().trim());
        $("#user-update-form #email-update").val($(this).parent().parent().find(".r-email").text().trim());
        $("#user-update-form #company-update").val($(this).parent().parent().find(".r-company").text().trim());
        $("#user-update-form #dept-update").val($(this).parent().parent().find(".r-dept").text().trim());

        $("#user-update-form #department-input-select-u option[value='-1']").attr("selected", "selected");
        $("#user-update-form #department-input-select-u").children("option").each(function () {
            if ($(this).val() == depId) {
                $(this).attr("selected", "selected");
            }
        });

    });

    $(".r-reset-pwd-btn").click(function () {
        $(document.getElementById("reset-pwd-modal")).modal({
            backdrop: true,
            keyboard: true
        });
        $("#reset-pwd-form #username-reset").val($(this).parent().parent().find(".r-name").text().trim());
    });

    $(".disable-btn").click(function () {

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "GET",
            url: "secure/change-user-status-" + $(this).data("id") + "-" + $(this).data("status"),
            success: function (message, tst, jqXHR) {
                if (!util.checkSessionOut(jqXHR))
                    return false;
                if (message.result == "success") {
                    util.success("操作成功", function () {
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

    var selectGroupId = $("#group option:selected").val();
    if(selectGroupId==0){
        $("#link-user-modal-btn").hide();
    }

    $("#add-user-modal-btn").click(function () {
        $(document.getElementById("add-user-modal")).modal({
            backdrop: true,
            keyboard: true
        });

        var selectGroupId = $("#group option:selected").val();
        var selectGroupName = $("#group option:selected").text().trim();

        $("#user-add-form #group-add").val(selectGroupName);
        $("#user-add-form #group-add").data("id", selectGroupId);
        $("#user-add-form #group-add-id").val(selectGroupId);
    });
    $("#link-user-modal-btn").click(function() {
        $(document.getElementById("link-user-modal")).modal({
            backdrop : true,
            keyboard : true
        });
        var selectGroupId =  $("#group option:selected").val();
        var selectGroupName =  $("#group option:selected").text().trim();

        $("#link-user-form #group-add-link").val(selectGroupName);
        $("#link-user-form #group-add-link").data("id", selectGroupId);
        $("#add-user-btn").data("group", selectGroupId);
    });

    $(".link-user-r-btn").click(function () {
        $(document.getElementById("link-user-modal-r")).modal({
            backdrop: true,
            keyboard: true
        });
        var userId = $(this).parent().parent().find(".r-id").text().trim();
        $("#link-user-form-r #name-add-link-r").val(userId);

    });

    $("#link-user-btn-r").click(function () {
        var groupId = $("#group-add-link-r").val();
        var userId = $("#name-add-link-r").val();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "GET",
            url: "secure/add-user-group-" + userId + "-" + groupId,
            success: function (message, tst, jqXHR) {
                if (!util.checkSessionOut(jqXHR))
                    return false;
                if (message.result == "success") {

                    util.success("操作成功", function () {
                        //	window.location.reload();

                    });
                    $("#link-user-modal-r").modal('hide');
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

    $("#link-user-btn").click(function () {
        var groupId = $("#group-add-link").data("id");
        var userNames = $("#name-add-link").val();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: userNames,
            url: "secure/add-user-group-" + groupId,
            success: function (message, tst, jqXHR) {
                if (!util.checkSessionOut(jqXHR))
                    return false;
                if (message.result == "success") {
                    util.success("操作成功", function () {
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

    $(".unlink-user-r-btn").click(function () {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "GET",
            url: "secure/delete-user-group-" + $(this).data("id") + "-" + $(this).data("group"),
            success: function (message, tst, jqXHR) {
                if (!util.checkSessionOut(jqXHR))
                    return false;
                if (message.result == "success") {
                    util.success("操作成功", function () {
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
    });

})

function search(){
    var groupId = $("#group option:selected").val();
    var searchStr = $("#txt-search").val();
    var authority = $("#authority-type").val();
    document.location.href = document.getElementsByTagName('base')[0].href
        + util.getCurrentRole() + "/user/student-list?searchStr=" + searchStr+"&groupId="+groupId;
}