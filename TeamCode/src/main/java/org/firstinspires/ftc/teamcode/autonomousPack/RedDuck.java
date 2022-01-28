package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class RedDuck extends LinearOpMode {
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
        b.goForward(-0.75,12); //goes forward to blocks
        b.slide(-0.75, 18); //left is negative
        b.goForward(-0.75, 1.7);
        if (level == 1) {
            b.goForward(.5, 1);
        }
        b.raiseCargo(level);
        b.goForward(0.75, 6);
        b.slide(0.75, 46);
        b.turnLeft(180,0.3);
        b.goForward(-0.2, 16);
        b.turnRight(65, 0.3);
        b.duckSpinR();
        b.turnLeft(65, 0.3);
        b.goForward(0.75, 16);
        b.slide(-0.5, 7);
    }
}
