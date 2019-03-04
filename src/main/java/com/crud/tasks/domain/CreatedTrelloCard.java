package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    @JsonProperty("name")
    private String name;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty ("pos")
    private String pos;
    @JsonProperty("idList")
    private String listId;
    @JsonProperty("shortUrl")
    private String shortUrl;

    private int votes;
    private int card;
    private int board;

    @JsonProperty("badges")
    private void unpackNested(Map<String,Object> badges) {
        this.votes = Optional.ofNullable((int)badges.get("votes")).orElse(0);
        Map<String,Object> attachementType = (Map<String,Object>)badges.get("attachmentsByType");
        Map<String,Integer> trello = (Map<String,Integer>)attachementType.get("trello");
        this.card = trello.get("card");
        this.board = trello.get("board");
    }

}
