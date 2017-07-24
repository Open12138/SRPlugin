package sr.plugin.xmlselector;

import java.io.Serializable;

import smartrobot.api.SmartSDK;
import smartrobot.api.entity.RecGroupXml;

/**
 * Created by TEST on 2017/7/24.
 */

public class GroupXml implements Serializable {

    public long myQQ = 0L;
    public long msgId = 0L;
    public long guin = 0L;
    public String groupName = "";
    public long senderUin = 0L;
    public String senderNick = "";
    public String xmlmsg = "";
    public long time = 0L;

    public String toString() {
        return "群XML消息 群名:" + this.groupName + " 群号:" + this.guin + " 发送者马甲:" + this.senderNick + " 发送者QQ:" + this.senderUin + " 时间:" + SmartSDK.formatTime10(this.time) + " 内容:" + this.xmlmsg;
    }

}
