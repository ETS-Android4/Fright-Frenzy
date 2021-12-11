package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;

@Autonomous
public class RedCargo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        b.startGyro();
        waitForStart();
        b.goForward(-0.75,12); //goes forward to blocks
        b.slide(0.75, 24); //left is negative
        b.goForward(-0.75, 5.6);
        b.raiseCargo(2);
        b.goForward(0.3, 4);
        b.turnRight(90,0.3);
        b.goForward(-1, 87);
    }
}