package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepresentoClass;

@Autonomous
public class RaiseCargoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepresentoClass r = new RepresentoClass(this); //allows RepresentoClass methods to be used in this class
        waitForStart(); //waits for start
        r.raiseCargo(3); //calls raiseCargo() method from RepresentoClass (changed from 1, 2, 3 for testing)
    }
}
