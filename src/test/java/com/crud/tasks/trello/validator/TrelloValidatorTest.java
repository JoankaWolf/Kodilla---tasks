package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TrelloValidatorTest {


    @Test
    void validateTrelloBoardsTest() {

        //Given
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("001", "name one", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("002", "test", new ArrayList<>()));

        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals("name one", resultList.get(0).getName());
        assertEquals(1, resultList.size());
    }
}