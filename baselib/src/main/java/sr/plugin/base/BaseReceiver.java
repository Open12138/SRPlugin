package sr.plugin.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import smartrobot.api.SmartSDK;
import smartrobot.api.entity.RecFriendMsg;
import smartrobot.api.entity.RecGroupMsg;
import smartrobot.api.entity.RecGroupXml;

public class BaseReceiver extends BroadcastReceiver {

    public static IPlugin iPlugin = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (!intent.getAction().equals(SmartSDK.BroadcastReceiverAction)) return;
        try {
            long actionQQ = 0l;//当前机器人登陆的QQ
            int cmd = intent.getIntExtra(SmartSDK.CMD, -1);
            switch (cmd) {
               /* case SmartSDK.CMD_REC_GROUP_TXTMSG:
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

                    break;*/
                case SmartSDK.CMD_REFRESH_DATA:
                    //更新数据 必须在Service或Activity中获取数据
                    actionQQ = intent.getLongExtra(SmartSDK.ACTION_QQ, 0l);
                    SmartSDK.log("更新数据");
                    BaseService.refreshData(context);
                    break;
                default:
//                    SmartSDK.log("未知CMD:" + cmd);
                    break;
            }
            if (iPlugin != null) {
//                SmartSDK.log("onIntent处理");
                iPlugin.onIntent(context, intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
