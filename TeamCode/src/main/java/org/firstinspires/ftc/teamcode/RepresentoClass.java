
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.lastyear.Gyro;
import org.firstinspires.ftc.teamcode.lastyear.Gyro2;
import org.firstinspires.ftc.teamcode.lastyear.Timer;

public class RepresentoClass {

    //declares variables for gyro methods
    private static final double MAX_ANGLE = 5.0;
    private static final double ANGLE_ADJ_PERC = 0.2;
    private static final double ANGLE_ADJ_SPEED = 0.2;

    //declares motor variables
    private DcMotor backLeftMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private DcMotor spinner;
    private DcMotor sweeper;
    private DcMotor linearSlideMotor;

    //declares various sensors and OpModes
    private Gyro gyro;
    private LinearOpMode opMode;
    private DistanceSensor distanceSensor;
    private NormalizedColorSensor sensorColor;
    ModernRoboticsI2cRangeSensor rangeSensor;
    private Gyro2 miniGyro;

    //declares Servos
    private Servo cargoServo;

    //private java.util.Timer timeKeeper = new java.util.Timer();
    private Timer timer4;

    public RepresentoClass (LinearOpMode om) {
        //sets opMode
        this.opMode = om;

        //sets each component to its place on the hardware map
        backLeftMotor = opMode.hardwareMap.get(DcMotor.class, "motor0");
        frontLeftMotor = opMode.hardwareMap.get(DcMotor.class, "motor1");
        frontRightMotor = opMode.hardwareMap.get(DcMotor.class, "motor2");
        backRightMotor = opMode.hardwareMap.get(DcMotor.class, "motor3");
        linearSlideMotor = opMode.hardwareMap.get(DcMotor.class, "slide");
        cargoServo = opMode.hardwareMap.get(Servo.class, "boxservo");
        BNO055IMU imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        spinner = opMode.hardwareMap.get(DcMotor.class, "spinner");
        gyro = new Gyro(imu, opMode);
        distanceSensor = opMode.hardwareMap.get(DistanceSensor.class, "sensor4");
        sweeper = opMode.hardwareMap.get(DcMotor.class, "sweepo");

        //stops movement of robot quickly.
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sweeper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //methods that starts the gyros
    public void startGyro(){
        gyro.StartGyro();
        miniGyro = gyro.getMiniGyro();
    }

    //method that ramps up the power, as to not jerk the robot at start
    public double ramp(double power, long startTime) {
        // ramp for 0.75 seconds
        long t = System.currentTimeMillis() - startTime;
        if (t >= 750) {
            return power;
        } else {
            return power / 750 * t;
        }
    }

    //method to turn right using gyro
    public void turnRight(double degrees, double power) {
        //
        gyro.resetWithDirection(Gyro.RIGHT);
        // tells the gyro we are turning right

        // start the motors turning right
        double rightY_G1 = 0.0;
        double rightX_G1 = -1.0 * power;
        double leftX_G1 = 0.0;

        frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
        backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
        backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
        frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
        // connects the motors to the correct variables

        // loop until the robot turns :) degrees
        double d = -1 * degrees;
        while (opMode.opModeIsActive()) {
            if (gyro.getAngle() <= d) {
                break;
            }
        }
        // gets angle turn

        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        // stops the motors
    }

    //method to turn left using gyro
    public void turnLeft(double degrees, double power) {
        gyro.resetWithDirection(Gyro.LEFT);
        // tells gyro we are going left

        // start the motors turning left
        double rightY_G1 = 0.0;
        double rightX_G1 = 1.0 * power;
        double leftX_G1 = 0.0;

        frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
        backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
        backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
        frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
        // sets the motors to the correct variables

        // loop until the robot turns :) degrees
        double d = degrees;
        while (opMode.opModeIsActive()) {
            if (gyro.getAngle() >= d) {
                break;
            }
        }
        // gets degrees

        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        // stops motors
    }

    //method to slide using ticks (encoder) and corrects with gyro
    public void slide (double power, double distance) {

        // sets power
        // left is negative

        double rightY_G1 = 0;
        double rightX_G1 = 0;
        double leftY_G1 = 0;
        double leftX_G1 = -power;

        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // sets encoder

        if (miniGyro != null) {
            miniGyro.reset();
        }
        long ticks = ticksToInchesSlide(distance);
        long start = System.currentTimeMillis();
        while (opMode.opModeIsActive()) {
            int rotations = frontRightMotor.getCurrentPosition();
            if (rotations<0) {
                rotations = rotations * -1;
            }
            if (rotations >= ticks) {
                break;
            }

            // Get the current heading; anything other than 0 is off course
            // this will return positive angle if drifting CCW
            // this will return negative angle if drifting CW
            double angle = miniGyro.getAngle();

            // Correct rightX_G1 [-1.0,1.0] to adjust turn
            // if rightX_G1 < 0 then robot will turn left
            // if rightX_G1 > 0 then robot will turn right
            rightX_G1 = -1.0 * angle * 0.022;
            leftX_G1 = ramp(-power, start);

            frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
            backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
            backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
            frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
        }

        // sets the inches to ticks so the motors understand
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        frontRightMotor.setPower(0);
    }

    public void goForwardOld(double power, double distance){
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // sets the encoders

        double rightY_G1 = 1.0 * power;
        double rightX_G1 = 0.0;
        double leftX_G1 = 0.0;
        // sets power

        frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
        backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
        backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
        frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
        // sets the correct variables to the motors

        if (miniGyro != null) {
            miniGyro.reset();
        }

        long ticks = ticksToInchesForward(distance);
        long start = System.currentTimeMillis();
        while (opMode.opModeIsActive()) {
            int rotations = frontRightMotor.getCurrentPosition();
            if (rotations<0) {
                rotations = rotations * -1;
            }
            if (rotations >= ticks) {
                break;
            }

            if (miniGyro.getAngle() > MAX_ANGLE) {
                turnRight(ANGLE_ADJ_PERC * miniGyro.getAngle(), ANGLE_ADJ_SPEED);
                frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
                backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
                backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
                frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
            }else if (miniGyro.getAngle() < -MAX_ANGLE){
                turnLeft (-ANGLE_ADJ_PERC * miniGyro.getAngle(), ANGLE_ADJ_SPEED);
                frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
                backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
                backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
                frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
            }
            // makes inches transfer to ticks
        }

        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        // sets motors to zero
    }

    //method for moving forward and backward using ticks (encoder), corrects with gyro
    public void goForward(double power, double distance){
        // sets the encoders
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double rightY_G1 = 1.0 * power;
        double rightX_G1 = 0.0;
        double leftX_G1 = 0.0;

        if (miniGyro != null) {
            miniGyro.reset();
        }

        long ticks = ticksToInchesForward(distance);
        long start = System.currentTimeMillis();

        while (opMode.opModeIsActive()) {
            int rotations = frontRightMotor.getCurrentPosition();
            if (rotations<0) {
                rotations = rotations * -1;
            }
            if (rotations >= ticks) {
                break;
            }

            // Get the current heading; anything other than 0 is off course
            // this will return positive angle if drifting CCW
            // this will return negative angle if drifting CW
            double angle = miniGyro.getAngle();

            // Correct rightX_G1 [-1.0,1.0] to adjust turn
            // if rightX_G1 < 0 then robot will turn left
            // if rightX_G1 > 0 then robot will turn right
            rightX_G1 = -1.0 * angle * 0.022;
            rightY_G1 = ramp(power, start);

            frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
            backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
            backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
            frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));

        }

