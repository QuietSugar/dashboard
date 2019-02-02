package com.maybe.cache;

import com.maybe.system.SpringContextUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;


/**
 * Maybe has infinite possibilities
 *
 * Cache工具类
 *
 * @author Created by Maybe on 2018/3/4
 */
public class CacheUtils {
    private static CacheManager cacheManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtils.class);

    private static final String SYS_CACHE = "sysCache";

    /**
     * 通过key获取默认缓存SYS_CACHE的值
     *
     * @param key key
     * @return 返回String类型
     */
    public static String getValue(String key) {
        return getValue(SYS_CACHE, key);
    }

    /**
     * 通过key获取默认缓存的值
     *
     * @param key key
     * @return 返回String类型
     */
    public static String getValue(String cacheName, String key) {
        return getCache(cacheName).get(key).toString();
    }

    /**
     * 写入默认缓存SYS_CACHE
     *
     * @param key   key
     * @param value value
     */
    public static void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 写入缓存SYS_CACHE
     *
     * @param cacheName cacheName
     * @param key       key
     * @param value     value
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 从默认缓存SYS_CACHE中移除
     *
     * @param key key
     */
    public static void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName cacheName
     * @param key       key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 获得已定义好的 cache 对象
     *
     * @param cacheName 缓存名称
     * @return Cache 缓存
     */
    public static Cache getCache(String cacheName) {
        return getCacheManager().getCache(cacheName);
    }

    /**
     * 获得 cacheManager 对象
     * 通过bean的名称
     *
     * @return cacheManager
     */
    public static CacheManager getCacheManagerByName() {
        if (cacheManager == null) {
            cacheManager = SpringContextUtil.getBean("appEhCacheCacheManager");
        }
        return cacheManager;
    }

    /**
     * 获得 cacheManager 对象
     * 通过bean的class类别
     *
     * @return cacheManager
     */
    private static CacheManager getCacheManager() {
        //获取EhCacheCacheManager类
        EhCacheCacheManager ehCacheCacheManager = SpringContextUtil.getApplicationContext().getBean(EhCacheCacheManager.class);
        //获取CacheManager类
        return ehCacheCacheManager.getCacheManager();
    }
}