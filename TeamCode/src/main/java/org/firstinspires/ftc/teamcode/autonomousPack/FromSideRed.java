package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;

@Autonomous
public class FromSideRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        b.startGyro();
        waitForStart();
        b.goForward(-0.75,30); //goes forward to block
        b.turnLeft(90, 0.3);
        b.goForward(-.5, 3);
        b.raiseCargo(2);
        b.goForward(.5, 3);
        b.slide(.75, 15);
        b.goForward(1, 40);

    }
}
