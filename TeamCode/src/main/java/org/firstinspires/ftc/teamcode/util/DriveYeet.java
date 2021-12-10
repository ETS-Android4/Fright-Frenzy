package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class DriveYeet extends LinearOpMode {
    private double servSpeed1;
    private DcMotor backLeftMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontRightMotor;
    private DcMotor spinner;
    private DcMotor sweepo;
    private DcMotor slide;
    private Servo cargo;
    private Servo iconServoR;
    private Servo iconServoL;
    private DistanceSensor distanceSensor;


    @Override
    public void runOpMode() throws InterruptedException {
        // hardware for:
        // --sweeper motor
        // --box servo
        // --linear slide motor
        // --spinner servo

        // type name = value
        sweepo = hardwareMap.get(DcMotor.class, "sweepo");
        slide = hardwareMap.get(DcMotor.class, "slide");
        cargo = hardwareMap.get(Servo.class, "boxservo");
        iconServoR = hardwareMap.get(Servo.class, "servo1");
        iconServoL = hardwareMap.get(Servo.class, "servo0");
        spinner = hardwareMap.get(DcMotor.class, "spinner");
        backLeftMotor = hardwareMap.get(DcMotor.class, "motor0");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "motor1");
        frontRightMotor = hardwareMap.get(DcMotor.class, "motor2");
        backRightMotor = hardwareMap.get(DcMotor.class, "motor3");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "sensor4");


        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

            sweepo.setPower(-gamepad2.right_trigger);

            if (gamepad2.dpad_up) {
                slide.setPower(1);
            } else if (gamepad2.dpad_down) {
                slide.setPower(-1);
            } else {
                slide.setPower(0);
            }

            if (distanceSensor.getDistance(DistanceUnit.INCH) > 4 && distanceSensor.getDistance(DistanceUnit.INCH) < 6 && gamepad2.left_stick_y > 0) {
                cargo.setPosition(0.5);
            }

            if (gamepad2.right_bumper){
                spinner.setPower(1.0);
            } else if (gamepad2.left_bumper) {
                spinner.setPower(-1.0);
            } else {
                spinner.setPower(0.0);
            }

            if (gamepad2.y){
                cargo.setPosition(Servo.MIN_POSITION);
            }
            else if (gamepad2.b){
                cargo.setPosition(0.5);
            }
            else{
                cargo.setPosition(Servo.MAX_POSITION);
            }
            // don't allow the linear slide to go up until basket is vertcal

            if (gamepad1.a) {
                iconServoL.setPosition(1);
                iconServoR.setPosition(0);
            } else if (gamepad1.b) {
                iconServoL.setPosition(1);
                iconServoR.setPosition(1);
            } else if (gamepad1.y) {
                iconServoL.setPosition(0);
                iconServoR.setPosition(0);
            }

        }
    }
}