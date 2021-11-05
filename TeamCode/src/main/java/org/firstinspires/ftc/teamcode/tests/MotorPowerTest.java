package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
class MotorPowerTest extends LinearOpMode {
    private DcMotor linear;

    @Override
    public void runOpMode(){
        linear = hardwareMap.get(DcMotor.class, "motor0");

        linear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {

            double tgt;

            tgt = gamepad1.right_stick_x;

            linear.setPower(tgt);

            telemetry.addData("Power: ", tgt);
        }
    }
}
