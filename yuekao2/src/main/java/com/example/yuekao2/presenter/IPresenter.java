package com.example.yuekao2.presenter;

import java.util.Map;

public interface IPresenter {
    void requestData(String url, Map<String,String>prams,Class clazz);
}
