package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class BlueDuckFirst extends LinearOpMode {
    int level; //creates integer to store where the team icon is
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this); //makes it so the RepBot (RepresentoClass but without a brake method, allows us to use a higher power) methods are available in this class
        Vision v = new Vision(this); //makes easy OpenCV methods available here
        b.startGyro(); //starts the gyro
        waitForStart(); //waits for start
        sleep(2100); //sleeps to give gyro time to calibrate
        level = b.iconConf(v); //detects the icon 10 times and sets level to the placement that appears the most
        telemetry.addData("Icon Position Number:", level); //adds level to telemetry for testing
        telemetry.addData("Icon Position", v.pos()); //adds position to telemetry for testing
        telemetry.update(); //updates telemetry
        b.goForward(-1, 10); //robot goes forward to get space between robot and wall
        b.slide(-1, 20); //robot slides to the duck carousel
        b.turnLeft(180, 0.3); //robot turns 180 degrees to align the duck wheel with duck carousel
        b.goForward(-0.2, 1); //robot goes forward to ensure the wheel is pressing against duck carousel
        b.duckSpin(); //spins the duck off the carousel
        b.goForward(0.7, 35); //robot backs away from the carousel, aligns with the alliance hub
        b.turnRight(90, 0.3); //robot turns right to face alliance hub
        b.goForwardNoGyro(0.7, 10); //robot backs against the wall to straighten out
        b.goForwardNoGyro(0.4, 5); //robot backs against the wall slower to not collide with the wall
        b.goForward(-0.7, 29); //robot goes forward to alliance hub
        b.raiseCargo(level); //robot raises the cargo and drops the cargo into the alliance hub
        b.goForward(1, 32); //robot backs up to the wall
        b.slide(1, 9); //robot slides into cargo area
    }
}
