package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepresentoClass;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class BlueDuckVision extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepresentoClass b = new RepresentoClass(this);
        Vision v = new Vision(this);
        b.startGyro();
        waitForStart();
        level = v.iconPos();
        b.goForward(-0.75,12); //goes forward to blocks
        b.slide(0.75, 22); //left is negative
        b.goForward(-0.75, 4.3);
        b.raiseCargo(level);
        b.slide(-0.75, 40);
        b.turnLeft(180,0.3);
        b.goForward(-0.5, 18);
        b.duckSpin();
        b.goForward(0.75, 22);
        b.slide(0.5, 10);
    }
}
