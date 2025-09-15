package com.github.cnxucheng.structure;

import com.github.cnxucheng.exception.NoSuchElementException;
import com.github.cnxucheng.exception.OutOfMaxSizeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * DSU
 *
 * @author xucheng
 * @since 1.0.0
 * @param <T>
 */
public class DSU<T> {
    // 最大大小
    private final int maxSize;
    // 索引
    private int index = 1;
    // 记录映射关系，方便后续用数组索引
    private final Map<T, Integer> hash = new HashMap<>();
    // 存储信息
    private final List<Integer> f = new ArrayList<>();

    public DSU() {
        this.maxSize = 100000;
        f.add(0);
    }
    public DSU(int size) {
        this.maxSize = size;
        f.add(0);
    }

    /**
     * 判断 a, b 是否属于同一块
     * @param a 元素a
     * @param b 元素b
     * @param exc 如果元素不存在是否抛出异常
     */
    public boolean same(T a, T b, boolean exc) {
        return find(a, exc) == find(b, exc);
    }

    /**
     * 判断 a, b 是否属于同一块
     * @param a 元素a
     * @param b 元素b
     */
    public boolean same(T a, T b) {
        int fa = find(a, false);
        int fb = find(b, false);
        if (fa == 0 || fb == 0) return false;
        return fa == fb;
    }

    /**
     * 将 a, b 所在块合并
     */
    public void merge(T a, T b) throws OutOfMaxSizeException {
        int idxA = getHashIndexWithInsert(a);
        int idxB = getHashIndexWithInsert(b);
        int rootA = find(idxA);
        int rootB = find(idxB);
        if (rootA != rootB) {
            f.set(rootA, rootB);
        }
    }

    /**
     * 找 a 的祖先下标
     */
    private int find(T a, boolean exc) throws NoSuchElementException {
        if (!hash.containsKey(a)) {
            if (exc) {
                throw new NoSuchElementException("该元素不存在");
            } else {
                return 0;
            }
        }
        int idx = hash.get(a);
        return find(idx);
    }

    /**
     * 直接使用下标的 find 方法（路径压缩）
     */
    private int find(int a) {
        if (f.get(a) != a) {
            f.set(a, find(f.get(a)));  // 递归查找并路径压缩
        }
        return f.get(a);
    }

    /**
     * 获取元素映射后的下标，如果不存在则插入
     */
    private int getHashIndexWithInsert(T a) throws OutOfMaxSizeException {
        if (!hash.containsKey(a)) {
            if (index > maxSize) {
                throw new OutOfMaxSizeException("ST表大小超限制");
            }
            hash.put(a, index);
            f.add(index);  // 新元素的父节点指向自己
            index++;
        }
        return hash.get(a);
    }
}