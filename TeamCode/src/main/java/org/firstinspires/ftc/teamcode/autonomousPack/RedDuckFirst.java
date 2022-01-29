package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class RedDuckFirst extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        sleep(2000);
        level = v.iconPos();
        telemetry.addData("Icon Position Number:", level);
        telemetry.addData("Icon Position", v.pos());
        telemetry.update();
        b.slide(0.7, 20);
        b.slide(0.5, 5);
        b.turnLeft(50, 0.3);
        b.duckSpinR();
        b.turnRight(50, 0.3);
        b.goForward(-0.7, 40);
        b.turnRight(90, 0.3);
        b.goForwardNoGyro(0.7, 5);
        b.goForward(-0.7, 28);
        b.raiseCargo(level);
        b.goForward(0.7, 32);
        b.slide(-0.7, 13);
    }
}
