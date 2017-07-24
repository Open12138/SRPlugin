package sr.plugin.xmlselector;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import smartrobot.api.SmartSDK;
import sr.plugin.base.GlideCircleTransform;
import sr.plugin.base.Util;

/**
 * Created by TEST on 2017/7/24.
 */

public class XmlAdapter extends BaseAdapter {
    private static List<GroupXml> groupXmls = new ArrayList<>();
    Context context;
    GlideCircleTransform glideCircleTransform = null;


    public XmlAdapter(Context context) {
        this.context = context;
        glideCircleTransform = new GlideCircleTransform(context);
        refresh();
    }

    public void refresh() {
        groupXmls = Collector.getInstance(context).getXmls();
        notifyDataSetChanged();
    }

    public void click(int index) {
        final GroupXml groupXml = (GroupXml) getItem(index);
        SmartSDK.log(groupXml.toString());
        new AlertDialog.Builder(context).setTitle("详情").setMessage("来自:" + groupXml.senderNick + "\n群:" + groupXml.groupName +
                "\n" + groupXml.xmlmsg).setPositiveButton("复制", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText(groupXml.xmlmsg);
            }
        }).create().show();
    }

    public void clear() {
        Collector.getInstance(context).clear();
        refresh();
    }

    @Override
    public int getCount() {
        return groupXmls.size();
    }

    @Override
    public Object getItem(int i) {
        return groupXmls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.xml_item, null);
        ImageView img = (ImageView) view.findViewById(R.id.img_face);
        TextView tv = (TextView) view.findViewById(R.id.tv_desc);

        GroupXml groupXml = (GroupXml) getItem(i);
        tv.setText("来自:" + groupXml.senderNick + " 群:" + groupXml.groupName);

        String url = Util.groupFaceUrl(groupXml.guin);
        SmartSDK.log("URL:" + url);
        Glide.with(context)
                .load(url)//加载图片
                .transform(glideCircleTransform)
                .placeholder(R.mipmap.group)//正在加载时的图片
                .error(R.mipmap.group)//加载错误是的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);


        return view;
    }

    class ViewHolder {
        public CircleImageView img;
        private TextView tv;

        public void loadImg() {
            if (img == null) return;
        }
    }
}
