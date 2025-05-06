package com.example.Api_TreeCount_Fiap.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectionTester {

    private final JdbcTemplate jdbcTemplate;

    public ConnectionTester(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean testarConexao() {
        try {
            // Consulta simples ao Oracle (DUAL é uma tabela virtual do Oracle)
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}