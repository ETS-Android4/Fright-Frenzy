package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class BlueCargoVision extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        level = v.iconPos();
        b.goForward(-0.75, 12); //goes forward to blocks
        b.slide(-0.75, 22); //left is negative
        b.goForward(-0.75, 8);
        b.raiseCargo(level);
        b.goForward(0.3, 2);
        b.turnLeft(90, 0.3);
        b.goForward(-1, 78);
    }
}
