package com.jason.manongapp.more.app;

import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jason.manongapp.base.BaseApplication;
import com.jason.manongapp.base.IComponentApplication;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MoreApplication implements IComponentApplication {

    @Override
    public void onCreate(BaseApplication application) {
        UMConfigure.init(application,"5c380a82b465f51b60000178","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
//        Log.i("TAG", "MoreonCreate: ");
//        Logger.i("moreApp");
    }

    {
        PlatformConfig.setQQZone("1108036605", "Iq1N0fbgnKg2izrD");
        PlatformConfig.setSinaWeibo("2330017991","76934ab5bbc048c3bdeb094d77e635e2","http://sns.whalecloud.com");
    }

}
