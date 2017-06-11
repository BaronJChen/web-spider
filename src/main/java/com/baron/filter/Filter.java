package com.baron.filter;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;

/**
 * Created by Jason on 2017/6/11.
 */
public interface Filter {
    default boolean filter(Page page, ResultItems resultItems) {
        return true;
    }
}
