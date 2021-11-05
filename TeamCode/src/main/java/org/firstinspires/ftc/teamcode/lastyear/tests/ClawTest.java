package org.firstinspires.ftc.teamcode.lastyear.tests;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp
public class ClawTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo claw = hardwareMap.get(Servo.class, "claw");

        waitForStart();

        //claw.setPosition(-1.0);
        int lastDir = 0;
        while (opModeIsActive()) {

            if (gamepad1.dpad_left) {
                claw.setPosition(-1.0);
            } else {
                claw.setPosition(1.0);
            }


        }

        //claw.setPower(0);
    }
}
