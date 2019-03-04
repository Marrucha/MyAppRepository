package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {
    @InjectMocks
    private TrelloClient trelloClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init(){
        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        Mockito.when(trelloConfig.getTrelloToken()).thenReturn("test");
        Mockito.when(trelloConfig.getTrelloUserName()).thenReturn("user");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
      //given

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_board", "test_id", new ArrayList<>());

       URI uri = new URI("http://test.com/members/user/boards?key=test&token=test&fields=name,id&lists=all");

       Mockito.when(restTemplate.getForObject(uri,TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> fetchedTrelloBoards =trelloClient.getTrelloBoards();


  //then
        assertEquals(1,fetchedTrelloBoards.size());
        assertEquals("test_id",fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board",fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(),fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException{
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "Test task",
                "test description",
                "top",
                "test_id",
                "http://test.com",
                new Badges(0,new AttachmentType(new Trello(0,0)))
        );
        Mockito.when(restTemplate.postForObject(uri,null,CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
        //when
        CreatedTrelloCard newCard =trelloClient.createNewCard(trelloCardDto);

        assertEquals("Test task",newCard.getName());
        assertEquals("http://test.com",newCard.getShortUrl());

    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException{
        //given

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_board", "test_id", new ArrayList<>());

        URI uri = new URI("http://test.com/members/user/boards?key=test&token=test&fields=name,id&lists=all");

        Mockito.when(restTemplate.getForObject(uri,TrelloBoardDto[].class)).thenReturn(null);

        //when
        List<TrelloBoardDto> fetchedTrelloBoards =trelloClient.getTrelloBoards();

        //then
        assertEquals(0,fetchedTrelloBoards.size());

    }

}