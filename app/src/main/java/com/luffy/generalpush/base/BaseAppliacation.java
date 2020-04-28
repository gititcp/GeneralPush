package com.luffy.generalpush.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.luffy.pushlib.UMPushConstans;
import com.luffy.pushlib.UMRegister;
import com.luffy.generalpush.helper.ConstansHelper;
import com.luffy.generalpush.ui.MainActivity;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * Created by lvlufei on 2018/10/23
 *
 * @name
 * @desc
 */
public class BaseAppliacation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化友盟分享
        UMConfigure.init(this, ConstansHelper.Umeng.APPKEY, "umeng", UMConfigure.DEVICE_TYPE_PHONE, ConstansHelper.Umeng.MESSAGE_SECRET);
        //初始化友盟推送
        initUmengPush();
    }

    /**
     * 初始化友盟推送
     */
    private void initUmengPush() {
        // 通知点击监听
        UMRegister.setNotificationClickHandler(this, new UmengNotificationClickHandler() {

            @Override
            public void launchApp(Context context, UMessage msg) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        });
        // 注册
        UMRegister.register(this, new IUmengRegisterCallback() {
            //注册成功会返回device token
            @Override
            public void onSuccess(String deviceToken) {
                /*保存devicetoken*/
                sendBroadcast(new Intent(UMPushConstans.Action.UPDATE_STATUS_ACTION));
            }

            @Override
            public void onFailure(String var1, String var2) {
                sendBroadcast(new Intent(UMPushConstans.Action.UPDATE_STATUS_ACTION));
            }
        });
        // 小米通道
        UMRegister.MiPushRegistar(this, ConstansHelper.XiaoMi.APPID, ConstansHelper.XiaoMi.APPKEY);
        // 华为通道
        UMRegister.HwPushRegistar(this);
        // 魅族通道
        UMRegister.MzPushRegistar(this, ConstansHelper.MeiZu.APPID, ConstansHelper.MeiZu.APPKEY);
    }
}
