package org.firstinspires.ftc.teamcode.lastyear.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.lastyear.RepresentoBotMVP;
import org.firstinspires.ftc.teamcode.lastyear.UltimateVuforia;
import org.firstinspires.ftc.teamcode.lastyear.VuforiaNavigator;

@Autonomous(group = "Tests")
public class NavX36Y36Test extends LinearOpMode {
    RepresentoBotMVP bot;
    UltimateVuforia vu;
    VuforiaNavigator vuNav;

    @Override
    public void runOpMode() throws InterruptedException {
        bot=new RepresentoBotMVP(this);
        vu = new UltimateVuforia(this);
        vuNav = new VuforiaNavigator(this, bot, vu);
        bot.startGyro();
        vu.yesVuforia();

        waitForStart();

        sleep(2000);
     vuNav.navigate(36, 36, 0);
        while(opModeIsActive()) {
            idle();
        }
        vu.noVuforia();
    }
}
