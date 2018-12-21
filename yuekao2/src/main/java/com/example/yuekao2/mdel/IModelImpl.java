package com.example.yuekao2.mdel;

import com.example.yuekao2.callback.MyCallBack;
import com.example.yuekao2.util.ICall;
import com.example.yuekao2.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void srequestDatam(String url, Map<String, String> prams, Class clazz, final MyCallBack callBack) {
        OkHttpUtil.getmIstance().postEqueue(url,prams, new ICall() {
            @Override
            public void faile(Exception e) {
                callBack.setData(e);
            }

            @Override
            public void success(Object data) {
               callBack.setData(data);
            }
        },clazz);
    }
}
