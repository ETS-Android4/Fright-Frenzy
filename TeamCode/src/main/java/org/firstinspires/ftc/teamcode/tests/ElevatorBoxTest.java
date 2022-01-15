package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.Timer;

@TeleOp(group="test")
public class ElevatorBoxTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor slide = hardwareMap.get(DcMotor.class, "slide");
        Servo cargo = hardwareMap.get(Servo.class, "boxservo");
        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "sensor4");

        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        Timer timer = new Timer();
        while (opModeIsActive()) {
            double distance = distanceSensor.getDistance(DistanceUnit.INCH);

            /*
            if (gamepad2.dpad_up && distance < 4) {
                slide.setPower(0.3);
            } else if (gamepad2.dpad_up && distance >= 4) {
                slide.setPower(1.0);
            }
            */

            if(!gamepad2.dpad_up) {
                // if not raising the box turn off the timer
                timer.stop();
            } else if(gamepad2.dpad_up && !timer.isRunning() && distance <= 3.0) {
                // if the timer is not already running and the box is starting
                // to be raised from ramp position (dist < 3) then start the timer
                // to give the servo time to move to the safe position
                timer.start(1000); // 1.5 seconds
            }

            if(gamepad2.dpad_up && ((timer.isRunning() && timer.check()) || !timer.isRunning()) && distance <= 15.0) {
                // only start to move up if the servo has had time
                // to move to the safe position
                slide.setPower(1.0);
            } else if (gamepad2.dpad_down && distance >= 2.4) {
                slide.setPower(-1);
            } else {
                slide.setPower(0);
            }

            if(gamepad2.dpad_up || gamepad2.dpad_down) {
                cargo.setPosition(0.35); // safe pose
            }
            else if (gamepad2.y && distance > 2.5 && cargo.getPosition() < 0.6){
                cargo.setPosition(Servo.MIN_POSITION); // drop pos
            }
            else if(distance < 2.5) {
                cargo.setPosition(Servo.MAX_POSITION); // ramp pos
            }
            else {
                cargo.setPosition(0.35); // safe pose
            }

            telemetry.addData("distance:", distance);
            telemetry.addData("box servo:", cargo.getPosition());
            telemetry.update();
        }
    }
}
