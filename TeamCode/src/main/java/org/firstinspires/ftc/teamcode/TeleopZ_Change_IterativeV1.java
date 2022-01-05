/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//Blue Down Arrow To Download
//Green Check With Comment To Upload

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Pushbot: Teleop Z Change V1 (Use this for now.)", group="Pushbot")
//@Disabled
public class TeleopZ_Change_IterativeV1 extends OpMode{
    HardwarePushbotV3 robot       = new HardwarePushbotV3();

    //Define Start Variables
//    double CurrentServoPos2 = .5;
    int profile = 0;
    int profile_count = 3;
    float controller_safe_zone = 10;
    int profile_switch_debug = 0;
    int bumper_check = 0;
    double old = 0;
    double claw_rot = 0;
    private final ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override

    public void loop() {
        //Start Files Here
        
        double pps = (1 / (runtime.seconds() - old));
        old = runtime.seconds();
        
        //Reset / Define Variables
        double left = 0;
        double right = 0;
        double forward_backward;
        double left_right;
        double turn_right_left;
        double spin;
        double trig_right = 0;
        double trig_left = 0;


        //Define Controller 1

        //Define continue when button Y is pressed
        if ((gamepad1.y) & (profile_switch_debug == 0)) {
            profile_switch_debug = 1;
            profile = profile + 1;
            profile = (profile % profile_count);

        }

        //Define Do not continue until button Y is not pressed
        if ((profile_switch_debug == 1) & (!gamepad1.y)) {
            profile_switch_debug = 0;
        }

        //Set Profile
        int base_profile = profile + 1;

        //Display Stats
        if (base_profile == 1) {
            telemetry.addData("Profile str", "Tank Drive");
        }
        else if (base_profile == 2) {
            telemetry.addData("Profile str", "Spider Drive");
        }

        telemetry.addData("Profile int",  base_profile);
        telemetry.addData("safe zone", controller_safe_zone);
        telemetry.addData("PPS", pps);
        //Set Profile

        //Define Movement/Controls
        if (base_profile == 1) {

            //Define Tank Drive

            spin = 0;

            //Define Spin Controls
            if (gamepad1.x) {
                spin = 1;
            }
            if (gamepad1.a) {
                spin = -1;
            }

            //Define Left Wheels
            if ((gamepad1.left_stick_y > (controller_safe_zone / 100)) || (gamepad1.left_stick_y < (controller_safe_zone / -100))) {
                left = -gamepad1.left_stick_y;
            }

            //Define Right Wheels
            if ((gamepad1.right_stick_y > (controller_safe_zone / 100)) || (gamepad1.right_stick_y < (controller_safe_zone / -100))) {
                right = -gamepad1.right_stick_y;
            }

            //Define Strafe Right
            if ((gamepad1.right_trigger > (controller_safe_zone / 100)) || (gamepad1.right_trigger < (controller_safe_zone / -100))) {
                trig_right = gamepad1.right_trigger;
            }

            //Define Strafe Left
            if ((gamepad1.left_trigger > (controller_safe_zone / 100)) || (gamepad1.left_trigger < (controller_safe_zone / -100))) {
                trig_left = gamepad1.left_trigger;
            }

            //Define New Drive
            robot.leftFrontDrive.setPower(-left + trig_right + -trig_left);
            robot.rightFrontDrive.setPower(right + -trig_right + trig_left);
            robot.leftRearDrive.setPower(-left + -trig_right + trig_left);
            robot.rightRearDrive.setPower(right + trig_right + -trig_left);

            //Define Spin The Spinner
            robot.Spin_fun.setPower(spin);

        }

        else if (base_profile == 2) {

            //Define Spider Drive
            forward_backward = 0;
            left_right = 0;
            turn_right_left = 0;
            spin = 0;

            //Define Spin Controls
            if (gamepad1.x) {
                spin = 1;
            }
            if (gamepad1.a) {
                spin = -1;
            }

            //Define Forward Backward Controls
            if ((gamepad1.left_stick_y > (controller_safe_zone / 100)) || (gamepad1.left_stick_y < (controller_safe_zone / -100))) {
                forward_backward = -(gamepad1.left_stick_y);
            }

            //Define Strafe Left Right Controls
            if ((gamepad1.left_stick_x > (controller_safe_zone / 100)) || (gamepad1.left_stick_x < (controller_safe_zone / -100))) {
                left_right = -(gamepad1.left_stick_x);
            }

            //Define Turn Left Right Controls
            if ((gamepad1.right_stick_x > (controller_safe_zone / 100)) || (gamepad1.right_stick_x < (controller_safe_zone / -100))) {
                turn_right_left = -(gamepad1.right_stick_x);
            }

            //Define Change Safe Zone
            if ((gamepad1.right_bumper) || (gamepad1.left_bumper)) {
                if ((gamepad1.right_bumper) & (bumper_check == 0) & (controller_safe_zone < 100)) {
                    controller_safe_zone = controller_safe_zone + 5;
                    bumper_check = 1;

                }
                if ((gamepad1.left_bumper) & (bumper_check == 0) & (controller_safe_zone > 0)) {
                    controller_safe_zone = controller_safe_zone - 5;
                    bumper_check = 1;

                }
            }
            else {
                bumper_check = 0;
            }

            //Define New Drive
            robot.leftFrontDrive.setPower(-forward_backward + -left_right + turn_right_left);
            robot.rightFrontDrive.setPower(forward_backward + left_right + turn_right_left);
            robot.leftRearDrive.setPower(-forward_backward + left_right + turn_right_left);
            robot.rightRearDrive.setPower(forward_backward + -left_right + turn_right_left);

            //Define Spin The Spinner
            robot.Spin_fun.setPower(spin);

            //Define Bucket Rotation


        }
        else if (base_profile == 3) {

            //Define D-Pad Drive
            forward_backward = 0;
            left_right = 0;
            turn_right_left = 0;

            if (gamepad1.dpad_up) {
                forward_backward = 1;
            }

            if (gamepad1.dpad_down) {
                forward_backward = -1;
            }

            if (gamepad1.dpad_left) {
                turn_right_left = 1;
            }

            if (gamepad1.dpad_right) {
                turn_right_left = -1;
            }

            //Spin The Spinner
            spin = 0;

            //Define Spin Controls
            if (gamepad1.x) {
                spin = 1;
            }
            if (gamepad1.a) {
                spin = -1;
            }

            //Define New Drive
            robot.leftFrontDrive.setPower(-forward_backward + -left_right + turn_right_left);
            robot.rightFrontDrive.setPower(forward_backward + left_right + turn_right_left);
            robot.leftRearDrive.setPower(-forward_backward + left_right + turn_right_left);
            robot.rightRearDrive.setPower(forward_backward + -left_right + turn_right_left);

            //Define Spin The Spinner
            robot.Spin_fun.setPower(spin);

            }

        //Define Controller 2

        double arm_power = 0;
        double claw_power = 0;

        if ((-gamepad2.left_stick_y > (controller_safe_zone / 100)) || (-gamepad2.left_stick_y < (controller_safe_zone / -100))) {
            arm_power = gamepad2.left_stick_y;
        }
        if ((gamepad2.right_stick_y > (controller_safe_zone / 100)) || (gamepad2.right_stick_y < (controller_safe_zone / -100))) {
            claw_power = -gamepad2.right_stick_y;
        }
//        if (claw_rot < 1 & claw_rot > -0.3)
//        claw_rot = claw_rot + claw_power;
        if (claw_power > .9) {
            claw_rot = .3;
        }
        if (claw_power < 0) {
            claw_rot = 0;
        }

        if (gamepad2.a) {
            robot.arm_rot.setPower(arm_power);
        }
        else {
            robot.arm_rot.setPower(arm_power / 2);
        }

        robot.clawL.setPosition(claw_rot);
//        robot.clawR.setPosition(claw_rot);
        telemetry.addData("Claw Rot", (claw_rot));
        telemetry.addData("Claw Rot", (claw_power));
        }


    @Override
    public void stop() {
    }
}
