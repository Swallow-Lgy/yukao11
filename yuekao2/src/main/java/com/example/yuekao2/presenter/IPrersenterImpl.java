package com.example.yuekao2.presenter;

import com.example.yuekao2.callback.MyCallBack;
import com.example.yuekao2.mdel.IModelImpl;
import com.example.yuekao2.view.IView;

import java.util.HashMap;
import java.util.Map;

public class IPrersenterImpl implements IPresenter{
    private IView miView;
    private IModelImpl miModel;
    public IPrersenterImpl(IView iView){
        miView =iView;
        miModel = new IModelImpl();
    }
    @Override
    public void requestData(String url, Map<String, String> prams, Class clazz) {
          miModel.srequestDatam(url,prams, clazz, new MyCallBack() {
              @Override
              public void setData(Object data) {
                  miView.success(data);
              }
          });
    }
    public void des(){
        if (miModel!=null){
            miModel=null;
        }
        if (miView!=null){
            miView=null;
        }

    }
}
