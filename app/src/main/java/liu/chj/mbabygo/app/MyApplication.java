package liu.chj.mbabygo.app;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.SocializeConstants;

import org.xutils.x;

import cn.smssdk.SMSSDK;

/**
 * 作者：柳成建
 * 日期：2016/11/8 - 15:22
 * 注释：
 */
public class MyApplication extends Application{
    private static MyApplication context;
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        context=this;
        //短信验证
        //等待审核
        SMSSDK.initSDK(this, "1b480e789f934", "89ad116850da8b4043e5eb3a97c3b528");

        if (instance == null) {
            instance = this;
    }
    /**
     * 初始化
     */
    UMShareAPI.get(this);
    /**
     * 开启debug环境,调试完毕后应该去掉该行代码恢复正式环境
     */
    Config.DEBUG = true;
    /**
     *
     */

    }
    {
        /**
         * 微信，qq，新浪微博 配置三方平台的appkey
         */
        SocializeConstants.APPKEY = "58832956ae1bf81fd80010e1";
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setWeixin("wx42128d1606dce056", "0e447781b317e3bc4ff40e6fb65bbee1");
        PlatformConfig.setSinaWeibo("2835904541", "646a44baf415c84e4787d7be4092281a");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

//      没有下载微信qq等软件，则跳转下载界面下载
//      其中qq 微信会跳转到下载界面进行下载，其他应用会跳到应用商店进行下载
        Config.isJumptoAppStore = true;

    }
    public static MyApplication getContext(){
        return context;
    }
    public static MyApplication getInstance() {
        return instance;
    }
}
