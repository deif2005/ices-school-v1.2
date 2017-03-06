$(function () {

    // 菜单下拉js
    //	jQuery('#sidebar .sub-menu > a').click(function () {
    $("#sidebar").delegate(".sub-menu > a", "click", function () {
        var last = jQuery('.sub-menu.open', $('#sidebar'));
        last.removeClass("open");
        jQuery('.arrow', last).removeClass("open");
        jQuery('.sub', last).slideUp(200);
        var sub = jQuery(this).next();
        if (sub.is(":visible")) {
            jQuery('.arrow', jQuery(this)).removeClass("open");
            jQuery(this).parent().removeClass("open");
            sub.slideUp(200);
        } else {
            jQuery('.arrow', jQuery(this)).addClass("open");
            jQuery(this).parent().addClass("open");
            sub.slideDown(200);
        }
        var o = ($(this).offset());
        diff = 200 - o.top;
        if (diff > 0)
            $("#sidebar").scrollTo("-=" + Math.abs(diff), 500);
        else
            $("#sidebar").scrollTo("+=" + Math.abs(diff), 500);
    });
});

$(function () {
    if (sessionStorage.menuData && sessionStorage.menuData != "") {
        var menu = JSON.parse(sessionStorage.menuData);
        var str = getMenuStr(menu);
        var object = document.getElementById("sidebar");
        if (object != null) {
            document.getElementById("sidebar").innerHTML = str;
        }

    } else {
        jQuery.ajax({

            type: "get",
            url: "common-page/menu",
            success: function (message, tst, jqXHR) {
                if (message.result == "success") {
                    var menu = message.object;
                    var str = getMenuStr(menu);
                    sessionStorage.menuData = JSON.stringify(menu);
                    var object = document.getElementById("sidebar");
                    if (object != null) {
                        document.getElementById("sidebar").innerHTML = str;
                    }

                }
            },
            error: function (jqXHR, textStatus) {
                alert("操作失败请稍后尝试");
            }
        });

    }
});

function getMenuStr(menu) {
    var menuId = "";
    var menuId2 = ""
    var servletPath = window.location.pathname;
    var list = servletPath.split("/");
    if (list.length > 2) {
        menuId = list[2];
        if (list.length > 3) {
            menuId2 = list[3];
        } else {
            menuId2 = "";
        }
    }
    var str = '<ul class="sidebar-menu">';
    for (var key in menu) {
        var menu1 = menu[key];
        var childMap = menu1.childMap;
        //如果没有子目录不显示class=arrow
        if (menu1.childSize == 0) {
            //如果等于当前一级目录 添加class=active
            if(menu1.menuId == menuId){
                str += ' <li class="sub-menu active">'
            }else{
                str += ' <li class="sub-menu">'
            }
            str += '<a href="' + menu1.menuHref + '" class="">'
            str += ' <i class="fa ' + menu1.icon + '"></i>'
            str += '<span>' + menu1.menuName + '</span>'
            str += '<span class=""></span></a>'
        } else {
            str += ' <li class="sub-menu">'
            str += '<a href="javascript:;" class="">'
            str += ' <i class="fa ' + menu1.icon + '"></i>'
            str += '<span>' + menu1.menuName + '</span>'
            str += '<span class="arrow"></span></a>'
        }

        if (menu1.menuId == menuId) {
            str += ' <ul class="sub" style="display: block">'
        } else {
            str += ' <ul class="sub">'
        }
        for (var i in childMap) {
            var menu2 = childMap[i];
            if (menu2.visiable == 1) {
                if (menu2.menuId == menuId2) {
                    str += '<li class="active">';
                } else {
                    str += '<li>';
                }
                str += '<a class="" href="' + menu2.menuHref + '"><i class="fa ' + menu2.icon + '"></i>' + menu2.menuName + '</a></li>'
            }

        }

        str += '</ul>'
        str += '</li>'
    }
    str += '</ul>'
    return str;
}

/*获取url hash*/
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

//设置cookie
function setCookie(cname, cvalue, exhours) {
    var d = new Date();
    d.setTime(d.getTime() + (exhours * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
