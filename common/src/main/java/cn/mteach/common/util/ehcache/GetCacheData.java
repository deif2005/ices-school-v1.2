package cn.mteach.common.util.ehcache;

import cn.mteach.common.Constants;
import cn.mteach.common.jyeoo.api.UserApiService;
import cn.mteach.common.jyeoo.constant.JyeooConfigure;
import org.apache.tools.ant.taskdefs.Get;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class GetCacheData {

    /**
     * 返回jyeoo token
     * @return
     */
    public static String getJyeooToken() {
        EhcacheUtil ehcacheUtil = EhcacheUtil.getInstance();
        String token = (String) ehcacheUtil.get(Constants.TOKENCACHENAME, Constants.TOKENCACHEKEY);
        try {
            if (token == null) {
                UserApiService userApiService = new UserApiService();
                token = userApiService.getToken(JyeooConfigure.API_ID, JyeooConfigure.API_USERID, JyeooConfigure.APIUSER_PWD);
                if (token.contains("登录参数错误")){
                    userApiService.register(JyeooConfigure.API_ID,JyeooConfigure.API_PWD,JyeooConfigure.API_USERID,
                            JyeooConfigure.APIUSER_NAME,JyeooConfigure.APIUSER_PWD,"1","1");
                    token = userApiService.getToken(JyeooConfigure.API_ID, JyeooConfigure.API_USERID, JyeooConfigure.API_PWD);
                }
                ehcacheUtil.put(Constants.TOKENCACHENAME, Constants.TOKENCACHEKEY, token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }


}
