package ru.tinkoff.edu.java.scrapper.domain.dao.so_answer_count;

import ru.tinkoff.edu.java.scrapper.domain.dto.AnswerCountSODTO;

public interface AnswerCountSODAO {
    AnswerCountSODTO add(AnswerCountSODTO answerCount);
    AnswerCountSODTO findByLinkId(long linkId);
    AnswerCountSODTO update(AnswerCountSODTO answerCount);
    boolean containsByLinkId(long linkId);
}
