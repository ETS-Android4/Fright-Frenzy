package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;

@Autonomous
public class RedDuckWait extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        b.startGyro();
        waitForStart();
        sleep(15000);
        b.goForward(-0.75,12); //goes forward to blocks
        b.slide(-0.75, 24); //left is negative
        b.goForward(-0.75, 8);
        b.raiseCargo(2);
        b.goForward(.5,2);
        b.turnLeft(90,0.3);
        b.goForward(-0.5, 72);
        b.slide(0.5, 15);
        b.duckSpinR();
        b.slide(-0.5, 17);
    }
}
