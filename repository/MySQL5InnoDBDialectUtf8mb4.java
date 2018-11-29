package com.oee.pikachu.repository;

import org.hibernate.dialect.MySQL55Dialect;

/**
 * Created by Aqua on 2018/11/29.
 */
public class MySQL5InnoDBDialectUtf8mb4 extends MySQL55Dialect {

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci";
    }
}
