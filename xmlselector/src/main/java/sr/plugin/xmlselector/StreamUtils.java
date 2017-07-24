package sr.plugin.xmlselector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TEST on 2017/7/24.
 */

public class StreamUtils {
    /**
     * 序列化,List
     */
    public static <T> boolean writeObject(List<T> list, File file) {
        synchronized (file) {
            T[] array = (T[]) list.toArray();
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(array);
                out.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化,List
     */
    public static <E> List<E> readObjectForList(File file) {
        synchronized (file) {
            E[] object;
            ObjectInputStream out = null;
            try {
                out = new ObjectInputStream(new FileInputStream(file));
                object = (E[]) out.readObject();
                return new ArrayList<E>(Arrays.asList(object));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
