package com.a2.pickyami.game.model;


import com.a2.pickyami.game.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameStartModel {
     private String message;
     private String sender;
     private MessageType type;
}
