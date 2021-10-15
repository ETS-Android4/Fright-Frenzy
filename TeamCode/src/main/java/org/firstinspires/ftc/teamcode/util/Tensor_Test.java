package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.TensorFlow;

public class Tensor_Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
TensorFlow T=new TensorFlow(this);
T.turnOn();
T.getRecognitions();
T.turnOff();

    }
}
