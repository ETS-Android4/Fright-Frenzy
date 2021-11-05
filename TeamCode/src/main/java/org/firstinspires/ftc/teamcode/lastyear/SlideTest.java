package org.firstinspires.ftc.teamcode.lastyear;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous
public class SlideTest extends LinearOpMode {
    RepresentoBotMVP bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot=new RepresentoBotMVP(this);
        waitForStart();
        bot.startGyro();
        //see how many rings there are
        bot.slide(0.5, 23);
    }
}
