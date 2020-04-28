package com.luffy.pushlib;

import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

/**
 * Created by lvlufei on 2018/10/23
 *
 * @desc 友盟推送注册
 */
public class UMRegister {

    public static PushAgent umPushAgent(Context context) {
        PushAgent mPushAgent;
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        mPushAgent = PushAgent.getInstance(context);
        PushAgent.getInstance(context).onAppStart();
        mPushAgent.setNoDisturbMode(23, 0, 7, 0);//SDK默认在“23:00”到“7:00”之间收到通知消息时不响铃，不振动，不闪灯
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //声音
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);//呼吸灯
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SERVER);//振动
        return mPushAgent;
    }

    /**
     * 通知点击监听
     *
     * @param context
     * @param umengNotificationClickHandler
     */
    public static void setNotificationClickHandler(Context context, UmengNotificationClickHandler umengNotificationClickHandler) {
        UMRegister.umPushAgent(context).setNotificationClickHandler(umengNotificationClickHandler);
    }

    /**
     * 自定义消息
     *
     * @param context
     * @param umengMessageHandler
     */
    public static void setMessageHandler(Context context, UmengMessageHandler umengMessageHandler) {
        UMRegister.umPushAgent(context).setMessageHandler(umengMessageHandler);
    }

    /**
     * 注册
     *
     * @param context
     * @param iUmengRegisterCallback
     */
    public static void register(Context context, IUmengRegisterCallback iUmengRegisterCallback) {
        UMRegister.umPushAgent(context).register(iUmengRegisterCallback);
    }


    /**
     * 小米通道
     *
     * @param mContext
     * @param appID
     * @param appKey
     */
    public static void MiPushRegistar(Context mContext, String appID, final String appKey) {
        MiPushRegistar.register(mContext, appID, appKey);
    }

    /**
     * 华为通道
     *
     * @param mContext
     */
    public static void HwPushRegistar(Context mContext) {
        HuaWeiRegister.register(mContext);
    }

    /**
     * 魅族通道
     *
     * @param mContext
     * @param appID
     * @param appKey
     */
    public static void MzPushRegistar(Context mContext, String appID, final String appKey) {
        MeizuRegister.register(mContext, appID, appKey);
    }

}
