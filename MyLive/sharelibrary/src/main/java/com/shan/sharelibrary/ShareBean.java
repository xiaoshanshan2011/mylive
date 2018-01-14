package com.shan.sharelibrary;

import com.umeng.socialize.media.UMWeb;

/**
 * Created by chenjunshan on 17-2-7.
 */

public class ShareBean {
    private UMWeb umWeb;

    public ShareBean(UMWeb umWeb) {
        this.umWeb = umWeb;
    }


    public UMWeb getUmWeb() {
        return umWeb;
    }

    public void setUmWeb(UMWeb umWeb) {
        this.umWeb = umWeb;
    }
}
