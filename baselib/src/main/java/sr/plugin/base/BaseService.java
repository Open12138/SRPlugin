package sr.plugin.base;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import smartrobot.api.SmartSDK;
import smartrobot.api.entity.AllQData;

public class BaseService extends Service {
    public BaseService() {
    }

    public static void refreshData(Context context) {
        context.startService(new Intent(context, BaseService.class));//更新数据
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getData();
        return super.onStartCommand(intent, flags, startId);
    }

    private void getData() {
        SmartSDK.getData(this, new SmartSDK.GetDataCallBack() {
            @Override
            public void success(AllQData allQData) {
                SmartSDK.allQData = allQData;//更新数据
                SmartSDK.log("插件成功加载" + allQData.allqdata.size() + "个QQ的数据 ActionQQ:" + SmartSDK.allQData.actionqq);
            }

            @Override
            public void failed(String reason) {
                Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_LONG).show();
            }
        });
    }
}
