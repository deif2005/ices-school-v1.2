package cn.mteach.common.jyeoo.api;

import cn.mteach.common.domain.jyeoo.UserRegister;
import cn.mteach.common.jyeoo.constant.HttpMethods;
import cn.mteach.common.jyeoo.util.DES3;
import cn.mteach.common.jyeoo.util.HttpClientUtil;
import net.sf.json.JSONObject;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class UserApiService {

    /**
     * 用户注册
     * @param apiId
     * @param apiPwd
     * @param userId 用户ID
     * @param userName 用户名
     * @param userPwd 密码
     * @param userRole 角色
     * @param userSex 性别
     * @throws Exception
     */
    public  String register(String apiId, String apiPwd, String userId, String userName, String userPwd, String userRole, String userSex) throws Exception{

        UserRegister userRegister = new UserRegister();
        userRegister.setApiID(apiId);
        userRegister.setApiPwd(apiPwd);
        userRegister.setUserID(userId);
        userRegister.setUserName(userName);
        userRegister.setUserPwd(userPwd);
        userRegister.setUserRole(userRole);
        userRegister.setUserSex(userSex);
        // 转json
        JSONObject jsonObject = JSONObject.fromObject(userRegister);
        String url = "http://api.jyeoo.com/v1/register?id=" + apiId + "&v=" + DES3.encrypt(jsonObject.toString());

        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.POST, null, null, "UTF-8");
        return resp;
    }

    /**
     *  获取用户token
     * @param apiId
     * @param userId 用户ID
     * @param userPwd 密码
     * @return
     * @throws Exception
     */
    public  String getToken(String apiId, String userId, String userPwd) throws Exception {
        String json = apiId + "#@@#" + userId + "#@@#" + userPwd;
        String url = "http://api.jyeoo.com/v1/user?id=" + apiId + "&v=" + DES3.encrypt(json);

        //发送请求
        String resp = HttpClientUtil.send(url, HttpMethods.POST, null, null, "UTF-8");
        return resp;
    }
}
