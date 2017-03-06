package cn.mteach.common.domain.jyeoo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuliangpu on 2017/2/14.
 */
public class Groups implements Serializable {
    private static final long serialVersionUID = -3524641011824711062L;

    private  String Key;

    private List<Ques> Value;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public List<Ques> getValue() {
        return Value;
    }

    public void setValue(List<Ques> value) {
        Value = value;
    }
}