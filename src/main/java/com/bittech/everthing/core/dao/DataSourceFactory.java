package com.bittech.everthing.core.dao;



import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang.text.StrBuilder;

import javax.sql.DataSource;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;
import java.lang.String;

public class DataSourceFactory {
    /**
     * 数据源
     */

    static volatile DruidDataSource dataSource;

    private DataSourceFactory(){

    }

    /**
     * 数据源初始化
     * @return
     */

    public static DataSource dataSource() {
        if (dataSource != null) {
            synchronized (DataSourceFactory.class) {
                //单例模式，双重检查
                if (dataSource == null) {
                    //实例化
                    dataSource = new DruidDataSource();

                    dataSource.setDriverClassName("org.h2.Driver");
                    //url username password
                    //采用的是H2的嵌入式数据库，数据库以本地方式存储，只需提供url接口
                    //jdbc
                    //获取当前路径
                    String workDir= System.getProperty("user.dri");
                    dataSource.setUrl("jdbc:h2:"+workDir+ File.separator+"everthing");

                }
            }
        }
        return dataSource;
    }
    public static void initDatabase(){
        //获取数据源
        DataSource dataSource = DataSourceFactory.dataSource();
        //获取sql语句
        try( InputStream in = DataSourceFactory.class.getClassLoader().getResourceAsStream("everthing.sql");){
            if(in==null){
                throw  new RuntimeException("NOt read init database");
            }
            StringBuffer sqlBuffer=new StringBuffer();
            try(BufferedReader reader=new BufferedReader(new InputStreamReader(in));){
                String line=null;
                while ((line=reader.readLine())!=null) {
                    if(!line.startsWith("--")){
                        sqlBuffer.append(line);
                    }
                }
            }

            String sql=sqlBuffer.toString();
            //获取数据库连接
            Collection collection= (Collection) dataSource.getConnection();
            //创建命令
           //PreparedStatement statement = collection.parallelStream(sql);
            //执行sql语句
           // statement.execute();
           // collection.close();
           // statement.close();

        }catch (IOException e){

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //获取数据库连接与名称执行sql语句

    }
}

