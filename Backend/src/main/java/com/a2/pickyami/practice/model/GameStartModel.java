package com.a2.pickyami.practice.model;

import com.a2.pickyami.practice.MessageType;
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
