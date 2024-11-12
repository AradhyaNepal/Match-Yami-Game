package com.a2.pickyami.gameplay;

import com.a2.pickyami.gameplay.model.GameStartModel;
import com.a2.pickyami.gameplay.model.GameStartRequest;
import org.springframework.web.util.HtmlUtils;

public class GameController {

public GameStartModel startGame(GameStartRequest request){
    return new GameStartModel(HtmlUtils.htmlEscape(request.getName()));

}
}
