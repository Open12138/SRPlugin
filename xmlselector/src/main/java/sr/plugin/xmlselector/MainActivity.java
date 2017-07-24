package sr.plugin.xmlselector;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;
import smartrobot.api.SmartSDK;
import sr.plugin.base.BaseActivity;
import sr.plugin.base.BaseService;
import sr.plugin.base.Util;

public class MainActivity extends BaseActivity {

    XmlAdapter adapter = null;
    TextView tvshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGeneralTitle(R.string.app_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);
        PluginImpl.init();

        tvshow = (TextView) findViewById(R.id.tvshow);

        BaseService.refreshData(this);

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refersh();
                swipe.setRefreshing(false);
            }
        });

        ListView lv = (ListView) findViewById(R.id.lv_xml);
        adapter = new XmlAdapter(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.click(i);
            }
        });
        lv.setAdapter(adapter);

        refersh();

        EventBus.getDefault().register(this); //第1步: 注册
    }

    public void refersh() {
        adapter.refresh();
        SmartSDK.log("数量:" + adapter.getCount());
        if (adapter.getCount() > 0) tvshow.setVisibility(View.GONE);
        else tvshow.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(String event) {
        refersh();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                new AlertDialog.Builder(this).setTitle("关于").setMessage("本插件可收集Xml消息").setPositiveButton("ok", null).create().show();
                break;
            case R.id.action_clear:
                adapter.clear();
                refersh();
                break;
            case R.id.action_refresh:
                refersh();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册
    }
}
