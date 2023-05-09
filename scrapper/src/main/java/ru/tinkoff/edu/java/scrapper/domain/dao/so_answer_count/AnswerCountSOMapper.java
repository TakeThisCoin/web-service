package ru.tinkoff.edu.java.scrapper.domain.dao.so_answer_count;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.AnswerCountSODTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerCountSOMapper implements RowMapper<AnswerCountSODTO> {

    @Override
    public AnswerCountSODTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AnswerCountSODTO(rs.getLong("link_id"), rs.getLong("count"));
    }
}
