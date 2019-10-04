package com.tregz.mvc.list;

import android.util.SparseArray;

import androidx.annotation.NonNull;

import com.tregz.mvc.data.item.ItemApple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListApple {
    private static final ListApple sInstance = new ListApple();

    public static ListApple getInstance() {
        return sInstance;
    }

    private ListApple() {
    }

    private final List<ItemApple> list = new ArrayList<>();
    private final Set<ItemApple> set = new HashSet<>();
    private final SparseArray<ItemApple> map = new SparseArray<>();

    public int getListCount() {
        return list.size();
    }

    public int getSetCount() {
        return set.size();
    }

    public void clear() {
        list.clear();
        set.clear();
        map.clear();
    }

    public ItemApple add(@NonNull ItemApple apple) {
        list.add(apple);
        set.add(apple);
        map.put(map.size(), apple);
        return apple;
    }

}
