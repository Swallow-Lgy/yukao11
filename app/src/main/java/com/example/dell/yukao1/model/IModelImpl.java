package com.example.dell.yukao1.model;

import com.example.dell.yukao1.callback.MyCallBack;
import com.example.dell.yukao1.util.ICall;
import com.example.dell.yukao1.util.OkHttpUtil;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void rquestData(String url, Map<String, String> prams, Class clazz, final MyCallBack callBack) {
        OkHttpUtil.getmIstance().postEqueue(url, prams, clazz, new ICall() {
            @Override
            public void falie(Exception e) {
                callBack.setData(e);
            }

            @Override
            public void success(Object data) {
                  callBack.setData(data);
            }
        });
    }
}
