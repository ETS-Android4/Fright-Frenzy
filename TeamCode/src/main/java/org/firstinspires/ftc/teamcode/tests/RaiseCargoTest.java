package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepresentoClass;

@Autonomous
public class RaiseCargoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepresentoClass r = new RepresentoClass(this);
        waitForStart();
        r.raiseCargo(3);
    }
}
