package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class SweepTestClass extends LinearOpMode {
    private DcMotor sweep; //initializes motor
    @Override
    public void runOpMode() throws InterruptedException {
        sweep = hardwareMap.get(DcMotor.class, "sweepo"); //connects the motor to the hardware map
        sweep.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //gives sweep the brake behavior to reduce drift

        waitForStart(); //waits for start
        while (opModeIsActive()){
            double tgtPower; //initalizes double for power
            tgtPower = gamepad1.left_stick_x; //connects power to the position of the gamepad1 left stick
            sweep.setPower(tgtPower); //sets sweep to position of left sick
        }
        sweep.setPower(0); //stops sweep power to make sure motor is off when program stops
    }
}
