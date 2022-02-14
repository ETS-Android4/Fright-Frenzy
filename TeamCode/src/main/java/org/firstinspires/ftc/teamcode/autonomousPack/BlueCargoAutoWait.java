package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepBot;
import org.firstinspires.ftc.teamcode.Vision;

@Autonomous
public class BlueCargoAutoWait extends LinearOpMode {
    int level;
    @Override
    public void runOpMode() throws InterruptedException {
        RepBot b = new RepBot(this); //makes it so the RepBot (RepresentoClass but without a brake method, allows us to use a higher power) methods are available in this class
        Vision v = new Vision(this); //makes it so the vision/easy OpenCV methods are available in this class
        b.startGyro(); //starts the gyro
        waitForStart(); //waits for start
        sleep(2000); //sleeps to give gyro time to calibrate
        sleep(8100); //sleeps to give time to other robot
        level = b.iconConf(v); //detects the icon 10 times and sets level to the placement that appears the most
        telemetry.addData("Icon Position Number:", level); //adds level to telemetry for testing
        telemetry.addData("Icon Position", v.pos()); //adds position to telemetry for testing
        telemetry.update(); //updates telemetry
        b.goForward(-0.75,12.5); //goes forward to icon
        b.slide(-0.75, 23); //slides to front of alliance hub
        b.goForward(-0.75, 8); //goes forward to be close to the alliance hub
        b.turnLeft(.25, 0.3); //robot turns left to straighten out
        b.raiseCargo(level); //robot raises the cargo and drops the cargo into the alliance hub
        b.goForward(0.3, 2); //robot backs away from alliance hub
        b.turnLeft(85,0.3); //robot turns left to face warehouse
        b.slide(0.5, 25.5); //robot slides back against wall
        b.goForward(-1, 78); //robot drives into warehouse
    }
}
