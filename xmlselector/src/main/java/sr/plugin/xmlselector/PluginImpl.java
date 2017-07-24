package sr.plugin.xmlselector;

import android.content.Context;
import android.content.Intent;

import smartrobot.api.SmartSDK;
import smartrobot.api.entity.RecFriendMsg;
import smartrobot.api.entity.RecGroupMsg;
import smartrobot.api.entity.RecGroupXml;
import sr.plugin.base.BaseReceiver;
import sr.plugin.base.BaseService;
import sr.plugin.base.IPlugin;

/**
 * Created by TEST on 2017/7/24.
 */

public class PluginImpl implements IPlugin {

    public static void init() {
        if (BaseReceiver.iPlugin == null) BaseReceiver.iPlugin = new PluginImpl();
        SmartSDK.log("初始化接口");
    }

    @Override
    public void onIntent(Context context, Intent intent) {
        SmartSDK.log("-----------onIntent-------");
        long actionQQ = 0l;//当前机器人登陆的QQ
        int cmd = intent.getIntExtra(SmartSDK.CMD, -1);
        switch (cmd) {
            case SmartSDK.CMD_REC_GROUP_TXTMSG:
                //群文本消息
                actionQQ = intent.getLongExtra(SmartSDK.ACTION_QQ, 0l);//收到消息的QQ
                RecGroupMsg recGroupMsg = intent.getExtras().getParcelable(SmartSDK.ParcelObj);
                SmartSDK.log(recGroupMsg.toString());
                break;
            case SmartSDK.CMD_REC_GROUP_XMLMSG:
                //收到群XML消息
                actionQQ = intent.getLongExtra(SmartSDK.ACTION_QQ, 0l);//收到消息的QQ
                RecGroupXml recGroupXml = intent.getExtras().getParcelable(SmartSDK.ParcelObj);
                SmartSDK.log(recGroupXml.toString());
                break;
            case SmartSDK.CMD_REC_FRIEND_TXTMSG:
                //好友文本消息
                actionQQ = intent.getLongExtra(SmartSDK.ACTION_QQ, 0l);
                RecFriendMsg recFriendMsg = intent.getExtras().getParcelable(SmartSDK.ParcelObj);
                SmartSDK.log(recFriendMsg.toString());
                break;
            default:
                SmartSDK.log("未知CMD:" + cmd);
                break;
        }
    }
}
