package com.bittech.everthing.core.dao.impl;

import com.bittech.everthing.core.dao.FileIndexDao;
import com.bittech.everthing.core.model.Condition;
import com.bittech.everthing.core.model.Thing;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileIndexDaoImpl implements FileIndexDao {

    private static final Object FileType = null;
    private final DataSource dataSource;

    public FileIndexDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void index(Thing thing) {
        Connection connection=null;//连接
        PreparedStatement statement=null;//命令

        try{
            //数据库连接
            connection =dataSource.getConnection();
            //准备sql语句

            String sql = "insert into file_index(name, path, depth, file_type) values (?,?,?,?)";
            //准备命令
            statement = connection.prepareStatement(sql);
            //设置参数
            statement.setString(1, thing.getName());
            statement.setString(2, thing.getPath());
            statement.setInt(3, thing.getDepth());
            //FileType.DOC -> DOC
            statement.setString(4, thing.getFileType().name());
            //5.执行命令 
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            releaseResource(null,statement,connection);
        }
    }

    private void releaseResource(Object o, PreparedStatement statement, Connection connection) {
    }


    @Override
    public List<Thing> search(Condition condition) {

        List<Thing>things=new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            condition = (Condition) dataSource.getConnection();

            //String sql="select name, path, depth, file_type from file_index ";
            StringBuffer sqlBuilder =new StringBuffer();

            sqlBuilder.append("select name, path, depth, file_type from file_index ");
            sqlBuilder.append("where")
                    //name匹配原则，前模糊，后模糊，前后模糊
                    .append("name like '%")
                    .append(condition.getName())
                    .append("%' ");
            if(condition.getFileType()!=null){
                sqlBuilder.append(" order by depth ")
                 .append(condition.getOrderByAsc() ? "asc" : "desc")
                .append(" limit ")
                .append(condition.getLimit())
               .append(" offset 0 ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return things;
    }
}
