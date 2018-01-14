package com.alishan.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alishan.live.contract.MainContract;
import com.alishan.live.databinding.ActivityMainBinding;
import com.alishan.live.presenter.MainPresenter;
import com.alishan.live.ui.BaseActivity;
import com.netease.nimlib.sdk.avchat.AVChatCallback;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.model.AVChatNotifyOption;
import com.shan.netlibrary.net.BaseBean;

public class MainActivity extends BaseActivity<ActivityMainBinding, Object> implements MainContract.View {
    private MainPresenter presenter;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        titleBinding.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
        presenter = new MainPresenter(this, this, mBinding);
        presenter.checkPsermissions();
    }

    @Override
    public void onSuccess(BaseBean baseBean, long mTag) {

    }

    @Override
    public void onFailure(Throwable e, long mTag) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelAllRequest();
    }


    /**
     * 拨打音视频
     */
    public void outGoingCalling(String account, final AVChatType callTypeEnum) {
        /*AVChatNotifyOption notifyOption = new AVChatNotifyOption();
        //附加字段
        notifyOption.extendMessage = "extra_data";
        //是否兼容WebRTC模式
        //notifyOption.webRTCCompat = webrtcCompat;
        //默认forceKeepCalling为true，开发者如果不需要离线持续呼叫功能可以将forceKeepCalling设为false
        //notifyOption.forceKeepCalling = false;
        //打开Rtc模块
        AVChatManager.getInstance().speakerEnabled()

        //this.callingState = (callTypeEnum == AVChatType.VIDEO ? CallStateEnum.VIDEO : CallStateEnum.AUDIO);

        //设置自己需要的可选参数
        //AVChatManager.getInstance().setParameters(avChatParameters);

        //视频通话
        if (callTypeEnum == AVChatType.VIDEO) {
            //打开视频模块
            AVChatManager.getInstance().vid();

            //创建视频采集模块并且设置到系统中
            if (mVideoCapturer == null) {
                mVideoCapturer = AVChatVideoCapturerFactory.createCameraCapturer();
                AVChatManager.getInstance().setupVideoCapturer(mVideoCapturer);
            }

            //设置本地预览画布
            AVChatManager.getInstance().setupLocalVideoRender(textureViewRenderer, false, scalingType);

            //开始视频预览
            AVChatManager.getInstance().startVideoPreview();
        }

        //呼叫
        AVChatManager.getInstance().call("shan2018", callTypeEnum, notifyOption, new AVChatCallback<AVChatData>() {

            @Override
            public void onSuccess(AVChatData avChatData) {

            }

            @Override
            public void onFailed(int code) {

            }

            @Override
            public void onException(Throwable exception) {

            }
        });*/
    }
}
