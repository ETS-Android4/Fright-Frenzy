package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

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
        b.goForward(-0.75,12); //goes forward to blocks
        b.slide(0.75, 24); //left is negative
        b.goForward(-0.75, 5.6);
        b.raiseCargo(level);
        b.goForward(0.3, 4);
        b.turnRight(90,0.3);
        b.goForward(-1, 87);
    }
}