package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloNoArgsTests {

    @Test
    public void trelloDtoNoArgsConstructorTest() {
        //Given
        TrelloDto trelloDto = new TrelloDto();

        //When

        //Then
        assertEquals(0, trelloDto.getBoard());
        assertEquals(0, trelloDto.getCard());
    }

    @Test
    public void trelloBoardDtoNoArgsConstructor() {

        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();

        //When

        //Then
        assertNull(trelloBoardDto.getId());
        assertNull(trelloBoardDto.getName());
    }

    @Test
    public void noArgsConstructorTest() {
        //Given
        AttachmentsByTypeDto attachmentsByTypeDto = new AttachmentsByTypeDto();

        //When

        //Then
        assertNull(attachmentsByTypeDto.getTrello());
    }
    @Test
    public void noArgsTrelloCardDtoConstructorTest() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();

        //When

        //Then
        assertNull(trelloCardDto.getName());
    }

}
