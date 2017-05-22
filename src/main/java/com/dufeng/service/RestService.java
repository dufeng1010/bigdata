package com.dufeng.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.dufeng.model.OrderChannel;

@Service
public class RestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OrderChannel> getOrderChannels() {
        return jdbcTemplate.query("select id, date, channel, num from ecstore_orderstat", 
                new RowMapper<OrderChannel>() {
            @Override
            public OrderChannel mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderChannel o = new OrderChannel();
                o.setCount(rs.getInt("num"));
                o.setLabel(rs.getString("channel"));

                return o;
            }
        });
    }

}
