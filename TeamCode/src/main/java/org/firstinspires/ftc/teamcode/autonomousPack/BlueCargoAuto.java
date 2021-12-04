package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepresentoClass;

@Autonomous
public class BlueCargoAuto extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        RepresentoClass b = new RepresentoClass(this);
        //identify where the icon is, what level the cargo goes on
        b.goForward(0.5, 24); //goes forward 24 inches
        b.slide(-0.5, 24); //slides 24 inches to the left
        b.raiseCargo(1/*what level is the icon at?*/); //raises the linear slide and places cargo
        b.turnLeft(90, 0.2); //turns 90 degrees to face cargo
        b.goForward(0.5, 48); //goes forward 48 inches
        b.stopMotor(); //ensures all motors are not moving
    }
}
