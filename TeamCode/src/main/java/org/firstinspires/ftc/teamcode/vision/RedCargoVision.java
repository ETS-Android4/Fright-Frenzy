package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Disabled
@Autonomous
public class RedCargoVision extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        level = v.iconPos();
        b.goForward(-0.75,25); //goes forward to align with hub
        b.turnRight(90, 0.3); //robot turns to face the hub
        b.goForward(-0.75, 6);
        b.raiseCargo(level);
        b.goForward(0.75, 10);
        b.turnLeft(90, 0.3);
        b.goForward(-0.75, 50);
        b.slide(-0.75, 20);
        b.goForward(-0.75, 14);
    }
}