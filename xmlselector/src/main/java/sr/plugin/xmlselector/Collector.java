package sr.plugin.xmlselector;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import smartrobot.api.SmartSDK;

/**
 * Created by TEST on 2017/7/24.
 */

public class Collector {
    private static Collector collector = null;
    private File xmlfile;

    /**
     * @return
     */
    public List<GroupXml> getXmls() {
        readXml();//读取
        return xmls;
    }

    private List<GroupXml> xmls;

    private Collector(Context context) {
        File dir = context.getDir("xml", Context.MODE_PRIVATE);
        xmlfile = new File(dir, "qasdfghjkl");
        xmls = new ArrayList<>();
        if (xmlfile.exists()) {
            readXml();
        }
    }

    public static Collector getInstance(Context context) {
        collector = collector == null ? new Collector(context) : collector;
        return collector;
    }

    private void readXml() {
        List<GroupXml> t = StreamUtils.readObjectForList(xmlfile);
        if (t == null) {
            if (xmls != null && xmls.size() > 0) {

            } else {
                xmls = new ArrayList<>();
            }
        } else {
            xmls = t;
        }
        SmartSDK.log("读取Xml Size:" + xmls.size());
    }

    public void add(GroupXml groupXml) {
        xmls.add(groupXml);
        boolean r = StreamUtils.writeObject(xmls, xmlfile);
        SmartSDK.log("写入Xml结果:" + r);
    }

    public void clear() {
        if (xmls != null) xmls.clear();
        if (xmlfile.exists()) xmlfile.delete();
    }
}
