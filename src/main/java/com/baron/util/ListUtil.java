package com.baron.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jason on 2017/6/13.
 */
public class ListUtil {
    private ListUtil() {}

    public static <T> List<T> from(Queue<T> queue) {
        List<T> result = new ArrayList<>();
        queue.forEach(t -> result.add(t));
        return result;
    }
}
