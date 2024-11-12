package com.a2.pickyami.gameplay.model;

import com.a2.pickyami.gameplay.MessageType;
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
