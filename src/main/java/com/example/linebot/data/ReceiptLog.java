package com.example.linebot.data;

import com.example.linebot.sevice.ReceiptResponse;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ReceiptLog {

    private JdbcTemplate jdbcTemplate;

    public ReceiptLog(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(ReceiptResponse response) {
        String sql = "insert into kakeibo VALUES (?, ?)";
        int n = jdbcTemplate.update(
                sql, response.date(), response.total_amount());
        return n;
    }

    public List<ReceiptResponse> selectAll() {
        String sql = "select date, total_amount from kakeibo";
        List<ReceiptResponse> selected = jdbcTemplate.query(
                sql, new DataClassRowMapper<>(ReceiptResponse.class));
        return selected;
    }
}
