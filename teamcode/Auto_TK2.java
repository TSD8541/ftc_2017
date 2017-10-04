package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
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

@Autonomous(name = "Auto TK2", group = "Auto_mode")
@Disabled
public class Auto_TK2 extends LinearOpMode {

    DcMotor leftMotor1;
    DcMotor rightMotor1;


    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        // bPrevinstance and bCurrinstance represent the previous and current instance of the button.
        boolean bPrevinstance = false;
        boolean bCurrinstance = false;


        // bLedOn represents the instance of the LED.
        boolean bLedOn = true;

        // state of the autonomous position for the robot
        int state = 0;


        ColorSensor sensorRGB;
        DeviceInterfaceModule cdim;
        CRServo crServo;

        sensorRGB = hardwareMap.colorSensor.get("cs");
        crServo = hardwareMap.crservo.get("c");


        leftMotor1 = hardwareMap.dcMotor.get("l1");
        rightMotor1 = hardwareMap.dcMotor.get("r1");


        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);

        // wait for the start button to be pressed.
        waitForStart();
        //runtime.reset(); //runtime starts

        // while the op mode is active, loop and read the RGB data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // check the status of the x button on either gamepad.
            bCurrinstance = gamepad1.x;

            // check for button instance transitions.
            if ((bCurrinstance = true) && (bCurrinstance != bPrevinstance)) {

                // button is transitioning to a pressed instance. So Toggle LED
                bLedOn = !bLedOn;
            }

            // update previous instance variable.
            bPrevinstance = bCurrinstance;

            //telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Red  ", sensorRGB.red());
            telemetry.addData("Blue ", sensorRGB.blue());
            telemetry.addData("State", state);

            telemetry.update();

            switch (state) {
                case 0:
                    crServo.setPower(0.5);
                    Thread.sleep(3000);
                    state++;
                    break;
                case 1:
                    // use servo controller for the second beacon
                    if (sensorRGB.red() > sensorRGB.blue()) {
                        leftMotor1.setPower(0.5);
                        rightMotor1.setPower(-0.5);
                        sleep(2000);
                    }
                    else {
                        leftMotor1.setPower(0.5);
                        rightMotor1.setPower(-0.5);
                        sleep(2000);
                    }
                    break;
                default:
            }
        }
    }
}