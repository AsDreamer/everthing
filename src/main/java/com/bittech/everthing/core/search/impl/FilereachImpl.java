package com.bittech.everthing.core.search.impl;

import com.bittech.everthing.core.dao.FileIndexDao;
import com.bittech.everthing.core.model.Condition;
import com.bittech.everthing.core.search.Filereach;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FilereachImpl implements Filereach {

    private final DataSource fileIndexdao;

    public FilereachImpl(FileIndexDao fileIndexdao){
        this.fileIndexdao = (DataSource) fileIndexdao;

    }
    @Override
    public List<String> search(Condition condition) {

        //数据库的处理逻辑
        return new ArrayList<>();

    }
}
