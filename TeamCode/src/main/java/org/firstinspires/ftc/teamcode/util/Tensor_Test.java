package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.TensorFlow;

import java.util.List;

@Disabled
@Autonomous
public class Tensor_Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TensorFlow T=new TensorFlow(this);
        T.turnOn();
    waitForStart();
    while (opModeIsActive()) {
        List<Recognition> getRecs = T.getRecognitions();
        if (getRecs != null) {
            int i = 0;
            float conf;
            float bestScore = -1;
            Recognition bestRec = null;
            for (Recognition recog: getRecs) {
                i = getRecs.size();
                telemetry.addData("Recognitions: ", recog.getLabel());
                conf = recog.getConfidence();
                if (conf > bestScore && recog.getLabel().equals("Duck")) {
                    bestScore = recog.getConfidence();
                    bestRec = recog;
                }
            }
            telemetry.addData(String.format("label (%d)", i), bestRec.getLabel());
            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                    bestRec.getLeft(), bestRec.getTop());
            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                    bestRec.getRight(), bestRec.getBottom());
            telemetry.update();
        }
        }
        T.turnOff();
    }
}
