package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class SweepTestClass extends LinearOpMode {
    private DcMotor sweep;
    @Override
    public void runOpMode() throws InterruptedException {
        sweep = hardwareMap.get(DcMotor.class, "sweepo");
        sweep.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()){
            double tgtPower;
            tgtPower = gamepad1.left_stick_x;
            sweep.setPower(tgtPower);
        }
        sweep.setPower(0);
    }
}
