package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;

@Autonomous
public class BlueDuckAutoSicko extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        b.startGyro();
        waitForStart();
        b.goForward(-0.75,12); //goes forward to blocks
        b.slide(0.75, 22); //left is negative
        b.goForward(-0.75, 4.3);
        b.raiseCargo(2);
        b.slide(-0.75, 40);
        b.turnLeft(180,0.3);
        b.goForward(-0.5, 18);
        b.duckSpin();
        b.goForward(0.75, 22);
        b.slide(0.5, 10);
    }
}
