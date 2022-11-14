package com.crud.tasks.mapper;


import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    void mapToBoards() {

        //Given
        TrelloListDto trelloListDto = new TrelloListDto("001", "name", true);
        List<TrelloListDto> trelloListDtoList = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("001","List", trelloListDtoList);
        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
       assertEquals(trelloBoardDtoList.size(), trelloBoardList.size());
       assertEquals(trelloBoardList.get(0).getName(), "List");
    }

    @Test
    void mapToBoardsDto() {

        //Given
        TrelloList trelloList = new TrelloList("001", "name", true);
        List<TrelloList> trelloListList = Arrays.asList(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("001","List", trelloListList);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(trelloBoardList.size(), trelloBoardDtoList.size());
        assertEquals(trelloBoardDtoList.get(0).getName(), "List");
    }

    @Test
    void mapToList() {

        //Given
        TrelloListDto trelloListDto = new TrelloListDto("001", "name", true);
        List<TrelloListDto> trelloListDtoList = Arrays.asList(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(trelloListDtoList.size(), trelloLists.size());
        assertEquals(trelloLists.get(0).getName(), "name");
    }

    @Test
    void mapToListDto() {

        //Given
        TrelloList trelloList = new TrelloList("001", "name", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloLists.size(), trelloListsDto.size());
        assertEquals(trelloListsDto.get(0).getName(), "name");
    }


    @Test
    void mapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("card name","description", "top", "001");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), "card name");
    }

    @Test
    void mapToCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card name","description", "top", "001");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCard.getName(), "card name");
    }
}