        stopMotor();
        // sets motors to zero
    }

    public void goForwardNoGyro(double power, double distance){
        double rightY_G1 = 1.0 * power;
        double rightX_G1 = 0.0;
        double leftX_G1 = 0.0;
        // sets power

        // sets the correct variables to the motors

        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // sets the encoders

        long ticks = ticksToInchesForward(distance);
        long start = System.currentTimeMillis();

        while (opMode.opModeIsActive()) {
            int rotations = backLeftMotor.getCurrentPosition();
            if (rotations<0) {
                rotations = rotations * -1;
            }
            if (rotations >= ticks) {
                break;
            }

            rightY_G1 = ramp(power, start);
            frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
            backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
            backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
            frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));
        }

        stopMotor();
        // sets motors to zero
    }

    //method main 4 wheel motors
    public void stopMotor(){
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
    }

    //method for converting ticks to inches when going forward or backward
    public long ticksToInchesForward(double inches) {
        return (long) (inches * 38.7);
        //38.4
        // ticks forward formula
    }

    //method for converting ticks to inches when sliding
    public long ticksToInchesSlide(double inches) {
        return (long) (inches * 52);
        // tick to slide inches formula 74.6
    }

    //method for converting time into inches
    public long inchesToTime(double inches, double power) {
        return (long) (0.0384 * inches * 500.0 / power);
        // inches to time formula
    }

