package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New trello Card";

    @Autowired
    TrelloClient trelloClient;
    @Autowired
    SimpleEmailService emailService;
    @Autowired
    AdminConfig adminConfig;


    public List<TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();

    }

    public CreatedTrelloCard createdTrelloCard(final TrelloCardDto trelloCardDto){

        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        Optional.ofNullable(newCard).ifPresent(card->emailService.send(new Mail(adminConfig.getAdminMail(),null, SUBJECT, "New card " + trelloCardDto.getName()  + " has been created")));
        return newCard;
    }
}
