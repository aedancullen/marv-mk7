package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;

import io.github.aedancullen.fruity.EssentialHeading;
import io.github.aedancullen.fruity.FruityController;
import io.github.aedancullen.fruity.MotorConfigurations;

@Autonomous(name="Auto-launching Autonomous - LEFT", group="MarvMk7")
public class LaunchAutoNewLeft extends LinearOpMode {

    DcMotor omni90;
    DcMotor omni0;

    final double FLAP_UP_POSITION = 0.5;
    final double LAUNCH_MOTOR_SPEED = 0.7;

    FruityController fruity;

    DcMotor collector;
    DcMotor launchL;
    DcMotor launchR;
    Servo launchFlap;

    LightSensor lightFront;

    /**public void oldinit() {
        fruity = new FruityController(hardwareMap, telemetry, "",
                Arrays.asList(
                        hardwareMap.dcMotor.get("dcOmni0"),
                        hardwareMap.dcMotor.get("dcOmni90"),
                        hardwareMap.dcMotor.get("dcOmni180"),
                        hardwareMap.dcMotor.get("dcOmni270")
                ),
                DcMotorSimple.Direction.REVERSE,
                DcMotor.RunMode.RUN_USING_ENCODER,
                MotorConfigurations.QUAD_NONDIAGONAL_SHORT);
        //fruity.setupRamper(0.002, 0.002);

        collector = hardwareMap.dcMotor.get("dcCollector0");
        launchL = hardwareMap.dcMotor.get("dcLaunchL");
        launchL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchR = hardwareMap.dcMotor.get("dcLaunchR");
        launchR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchR.setDirection(DcMotorSimple.Direction.REVERSE);
        launchFlap = hardwareMap.servo.get("svFlap0");
        launchFlap.setDirection(Servo.Direction.REVERSE);
        omni90 = hardwareMap.dcMotor.get("dcOmni90");
        omni0 = hardwareMap.dcMotor.get("dcOmni0");

    }**/

    public void runOpMode() {
        fruity = new FruityController(hardwareMap, telemetry, "imu",
                Arrays.asList(
                        hardwareMap.dcMotor.get("dcOmni0"),
                        hardwareMap.dcMotor.get("dcOmni90"),
                        hardwareMap.dcMotor.get("dcOmni180"),
                        hardwareMap.dcMotor.get("dcOmni270")
                ),
                DcMotorSimple.Direction.REVERSE,
                DcMotor.RunMode.RUN_USING_ENCODER,
                MotorConfigurations.QUAD_NONDIAGONAL_SHORT);
        fruity.setupRamper(0.002, 0.002, false);

        collector = hardwareMap.dcMotor.get("dcCollector0");
        launchL = hardwareMap.dcMotor.get("dcLaunchL");
        launchL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launchL.setMaxSpeed(2400);
        launchL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchR = hardwareMap.dcMotor.get("dcLaunchR");
        launchR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launchR.setMaxSpeed(2400);
        launchR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchR.setDirection(DcMotorSimple.Direction.REVERSE);
        launchFlap = hardwareMap.servo.get("svFlap0");
        launchFlap.setDirection(Servo.Direction.REVERSE);
        omni90 = hardwareMap.dcMotor.get("dcOmni90");

        lightFront = hardwareMap.lightSensor.get("lightFront");

        lightFront.enableLed(false);


        waitForStart();

        int start;

        launchL.setPower(LAUNCH_MOTOR_SPEED);
        launchR.setPower(LAUNCH_MOTOR_SPEED);
        sleep(800);

        launchFlap.setPosition(FLAP_UP_POSITION);
        sleep(400);
        launchFlap.setPosition(0);
        sleep(1000);

        launchFlap.setPosition(FLAP_UP_POSITION);
        sleep(400);


        launchFlap.setPosition(0);
        launchL.setPower(0);
        launchR.setPower(0);

        EssentialHeading target;
        double gain = 0.01;

        target = new EssentialHeading(40);
        start = omni90.getCurrentPosition();
        while (!(omni90.getCurrentPosition() > start + 4752 && opModeIsActive())) {
            fruity.drive(target, -0.2, fruity.getNecessaryRotationPower(target, gain));
        }
        fruity.drive(new EssentialHeading(0), 0, 0);

        /**start = omni90.getCurrentPosition();
        fruity.drive(new EssentialHeading(0), 0, 0.2);
        while (!(omni90.getCurrentPosition() > start + 2280 && opModeIsActive())) {}
        fruity.drive(new EssentialHeading(0), 0, 0);

        start = omni90.getCurrentPosition();
        fruity.drive(new EssentialHeading(0), -0.2, 0);
        while (!(omni90.getCurrentPosition() > start + 2280 && opModeIsActive())) {}
        fruity.drive(new EssentialHeading(0), 0, 0);

        double light = lightFront.getLightDetected();

        if (light > 0 && light < 98) {
            // is bad
            start = omni90.getCurrentPosition();
            fruity.drive(new EssentialHeading(-90), -0.2, 0);
            while (!(omni90.getCurrentPosition() > start + 2280 && opModeIsActive())) {}
            fruity.drive(new EssentialHeading(0), 0, 0);
        }

        start = omni90.getCurrentPosition();
        fruity.drive(new EssentialHeading(0), -0.2, 0);
        while (!(omni90.getCurrentPosition() > start + 2280 && opModeIsActive())) {}
        fruity.drive(new EssentialHeading(0), 0, 0);**/

    }

}
