package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class TensorFlow {

    private OpMode op;
    
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private static final String VUFORIA_KEY = "AcUwNff/////AAABmSDjtLt8eEYos7+P16Q5uMpfai9UDhOe3GPRF9oLweSCr+ydsB1z1O07EAL8u6QzfDIp2DKNqqxD7AzaTUEjHxLeL/W86upMAQ/yj+i0xCTmb46d6WyaCEK//pGA1eXtYAUzXizSQiLvp3ljz1d27Lv8xsJb+RQqFRW+IgJ/k+McoNBZF6v9Y+huXNSZhUtfqklrr4IhP64h9DGxrAst7swmUES4fsMGXRAF+p2sJlgv9cKtJpdKo6e0xSbN5Oe4+0nGKhvwIO7qGW4tQLM/1h1VlJoQWhT8N42Ccho4cu83IcFN1WIqSpz7KsGEuqEonrf2S4tthqMJ+FE5f4cYY0jgfkxaPf4y0GjKR0cggrXS";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public TensorFlow (LinearOpMode op){
        this.op=op;
    }

    public void turnOn() {
        // TODO: turn on tensor flow
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = op.hardwareMap.get(WebcamName.class, "Webcam 1");
        
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        int tfodMonitorViewId = op.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", op.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }
    }

    public boolean isDuck () {
        boolean OTF = false;
        if (tfod != null) {
            op.telemetry.addData("TFOD", "NOT NULL");
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                op.telemetry.addData("RECLIST: ", "NOT NULL");
                //op.telemetry.addData("# Object Detected", updatedRecognitions.size());
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    //if (recognition.getBottom()>= 800) {
                    //op.telemetry.addData(String.format("height", i), recognition.getHeight());
                    //op.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    //op.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                    //        recognition.getLeft(), recognition.getTop());
                    //op.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                    //        recognition.getRight(), recognition.getBottom());
                    if (recognition.getLabel().equals("Duck")) {
                        OTF = true;
                    } else {
                        OTF = false;
                    }

                    op.telemetry.addData("Duck: ", OTF);
                    op.telemetry.update();
                }
                op.telemetry.update();
            } else {
                op.telemetry.addData("RECLIST", "NULL");
            }
        } else {
            op.telemetry.addData("TFOD", "NULL");
        }
        return OTF;
    }

    public void turnOff() {
        // TODO: turn off tensor flow
        tfod.shutdown();
        tfod.deactivate();
    }

    public List<Recognition> getRecognitions() {
        // TODO: get the list of Recognitions from tfod
        List<Recognition> updatedRecognitions = null;
        if (tfod != null) {
            updatedRecognitions = tfod.getUpdatedRecognitions();
        }
        return updatedRecognitions;
    }
}
