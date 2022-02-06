package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Button {
    DigitalChannel dc;
    boolean clicked = false;

    static final int DOWN = 0;
    static final int UP = 1;
    int lastState;

    public Button(DigitalChannel dc) {
        this.dc = dc;
        dc.setMode(DigitalChannel.Mode.INPUT);

        if(dc.getState()) {
            lastState = UP;
        } else {
            lastState = DOWN;
        }
    }

    public boolean isPressed() {
        return !dc.getState();
    }

    public boolean clicked() {
        boolean val = clicked;
        if(clicked) {
            clicked = false;
        }

        return val;
    }

    public void update() {
        if(dc.getState()) {
            lastState = UP;
            clicked = false;
        } else {
            if(lastState == UP) {
                clicked = true;
            }
            lastState = DOWN;
        }
    }
}
