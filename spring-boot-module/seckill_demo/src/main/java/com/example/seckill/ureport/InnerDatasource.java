package com.example.seckill.ureport;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author KPQ
 * @date 2022/2/8
 */
@Component
@RequiredArgsConstructor
public class InnerDatasource implements BuildinDatasource {

    private final DataSource dataSource;

    @Override
    public String name() {
        return "内部数据源";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
