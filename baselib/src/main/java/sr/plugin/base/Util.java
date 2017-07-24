package sr.plugin.base;

/**
 * Created by TEST on 2017/7/24.
 */

public class Util {
    /*Q群
    * http://p.qlogo.cn/gh/" + arg4 + "/" + arg4 + "/40?t=" + System.currentTimeMillis()
    *
    * 获取QQ头像

http://q2.qlogo.cn/headimg_dl?bs=QQ号&dst_uin=QQ号&dst_uin=QQ号&;dst_uin=QQ号&spec=100&url_enc=0&referer=bu_interface&term_type=PC
获取QQ昵称

http://r.pengyou.com/fcg-bin/cgi_get_portrait.fcg?uins=QQ号码
    *
    * */

    public static String groupFaceUrl(long guin) {
        return "http://p.qlogo.cn/gh/" + guin + "/" + guin + "/100?t=" + System.currentTimeMillis();
    }
}
