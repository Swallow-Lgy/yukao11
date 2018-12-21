package com.example.yuekao2.mdel;

import com.example.yuekao2.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void srequestDatam(String url, Map<String,String>prams,Class clazz,MyCallBack callBack);
}
