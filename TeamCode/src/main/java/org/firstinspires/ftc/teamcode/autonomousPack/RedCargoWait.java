package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class RedCargoWait extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        sleep(10000);
        level = v.iconPos();
        telemetry.addData("Icon Position Number:", level);
        telemetry.addData("Icon Position", v.pos());
        telemetry.update();
        b.goForward(-0.75,15); //goes forward to blocks
        b.slide(0.75, 27); //left is negative
        b.goForward(-0.75, 6);
        if (level == 1) {
            b.goForward(.5, 1);
        }
        b.raiseCargo(level);
        b.goForward(0.3, 4);
        b.turnRight(85,0.3);
        b.slide(-0.5, 22);
        b.goForward(-1, 87);
    }
}