package com.cn.tj.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 张梦楠
 * @简书：https://www.jianshu.com/u/d611be10d1a6
 * @码云：https://gitee.com/zhangqiye/events
 * @Date: 2018/6/15
 *
 * 用于处理jdbc相关的操作
 *
 **/
public class JdbcUtils {


    /**
    *  给sql语句设值值
    * @date 2018/6/15 20:46
    * @param ps
    * @param params
    * @return void
    */
    public static void handlerParams(PreparedStatement ps, Object params[]){
        if (params != null){
            for (int i =0; i< params.length; i++){
                try {
                    ps.setObject(1+i,params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
