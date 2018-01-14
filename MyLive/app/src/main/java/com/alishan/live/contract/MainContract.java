package com.alishan.live.contract;

import com.shan.netlibrary.contract.BaseView;

/**
 * 首页
 * Created by chenjunshan on 2017-12-20.
 */

public interface MainContract {
    interface Model {
    }

    interface View extends BaseView {
    }

    interface Presenter {
        void checkPsermissions();
    }
}
