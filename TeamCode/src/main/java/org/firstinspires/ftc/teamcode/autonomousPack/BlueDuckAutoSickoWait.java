package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Disabled
@Autonomous
public class BlueDuckAutoSickoWait extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        sleep(14000);
        level = v.iconPos();
        telemetry.addData("Icon Position Number:", level);
        telemetry.addData("Icon Position", v.pos());
        telemetry.update();
        b.goForward(-0.75,14.5); //goes forward to blocks
        b.slide(0.75, 24); //left is negative
        b.goForward(-0.75, 10);
        b.raiseCargo(level);
        b.goForward(0.75, 4);
        b.slide(-0.75, 42);
        b.turnLeft(180,0.2);
        b.goForward(-0.2, 16.5);
        b.duckSpin();
        b.goForward(0.75, 19);
        b.slide(0.5, 3);
    }
}
