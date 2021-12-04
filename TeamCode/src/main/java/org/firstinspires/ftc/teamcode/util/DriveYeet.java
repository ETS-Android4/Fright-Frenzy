package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriveYeet extends LinearOpMode {
    private double servSpeed1;
    private DcMotor backLeftMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontRightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        // hardware for:
        // --sweeper motor
        // --box servo
        // --linear slide motor
        // --spinner servo

        // type name = value
        DcMotor sweepomode = hardwareMap.get(DcMotor.class, "sweepo");
        DcMotor slipnslide = hardwareMap.get(DcMotor.class, "slide");
        Servo boxservo = hardwareMap.get(Servo.class, "boxservo");
        CRServo beyblade = hardwareMap.get(CRServo.class, "spinner");
        backLeftMotor = hardwareMap.get(DcMotor.class, "motor0");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "motor1");
        frontRightMotor = hardwareMap.get(DcMotor.class, "motor2");
        backRightMotor = hardwareMap.get(DcMotor.class, "motor3");

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

            rightY_G1 = -gamepad1.right_stick_y;
            rightX_G1 = -gamepad1.right_stick_x;
            leftY_G1 = gamepad1.left_stick_y;
            leftX_G1 = -gamepad1.left_stick_x;

            double frontLeft = (rightX_G1 + rightY_G1 - leftX_G1);
            double backLeft = (rightX_G1 + rightY_G1 + leftX_G1);
            double backRight = (rightX_G1 - rightY_G1 + leftX_G1);
            double frontRight = (rightX_G1 - rightY_G1 - leftX_G1);

            frontLeftMotor.setPower(frontLeft);
            backLeftMotor.setPower(backLeft);
            backRightMotor.setPower(backRight);
            frontRightMotor.setPower(frontRight);

            // map controller for:
            // --sweeper motor
            // --box servo
            // --linear slide motor
            // --spinner servo

            sweepomode.setPower(-gamepad2.right_trigger);
            slipnslide.setPower(gamepad2.left_stick_y);

            if (gamepad2.right_bumper){
                beyblade.setPower(1.0);
            } else if (gamepad2.left_bumper) {
                beyblade.setPower(-1.);
            } else {
                beyblade.setPower(0.0);
            }

            if (gamepad2.dpad_up){
                boxservo.setPosition(0.0);
            }
            else if (gamepad2.dpad_right){
                boxservo.setPosition(0.5);
            }
            else{
                boxservo.setPosition(1.0);

            }
            // don't allow the linear slide to go up until basket is vertcal

        }
    }
}