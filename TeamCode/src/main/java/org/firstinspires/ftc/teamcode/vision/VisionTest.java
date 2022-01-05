package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class VisionTest extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        Vision v = new Vision(this);
        waitForStart();
        level = v.iconPos();
        while (opModeIsActive()) {
            telemetry.addData("Icon Position Number:", level);
            telemetry.addData("Icon Position", v.pos());
            telemetry.update();
        }
    }
}