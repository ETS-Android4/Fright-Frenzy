package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class OutputDistance extends LinearOpMode {
    DistanceSensor distanceSensor;
    double dist;

    @Override
    public void runOpMode() throws InterruptedException {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "sensor4");
        waitForStart();
        while (opModeIsActive()) {
            dist = distanceSensor.getDistance(DistanceUnit.INCH);
            telemetry.addData("Distance: ", dist);
            telemetry.update();
        }
    }
}
