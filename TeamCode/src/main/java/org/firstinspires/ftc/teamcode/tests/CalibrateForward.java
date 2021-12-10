package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(group="test")
public class CalibrateForward extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "motor0");
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "motor1");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "motor2");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "motor3");
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double rightY_G1 = 0.5;
        double rightX_G1 = 0.0;
        double leftX_G1 = 0.0;
        frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
        backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
        backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
        frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));

        long start = System.currentTimeMillis();
        while (opModeIsActive() && System.currentTimeMillis() - start < 3000) {
            idle();
        }

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontRightMotor.setPower(0);

        telemetry.addData("tics:", frontRightMotor.getCurrentPosition());
        telemetry.update();
        while (opModeIsActive()) {
            idle();
        }
    }
}
