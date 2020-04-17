package top.ywlog.mycache.manager;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Durian
 * @Date: 2020/4/17 12:45
 * @Description: 自定义缓存
 */
@Component
public class MyCacheManager<T>
{
    /** 使用ConcurrentHashMap创建内存中的缓存 */
    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T getValue(String key)
    {
        return cache.get(key);
    }

    public void add(String key, T value)
    {
        cache.put(key, value);
    }

    public void evictCache(String key)
    {
        cache.remove(key);
    }

    public void clear()
    {
        cache.clear();
    }
}
