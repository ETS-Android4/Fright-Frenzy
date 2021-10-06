package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriveYeet extends LinearOpMode {
    private Servo servo0;
    private Servo servo1;
    private double servSpeed1;

    @Override
    public void runOpMode() throws InterruptedException {
        servo0 = hardwareMap.get(Servo.class, "servo0");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.right_bumper) {
                servo0.setPosition(1);
            } else {
                servo0.setPosition(0);
            }
            if (gamepad1.left_bumper) {
                servo1.setPosition(1);
            } else {
                servo1.setPosition(0);
            }
        }
    }
}