package org.firstinspires.ftc.teamcode.lastyear.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.lastyear.RepresentoBotMVP;

@Autonomous
public class TestCaseSimple extends LinearOpMode {
    RepresentoBotMVP bot;

    @Override
    public void runOpMode() throws InterruptedException {
        bot=new RepresentoBotMVP(this);
        waitForStart();
        bot.startGyro();

        bot.dropSweep();
        bot.goForward(0.5, 53);
        bot.shootRings(3);
        bot.goForward(0.5, 18);
    }
}
