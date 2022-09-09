package com.plantity.server.domain.plantlog;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Log {

    private boolean water;
    private boolean look;
    private boolean sun;
    private boolean repot;

    protected Log() {}

    public Log(boolean water, boolean look, boolean sun, boolean repot) {
        this.water = water;
        this.look = look;
        this.sun = sun;
        this.repot = repot;
    }

    public void updateSun(Boolean sun) {
        this.sun = sun;
    }
}