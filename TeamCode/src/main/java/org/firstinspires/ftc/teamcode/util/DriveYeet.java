package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriveYeet extends LinearOpMode {
    private CRServo servo0;
    private Servo servo1;
    private double servSpeed1;
    private DcMotor sweep;
    private DcMotor backLeftMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontRightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        servo0 = hardwareMap.get(CRServo.class, "spinner");
        servo1 = hardwareMap.get(Servo.class, "boxservo");
        backLeftMotor = hardwareMap.get(DcMotor.class, "motor0");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "motor1");
        frontRightMotor = hardwareMap.get(DcMotor.class, "motor2");
        backRightMotor = hardwareMap.get(DcMotor.class, "motor3");
        sweep = hardwareMap.get(DcMotor.class, "sweepo");

        sweep.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()){

            double rightX_G1;
            double rightY_G1;
            double leftX_G1;
            double leftY_G1;
            double powSweep;

            rightY_G1 = -gamepad1.right_stick_y;
            rightX_G1 = -gamepad1.right_stick_x;
            leftY_G1 = gamepad1.left_stick_y;
            leftX_G1 = -gamepad1.left_stick_x;
            powSweep = gamepad2.left_stick_y;

            double frontLeft = (rightX_G1 + rightY_G1 - leftX_G1);
            double backLeft = (rightX_G1 + rightY_G1 + leftX_G1);
            double backRight = (rightX_G1 - rightY_G1 + leftX_G1);
            double frontRight = (rightX_G1 - rightY_G1 - leftX_G1);

            frontLeftMotor.setPower(frontLeft);
            backLeftMotor.setPower(backLeft);
            backRightMotor.setPower(backRight);
            frontRightMotor.setPower(frontRight);
            sweep.setPower(powSweep);

            if(gamepad1.right_bumper) {
                servo0.setPower(1);
            } else {
                servo0.setPower(0);
            }
            if (gamepad1.left_bumper) {
                servo1.setPosition(1);
            } else {
                servo1.setPosition(0);
            }
        }
    }
}