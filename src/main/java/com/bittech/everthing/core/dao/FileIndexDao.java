package com.bittech.everthing.core.dao;

import com.bittech.everthing.core.model.Condition;
import com.bittech.everthing.core.model.Thing;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 业务访问数据库的CRUD
 */

public interface FileIndexDao {
    /**
     * 插入数据Thing
     * @param thing
     */

    void index (Thing thing);

    /**
     * 根据condition条件进行数据库的检索
     * @param condition
     * @return
     */
    List<Thing> search(Condition condition);

}
