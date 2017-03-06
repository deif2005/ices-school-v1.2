package cn.mteach.common.domain.jyeoo;

import java.io.Serializable;

/**
 * Created by wuliangpu on 2017/2/14.
 */
public class UserRegister implements Serializable {

    private static final long serialVersionUID = -5609303198889760364L;
    private String ApiID;
    private String apiPwd;
    private String userID;
    private String userName;
    private String userPwd;
    private String userRole;
    private String userSex;

    public String getApiID() {
        return ApiID;
    }

    public void setApiID(String apiID) {
        ApiID = apiID;
    }

    public String getApiPwd() {
        return apiPwd;
    }

    public void setApiPwd(String apiPwd) {
        this.apiPwd = apiPwd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
