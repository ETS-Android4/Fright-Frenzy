package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Disabled
@Autonomous
public class BlueDuckVision extends LinearOpMode {
    int level; //creates integer to store where the team icon is
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this); //makes it so the RepBot (RepresentoClass but without a brake method, allows us to use a higher power) methods are available in this class
        Vision v = new Vision(this); //makes easy OpenCV methods available here
        b.startGyro(); //starts the gyro
        waitForStart(); //waits for start
        level = v.iconPos(); //identifies the position of the team icon on the field and stores it in
        b.goForward(-0.75,30); //moves forward to align horizontally with the alliance hub
        b.turnLeft(90, 0.3); //the robot turns to face the alliance hub
        b.goForward(-0.75, 6); //moves forward to be close enough to drop cargo
        b.raiseCargo(level); //raises and drops cargo to the level recorded from v.iconPos()
        b.goForward(0.75, 45); //moves backwards to wall
        b.turnRight(90, 0.3); //turns to face the duck spinner
        b.goForward(0.75, 50); //goes forward to the duck spinner
        b.duckSpin(); //spins the duck spinner for 4 seconds
        b.goForward(-0.75, 40); //moves backwards to park in the depot
    }
}
