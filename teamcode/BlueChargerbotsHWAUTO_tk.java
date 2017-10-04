package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by robotics on 1/4/17.
 */

public class BlueChargerbotsHWAUTO_tk {

    /* local OpMode members. */
    private ElapsedTime period = new ElapsedTime();
    HardwareMap hwMap           =  null;

    DcMotor leftMotor1;
    DcMotor rightMotor1;

    ColorSensor ColorSensorRGB;
    Servo dividerServo;
    /* Constructor */
    public BlueChargerbotsHWAUTO_tk() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;
        leftMotor1 = ahwMap.dcMotor.get("l1");
        rightMotor1 = ahwMap.dcMotor.get("r1");
        dividerServo = ahwMap.servo.get("c");
        ColorSensorRGB = ahwMap.colorSensor.get("cs");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    /***
     *
     * setMotorPower implements power for each of four motors. Those four values are
     * used to move the robot at a certain direction.
     *
     * @param lM1 the power for the left motor 1
     * @param rM1 the power for the right motor 1

     */
    public void setMotorPower(double lM1,double rM1) {
        leftMotor1.setPower(lM1);
        rightMotor1.setPower(rM1);
    }

}

