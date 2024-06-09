package com.mymusic.usermanagement.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BooleanResponse {
    private boolean exists;

    public BooleanResponse(boolean b) {
        this.exists = b;
    }

    public BooleanResponse() {}

    public boolean getExists() {
        return this.exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
