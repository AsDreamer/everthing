package com.bittech.everthing.core.search;

import com.bittech.everthing.core.model.Condition;

import java.util.List;

public interface Filereach {
    /**
     * 根据condition条进行检索
     * @param condition
     * @return
     */
    List<String> search(Condition condition);

}
