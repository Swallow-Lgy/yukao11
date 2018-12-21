package com.example.dell.yukao1.model;

import com.example.dell.yukao1.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void rquestData(String url, Map<String,String>prams, Class clazz, MyCallBack callBack);
}
