package sr.plugin.base;

import android.content.Context;
import android.content.Intent;

/**
 * Created by TEST on 2017/7/24.
 */

public interface IPlugin {
    void onIntent(Context context, Intent intent);
}
