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

    public boolean waterInfo(Boolean water) {
        return this.water = water;
    }
    public boolean lookInfo(Boolean look) {
        return this.look = look;
    }
    public boolean sunInfo(Boolean sun) {
        return this.sun = sun;
    }
    public boolean repotInfo(Boolean repot) {
        return this.repot = repot;
    }



    public void updateSun(Boolean sun) {
        this.sun = sun;
    }
    
    public void updateRepot(Boolean repot){
        this.repot = repot;
    }

    public void updateWater(Boolean water) { this.water = water; }

    public void updateLook(Boolean look) { this.look = look; }
}