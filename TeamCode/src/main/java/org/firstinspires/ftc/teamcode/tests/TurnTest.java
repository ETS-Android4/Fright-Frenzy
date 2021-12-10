package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RepresentoClass;

@Autonomous(group="test")
public class TurnTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RepresentoClass bot = new RepresentoClass(this);

        bot.turnRight(180, 0.5);
    }
}
