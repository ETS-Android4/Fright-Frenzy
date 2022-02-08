package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class BlueDuckFirst extends LinearOpMode {
    int level;
    int one;
    int two;
    int three;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        sleep(2000);
        level = b.iconConf(v);
        telemetry.addData("Icon Position Number:", level);
        telemetry.addData("Icon Position", v.pos());
        telemetry.update();
        b.goForward(-1, 10);
        b.slide(-1, 20);
        b.turnLeft(180, 0.3);
        b.goForward(-0.2, .5);
        b.duckSpin();
        b.goForward(0.7, 35);
        b.turnRight(90, 0.3);
        b.goForwardNoGyro(0.7, 10);
        b.goForwardNoGyro(0.4, 5);
        b.goForward(-0.7, 29);
        b.raiseCargo(level);
        b.goForward(1, 32);
        b.slide(1, 9);
    }
}
