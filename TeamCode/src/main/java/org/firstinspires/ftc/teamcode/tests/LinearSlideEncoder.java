package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.Button;
import org.firstinspires.ftc.teamcode.util.Timer;

@TeleOp
public class LinearSlideEncoder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor slide = hardwareMap.get(DcMotor.class, "slide");
        Servo cargo = hardwareMap.get(Servo.class, "boxservo");
        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "sensor4");

        DigitalChannel tummyTime = hardwareMap.get(DigitalChannel.class, "tummy time");
        Button slideDown = new Button(tummyTime);

        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        Timer timer = new Timer();
        boolean downLatch = slideDown.isPressed();

        while (opModeIsActive()) {
            double distance = distanceSensor.getDistance(DistanceUnit.INCH);

            // Reset the slide motor encoder when the touch sensor is pressed
            if(slideDown.isPressed()) {
                slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

            if(!gamepad2.dpad_up) {
                // if not raising the box turn off the timer
                timer.stop();
            } else if(gamepad2.dpad_up && !timer.isRunning() && downLatch) {
                // if the timer is not already running and the box is starting
                // to be raised from ramp position (downLatch = true) then start the timer
                // to give the servo time to move to the safe position
                timer.start(1000); // 1 second
            }

            if(gamepad2.dpad_up && ((timer.isRunning() && timer.check()) || !timer.isRunning()) && distance <= 15.0) {
                // only start to move up if the servo has had time
                // to move to the safe position
                slide.setPower(1.0);
            } else if (gamepad2.dpad_down && !downLatch) {
                slide.setPower(-1);
            } else {
                slide.setPower(0);
            }

            if(gamepad2.dpad_up || gamepad2.dpad_down) {
                cargo.setPosition(0.28); // safe pose
            }
            else if (gamepad2.y && !downLatch && cargo.getPosition() < 0.6){
                cargo.setPosition(Servo.MIN_POSITION); // drop pos
            }
            else if(downLatch) {
                cargo.setPosition(Servo.MAX_POSITION); // ramp pos
            }
            else {
                cargo.setPosition(0.28); // safe pose
            }

            // The downLatch is set when the button is first pressed and
            // stays set until dpad_up is pressed. This prevents the box server
            // from twitching if the slide is down but the button is not always
            // pressed (like when driving over bumps)
            if(gamepad2.dpad_up) {
                downLatch = false;
            } else if(slideDown.isPressed()) {
                downLatch = true;
            }

            telemetry.addData("distance:", distance);
            telemetry.addData("box servo:", cargo.getPosition());
            telemetry.addData("encoder:", slide.getCurrentPosition());
            telemetry.addData("tummy:", tummyTime.getState());
            telemetry.update();
        }
    }


}
