package ru.tinkoff.edu.java.scrapper.domain.dao.github_repository_issue;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.dto.ChatLinkDTO;
import ru.tinkoff.edu.java.scrapper.domain.dto.GitHubRepositoryIssueDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GitHubRepositoryIssueMapper implements RowMapper<GitHubRepositoryIssueDTO> {

    @Override
    public GitHubRepositoryIssueDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GitHubRepositoryIssueDTO(rs.getLong("id"), rs.getLong("link_id"), rs.getString("state"));
    }
}
