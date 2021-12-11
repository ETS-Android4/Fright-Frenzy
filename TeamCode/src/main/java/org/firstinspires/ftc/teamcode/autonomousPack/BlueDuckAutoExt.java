package org.firstinspires.ftc.teamcode.autonomousPack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepresentoClass;

@Autonomous
@Disabled
public class BlueDuckAutoExt extends LinearOpMode {
   int level = 2;
    @Override
    public void runOpMode() throws InterruptedException {
        RepresentoClass b = new RepresentoClass(this);
//        TensorFlow t = new TensorFlow(this);
//        t.turnOn();
        b.startGyro();
        waitForStart();
        b.goForward(-0.4, 15); //goes forward to blocks
//        if(t.isDuck()) { //sees if duck is in middle spot
//            level = 2;
//        }
//        b.turnLeft(20, .3);
//        if(t.isDuck()) { //sees if duck is in left spot
//            level = 1;
//        }
//        b.turnRight(40, .3);
//        if(t.isDuck()) { //sees if duck is in right spot
//            level = 3;
//        }
//        if(!t.isDuck()) { //sees if duck is in the middle
//            level = 2;
//        }
        b.slide(0.5, 24); //left is negative
        b.goForward(-0.5, 7);
        b.raiseCargo(level);
        b.slide(-0.5, 40);
        b.turnLeft(180,0.3);
        b.goForward(-0.5, 20);
        b.duckSpin();
        b.goForward(0.5, 23);
        b.slide(0.5, 3);
    }
}
