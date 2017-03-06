package cn.mteach.common.jyeoo.util;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuliangpu on 2017/2/15.
 */
public class JsonUtil {

    public static List jsonMapToList(String json){
        JSONArray jArray=JSONArray.fromObject(json);
        List<Map> list=JSONArray.toList(jArray,Map.class);
//        List reslut=new ArrayList();
//        for(int i=0;i<list.size();i++){
//            Map map=list.get(i);
//            reslut.add(map.get("Value"));
//        }
        return list;
    }
}
