package com.example.dell.yukao1.presenter;

import com.example.dell.yukao1.callback.MyCallBack;
import com.example.dell.yukao1.model.IModelImpl;
import com.example.dell.yukao1.view.IView;

import java.util.Map;

public class IPermentImpl implements IPrsenter {
    private IView miView;
    private IModelImpl miModel;
    public IPermentImpl(IView iView){
        miView = iView;
        miModel = new IModelImpl();
    }
    @Override
    public void requestData(String url, Map<String, String> prams, Class clazz) {
         miModel.rquestData(url, prams, clazz, new MyCallBack() {
             @Override
             public void setData(Object data) {
                 miView.success(data);
             }
         });
    }
}