//    public void dropSweep() {
//        miniSweep.setPosition(-1);
//        opMode.sleep(500);
//    }

    public void goForwardRamp(double power, double distance){
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double rightY_G1 = 1.0 * power;
        double rightX_G1 = 0.0;
        double leftX_G1 = 0.0;

        long ticks = ticksToInchesForward(distance);

        // TODO: figure out the increment value based on the target speed
        float increement = 0.0f;

        // TODO: set the rightY_G1 start speed to a low value, but not so low the motors won't turn

        if (miniGyro != null) {
            miniGyro.reset();
        }
        while (opMode.opModeIsActive()) {
            int rotations = frontRightMotor.getCurrentPosition();
            if (rotations<0) {
                rotations = rotations * -1;
            }
            if (rotations >= ticks) {
                break;
            }

            // TODO: if a certain number of ticks has occured then increment rightY_G1 until desired speed

            // TODO: if we are approaching the target distance then start to decrement rightY_G1 speed

            // Get the current heading; anything other than 0 is off course
            // this will return positive angle if drifting CCW
            // this will return negative angle if drifting CW
            double angle = miniGyro.getAngle();

            // Correct rightX_G1 [-1.0,1.0] to adjust turn
            // if rightX_G1 < 0 then robot will turn left
            // if rightX_G1 > 0 then robot will turn right
            rightX_G1 = -1.0 * angle * 0.022;

            frontLeftMotor.setPower((rightX_G1 + rightY_G1 - leftX_G1));
            backLeftMotor.setPower((rightX_G1 + rightY_G1 + leftX_G1));
            backRightMotor.setPower((rightX_G1 - rightY_G1 + leftX_G1));
            frontRightMotor.setPower((rightX_G1 - rightY_G1 - leftX_G1));

        }

        // sets motors to zero
        stopMotor();
    }

    //method that raises cargo to the imputed level, places cargo, and lowers the linear slide
    public void raiseCargo(int level) {
        cargoServo.setPosition(0.28);
        double distance;
        //sensor is 4.75 inches from ground
        //sensor is 1.5 inches from linear slide in resting position
        //bucket is 2 inches less than the slide
        if (level == 1) {
            distance = 5.5;
        } else if (level == 2) {
            distance = 10;
        } else {
            distance = 16;
        }
        opMode.sleep(2000);
        while (distanceSensor.getDistance(DistanceUnit.INCH) <= distance) {
            linearSlideMotor.setPower(1);
            opMode.telemetry.addData("Distance:", distance);
            opMode.telemetry.update();
        }
        linearSlideMotor.setPower(0);
        shake();
        while (distanceSensor.getDistance(DistanceUnit.INCH) > 3) {
            linearSlideMotor.setPower(-0.3);
            opMode.telemetry.addData("Distance:", distance);
            opMode.telemetry.update();
        }
        linearSlideMotor.setPower(0);
        cargoServo.setPosition(1);
    }

    //method that places cargo
    public void placeCargo() {
        cargoServo.setPosition(0);
        opMode.sleep(1000);
        cargoServo.setPosition(0.28);
    }

    //method that picks up cargo, used for testing
    public void pickUpCargo() {
        cargoServo.setPosition(1);
        sweeper.setPower(1);
        opMode.sleep(2500);
        sweeper.setPower(0);
        cargoServo.setPosition(0);
    }

    //method that calls place cargo multiple times to shake out a stuck cargo
    public void shake() {
        placeCargo();
        placeCargo();
    }

    //method for duck spin (blue)
    public void duckSpin() {
        spinner.setPower(1);
        opMode.sleep(5000);
        spinner.setPower(0);
    }

    //method for duck spin (red)
    public void duckSpinR() {
        spinner.setPower(-1);
        opMode.sleep(5000);
        spinner.setPower(0);
    }
}