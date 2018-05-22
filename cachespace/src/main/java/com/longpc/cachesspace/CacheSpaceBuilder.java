package com.longpc.cachesspace;

public class CacheSpaceBuilder {

    private int mCacheTime = 60000;
    private String mCacheSpaceKey;
    private CacheSpaceCallback mCacheSpaceCallback;

    /**
     * 设置缓存时间 默认缓存时间为60000毫秒
     * @param cacheTime 缓存时间 单位毫秒
     * @return
     */
    public CacheSpaceBuilder cacheTime(int cacheTime){
        this.mCacheTime = cacheTime;
        return this;
    }

    public CacheSpaceBuilder cacheSpaceKey(String cacheSpaceKey){
        this.mCacheSpaceKey = cacheSpaceKey;
        return this;
    }

    public CacheSpaceBuilder cacheSpaceInterface(CacheSpaceCallback cacheSpaceCallback){
        this.mCacheSpaceCallback = cacheSpaceCallback;
        return this;
    }

    public CacheSpaceUtil build(){
        return new CacheSpaceUtil(mCacheTime, mCacheSpaceKey, mCacheSpaceCallback);
    }
}
