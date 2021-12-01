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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Pushbot: ChangeZ Drive V1", group="Red")
//@Disabled
public class AutoDriveByTimeZ_Change_LinearV1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotV3 robot   = new HardwarePushbotV3();   // Use a Pushbot's hardware
    private final ElapsedTime     runtime = new ElapsedTime();
    static final double     r0 = 0;

    static final double     r1 = r0 + 0.1;
    static final double     r2 = r1 + 0.1;
    static final double     r3 = r2 + 0.6999999999;
    static final double     r4 = r3 + 2;
    static final double     r5 = r4 + 0.6999999999;
    static final double     r6 = r5 + 1;
    double old = 0;
    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();
        double run = 1;
        runtime.reset();
        while (run == 1) {

            //Notes
                //Each Tile = 59cm

                //fb = 1 second at power of 1 will be 90cm
                    // 1 = Forward
                    //-1 = Backward
                //lr = 1 second at power of 1 will be 90cm
                    // 1 = Right
                    //-1 = Left
                //t = 1 second at power of 1 will be 270 Degrees
                //t = 1 seocnds at power of 0.3 will be 90 Degrees
                    // 1 = Right
                    //-1 = Left

            double pps = (1 / (runtime.seconds() - old));
            old = runtime.seconds();

            double fb = 0;
            double lr = 0;
            double t = 0;
            double spin = 0;
            double arm_power = 0;

            if      (runtime.seconds() < r1) {
                fb = -0.5;
            }
            else if (runtime.seconds() < r2) {
                fb = 0.2;
            }
            else if (runtime.seconds() < r3) {
                lr = 1;
            }
            else if (runtime.seconds() < r4) {
                spin = 0.5;
            }
            else if (runtime.seconds() < r5) {
                lr = -1;
                fb = 1;
            }
            else if (runtime.seconds() < r6) {
                arm_power = 0.4
            }

            else {
                run = 0;
            }
` 
            robot.leftFrontDrive.setPower(-fb + lr + -t);
            robot.rightFrontDrive.setPower(fb + -lr + -t);
            robot.leftRearDrive.setPower(-fb + -lr + -t);
            robot.rightRearDrive.setPower(fb + lr + -t);
            robot.arm_rot.setPower(arm_power);
            robot.Spin_fun.setPower(spin);
            
            telemetry.addData("PPS", pps);

        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
