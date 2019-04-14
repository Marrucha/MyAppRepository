package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        // Given
        String listId = "111";
        String listName = "My List";
        boolean listIsClosed = false;
        String boardName = "My Board";
        String boardId = "222";

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto(listName,listId,listIsClosed));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto(boardName, boardId, lists));
        // When
        List<TrelloBoard> boardList = trelloMapper.mapToBoards(trelloBoardDto);
        // Then
        Assert.assertEquals(boardId, boardList.get(0).getId());
        Assert.assertEquals(boardName, boardList.get(0).getName());
        Assert.assertEquals(1, boardList.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        // Given
        String listId = "111";
        String listName = "My List";
        boolean listIsClosed = false;
        String boardName = "My Board";
        String boardId = "222";

        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList(listName,listId, listIsClosed));

        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard(boardName, boardId, lists));
        // When
        List<TrelloBoardDto> boardListDto = trelloMapper.mapToBoardsDto(trelloBoard);
        // Then
        Assert.assertEquals(boardId, boardListDto.get(0).getId());
        Assert.assertEquals(boardName, boardListDto.get(0).getName());
        Assert.assertEquals(1, boardListDto.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        // Given
        String listId = "111";
        String listName = "My List";
        boolean listIsClosed = false;

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto(listName, listId, listIsClosed));
        // When
        List<TrelloList> trelloLists = trelloMapper.mapToLists(lists);
        // Then
        Assert.assertEquals(listId, trelloLists.get(0).getId());
        Assert.assertEquals(listName, trelloLists.get(0).getName());
        Assert.assertEquals(listIsClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // Given
        String listId = "111";
        String listName = "My List";
        boolean listIsClosed = false;

        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList(listName, listId,  listIsClosed));
        // When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListsDto(lists);
        // Then
        Assert.assertEquals(listId, trelloLists.get(0).getId());
        Assert.assertEquals(listName, trelloLists.get(0).getName());
        Assert.assertEquals(listIsClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        // Given
        String cardName = "MyCard";
        String cardDescription = "My Card description";
        String cardPos = "Card Pos";
        String cardListId = "111";
        TrelloCardDto trelloCardDto = new TrelloCardDto(cardName, cardDescription, cardPos, cardListId);
        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        // Then
        Assert.assertEquals(cardName, trelloCard.getName());
        Assert.assertEquals(cardDescription, trelloCard.getDescription());
        Assert.assertEquals(cardPos, trelloCard.getPos());
        Assert.assertEquals(cardListId, trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        // Given
        String cardName = "My card";
        String cardDescription = "My Card description";
        String cardPos = "Card Pos";
        String cardListId = "111";
        TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        // Then
        Assert.assertEquals(cardName, trelloCardDto.getName());
        Assert.assertEquals(cardDescription, trelloCardDto.getDesc());
        Assert.assertEquals(cardPos, trelloCardDto.getPos());
        Assert.assertEquals(cardListId, trelloCardDto.getListId());
    }

}