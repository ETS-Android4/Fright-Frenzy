package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp
public class ShowbotCode extends LinearOpMode {
    private DcMotor left;
    private DcMotor right;
    private Servo tail;

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        tail = hardwareMap.get(Servo.class, "tail");

        waitForStart();
        while (opModeIsActive()) {
            double powLeft = gamepad1.left_stick_y;
            double powRight = -gamepad1.right_stick_y;

            left.setPower(powLeft);
            right.setPower(powRight);

            if (gamepad1.a) {
                tail.setPosition(0);
            } else if (gamepad1.b) {
                tail.setPosition(0.3);
            } else if (gamepad1.x) {
                tail.setPosition(0.6);
            } else if (gamepad1.y) {
                tail.setPosition(1);
            }
        }
    }
}
