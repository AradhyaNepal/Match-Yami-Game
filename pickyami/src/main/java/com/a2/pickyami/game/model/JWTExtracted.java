package com.a2.pickyami.game.model;

import com.a2.pickyami.game.enums.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JWTExtracted {
    private  String uid;
    private Role role;
}
