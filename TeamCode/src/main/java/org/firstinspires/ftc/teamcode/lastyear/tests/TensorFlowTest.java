package org.firstinspires.ftc.teamcode.lastyear.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.lastyear.RepresentoBotMVP;
import org.firstinspires.ftc.teamcode.lastyear.UltimateVuforia;
import org.firstinspires.ftc.teamcode.lastyear.VuforiaNavigator;

@Disabled
@Autonomous
public class TensorFlowTest extends LinearOpMode {
    RepresentoBotMVP bot;
    VuforiaNavigator vuNav;
    UltimateVuforia vu;
    int rings;
    int zeroRing;
    int oneRing;
    int fourRing;

    @Override
    public void runOpMode() throws InterruptedException {
        vu = new UltimateVuforia(this);
        vu.yesVuforia();

        waitForStart();

        //while (opModeIsActive()) {
        //    rings = vu.tensorflow();
            //telemetry.addData("rings", rings);
            //telemetry.update();
            for (int i = 0; i < 50; i++) {
                rings = vu.tensorflow();
                //telemetry.addData("rings", rings);
                //telemetry.update();
                if (rings == 0) {
                    zeroRing++;
                } else if (rings == 1) {
                    oneRing++;
                } else if (rings == 4) {
                    fourRing++;
                }
                //telemetry.addData("fourRing: ", fourRing);
                //telemetry.addData("oneRing: ", oneRing);
                //telemetry.addData("zeroRing: ", zeroRing);
                //telemetry.update();
            }
            telemetry.addData("fourRing: ", fourRing);
            telemetry.addData("oneRing: ", oneRing);
            telemetry.addData("zeroRing: ", zeroRing);
            telemetry.update();
        //}
        vu.noVuforia();

        while(opModeIsActive()) {
            idle();
        }
    }
}
