package org.firstinspires.ftc.teamcode.util;

public class Timer {
    long end;
    boolean exp;
    boolean running;

    public Timer() {
        super();

        end = 0;
        exp = true;
        running = false;
    }

    public void start(long duration) {
        end = System.currentTimeMillis() + duration;
        exp = false;
        running = true;
    }

    public boolean check() {
        if(!exp) {
            exp = System.currentTimeMillis() >= end;
        }

        return exp;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}