package ru.tinkoff.edu.java.scrapper.domain.dao.so_answer_count.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.dao.link.LinkMapper;
import ru.tinkoff.edu.java.scrapper.domain.dao.so_answer_count.AnswerCountSODAO;
import ru.tinkoff.edu.java.scrapper.domain.dao.so_answer_count.AnswerCountSOMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.AnswerCountSODTO;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerCountSODAOImpl implements AnswerCountSODAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public AnswerCountSODTO add(AnswerCountSODTO repositoryIssue) {
        return jdbcTemplate.queryForObject("INSERT INTO so_answer_count(link_id, count) VALUES (?, ?) RETURNING *;", new AnswerCountSOMapper(), repositoryIssue.linkId(), repositoryIssue.count());
    }

    @Override
    public AnswerCountSODTO findByLinkId(long linkId) {
        return jdbcTemplate.queryForObject("SELECT * FROM so_answer_count WHERE link_id = ?", new AnswerCountSOMapper(), linkId);
    }

    @Override
    public AnswerCountSODTO update(AnswerCountSODTO answerCount) {
        return jdbcTemplate.queryForObject("UPDATE so_answer_count SET count=? WHERE link_id = ? RETURNING *;", new AnswerCountSOMapper(), answerCount.count(), answerCount.linkId());
    }

    @Override
    public boolean containsByLinkId(long linkId) {
        String sql = "SELECT count(*) FROM so_answer_count WHERE link_id = ?";
        boolean exists = false;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, linkId);
        exists = count != null && count > 0;
        return exists;
    }
}
