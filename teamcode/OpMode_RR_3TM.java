/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="OpMode RR_3_TM", group="Linear Opmode")
@Disabled
public class OpMode_RR_3TM extends OpMode {

    BlueChargerbotsHWtk2 robot = new BlueChargerbotsHWtk2();


    /*
    * Code to run ONCE when the driver hits INIT
    */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
    }

    /*
    * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    */
    @Override
    public void init_loop() {
    }
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    @Override
    public void loop() {

        telemetry.addLine("Robot starts Now");
        telemetry.update();

        // Run wheels in Opmode
        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0){
            //robot.waitForTick(100);
            robot.leftMotor1.setPower(-gamepad1.left_stick_y);
            robot.leftMotor2.setPower(gamepad1.left_stick_x);
            robot.rightMotor1.setPower(-gamepad1.left_stick_y);
            robot.rightMotor2.setPower(gamepad1.left_stick_x);

        }
        else if (gamepad1.left_trigger !=0){
            //robot.waitForTick(100);
            robot.leftMotor2.setPower(-gamepad1.left_trigger);
            robot.rightMotor2.setPower(gamepad1.left_trigger);
            robot.leftMotor1.setPower(-gamepad1.left_trigger);
            robot.rightMotor1.setPower(gamepad1.left_trigger);
        }
        else if (gamepad1.right_trigger !=0){
            //robot.waitForTick(100);
            robot.leftMotor2.setPower(gamepad1.right_trigger);
            robot.rightMotor2.setPower(-gamepad1.right_trigger);
            robot.leftMotor1.setPower(gamepad1.right_trigger);
            robot.rightMotor1.setPower(-gamepad1.right_trigger);
        }
        else {
            //robot.waitForTick(100);
            robot.leftMotor1.setPower(0);
            robot.leftMotor2.setPower(0);
            robot.rightMotor2.setPower(0);
            robot.rightMotor1.setPower(0);
        }
    }
}

