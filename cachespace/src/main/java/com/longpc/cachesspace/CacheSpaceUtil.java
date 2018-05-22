package com.longpc.cachesspace;

import android.content.Context;
import android.text.TextUtils;

/**
 * 间隔操作
 */
public class CacheSpaceUtil {
    private static Context mContext;
    /** 缓存时间 -- 默认为60毫秒*/
    private int CACHE_TIME;
    /** 缓存key*/
    private final String KEY;
    /** 缓存后刷新*/
    public final CacheSpaceCallback CALLBACK;

    public CacheSpaceUtil(int cacheTime, String cacheSpaceKey, CacheSpaceCallback cacheSpaceCallback) {
        this.CACHE_TIME = cacheTime;
        this.KEY = cacheSpaceKey;
        this.CALLBACK = cacheSpaceCallback;
    }

    public static CacheSpaceBuilder builder(Context context){
        mContext = context;
        return new CacheSpaceBuilder();
    }

    /**
     * 设置本次请求时间（缓存时间）
     * @param pCurrCacheTime
     * @return
     */
    public CacheSpaceUtil setCacheTime(long pCurrCacheTime){
        //此时为重新执行方法的操作并储存当前时间
        setCacheTime(KEY, pCurrCacheTime);
        return this;
    }

    /**
     * 指定key设置本次请求的时间（缓存时间）
     * @param key 指定key
     * @param pCurrCacheTime 设置本次缓存时间
     * @return
     */
    public CacheSpaceUtil setCacheTime(String key, long pCurrCacheTime){
        //此时为重新执行方法的操作并储存当前时间
        DataHelper.setStringSF(mContext, key, pCurrCacheTime+"");
        return this;
    }
    /**
     * 获取上次请求的时间（缓存时间）
     * @return
     */
    private long getCacheSpaceTime(){
        long cacheTime = 0;
        String cacheTimeStr = DataHelper.getStringSF(mContext, KEY);
        if (TextUtils.isEmpty(cacheTimeStr)) {
            setCacheTime(0);
            cacheTime = 0;
        }else{
            cacheTime = Long.valueOf(cacheTimeStr);
        }
        return cacheTime;
    }

    public void execute(){
        //检查key是否为空
        boolean keyIsEmpty = TextUtils.isEmpty(KEY);
        if(keyIsEmpty){
            throw new RuntimeException("CacheSpaceUtil KEY 不能为空");
        }
        if (CALLBACK != null) {
            long currTime = System.currentTimeMillis();
            long cacheTime = getCacheSpaceTime();
            if (currTime - cacheTime >= CACHE_TIME) {
                //此时为重新执行方法的操作并储存当前时间
                setCacheTime(currTime);
                CALLBACK.cacheTimeEnd();
            }
        }
    }

    /**
     * 重置缓存时间
     * @return
     */
    public static void initCacheSpaceTime(Context pContext, String pCacheSpaceKey){
        //此时为重新执行方法的操作并储存当前时间
        long currCacheTime = System.currentTimeMillis();
        DataHelper.setStringSF(pContext, pCacheSpaceKey, currCacheTime+"");
    }

    /**
     * 删除对应的缓存
     * @param pCacheSpaceKey
     */
    public static void removeCacheKey(Context pContext, String pCacheSpaceKey){
        DataHelper.removeSF(pContext, pCacheSpaceKey);
    }

}
