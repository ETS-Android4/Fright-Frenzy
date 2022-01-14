package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class BlueCargoVision extends LinearOpMode {
    int level; //creates integer to store where the team icon is
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this); //makes it so the RepBot (RepresentoClass but without a brake method, allows us to use a higher power) methods are available in this class
        Vision v = new Vision(this); //makes it so the vision/easy OpenCV methods are available in this class
        b.startGyro(); //starts the gyro
        waitForStart(); //waits for the start
        level = v.iconPos(); //identifies the position of the team icon on the field and stores it in
        b.goForward(-0.75,25); //moves forward to align horizontally with the alliance hub
        b.turnLeft(90, 0.3); //the robot turns to face the alliance hub
        b.goForward(-0.75, 6); //moves forward to be close enough to drop cargo
        b.raiseCargo(level); //raises and drops cargo to the level recorded from v.iconPos()
        b.goForward(0.75, 10); //moves backwards to warehouse barrier
        b.turnRight(90, 0.3); //turn to face starting wall
        b.goForward(-0.75, 50); //goes forward to starting wall
        b.slide(0.75, 20); //slides into warehouse using the gap
        b.goForward(-0.75, 14); //moves out of the way of incoming robots, depending on plan of other team
    }
}
