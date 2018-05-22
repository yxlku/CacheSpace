package com.longpc.cachesspace_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.longpc.cachesspace.CacheSpaceCallback;
import com.longpc.cachesspace.CacheSpaceUtil;

public class MainActivity extends Activity {
    /** 缓存key*/
    public static final String SPACKE_KEY = "space_key";
    /** 缓存时间*/
    public static final int SPACE_TIME = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCacheSpace();
            }
        });
    }

    /**
     * 测试缓存时间
     */
    private void testCacheSpace(){
        CacheSpaceUtil.builder(this)
                .cacheSpaceKey(SPACKE_KEY)
                .cacheTime(SPACE_TIME)
                .cacheSpaceInterface(new CacheSpaceCallback() {
                    @Override
                    public void cacheTimeEnd() {
                        Log.e("ssss","ssss");
                        Toast.makeText(MainActivity.this, "你点我了啊！", Toast.LENGTH_SHORT).show();
                    }
                })
                .build().execute();
    }
}
