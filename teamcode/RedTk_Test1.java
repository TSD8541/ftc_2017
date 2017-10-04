package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robotics on 10/22/16.
 *
 * 11/1/16 change sideways percentage to 0.8 instead of 0.5
 * add a new case to detect white line at the robot's center instead of front (result from the old test robot)
 */

@Autonomous(name = "RedTk_Test1", group = "Auto_mode")
//@Disabled
public class RedTk_Test1 extends LinearOpMode {

    DcMotor leftMotor1;
    DcMotor rightMotor1;


    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {


        // state of the autonomous position for the robot
        int state = 0;


        Servo servo;
        double position = 0.5;
        double setPosition = 0;


        servo = hardwareMap.get(Servo.class, "c");


        //leftMotor1 = hardwareMap.dcMotor.get("l1");
        //rightMotor1 = hardwareMap.dcMotor.get("r1");

        leftMotor1  = hardwareMap.get(DcMotor.class, "l1");
        rightMotor1 = hardwareMap.get(DcMotor.class, "r1");



        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData(">", "Robot Start" );
        telemetry.update();

        // wait for the start button to be pressed.
        waitForStart();
        //runtime.reset(); //runtime starts

        // while the op mode is active, loop and read the RGB data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            telemetry.addData("Servo Position", "%5.2f", position);
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.addData("state", state);
            telemetry.update();

            switch (state) {
                case 0:
                    servo.setPosition(setPosition);
                    Thread.sleep(500);
                    state++;
                    break;
                case 1:
                    servo.setPosition(position);
                    Thread.sleep(500);
                    state++;
                    break;
                case 2:
                    leftMotor1.setPower(0.2);
                    rightMotor1.setPower(-0.2);
                    Thread.sleep(200);
                    state++;
                    break;
                case 3:
                    leftMotor1.setPower(0);
                    rightMotor1.setPower(0);
                    state++;
                    break;
                case 4:
                    servo.setPosition(setPosition);
                    Thread.sleep(500);
                default:
            }
        }
    }
}