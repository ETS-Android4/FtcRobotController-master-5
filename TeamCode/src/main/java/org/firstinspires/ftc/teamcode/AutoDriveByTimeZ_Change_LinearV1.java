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

@Autonomous(name="Pushbot: Change Drive V1", group="Red")
//@Disabled
public class AutoDriveByTimeZ_Change_LinearV1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotV3 robot   = new HardwarePushbotV3();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();
    static final double     r0 = 0;

    static final double     r1 = r0 + 1;
    static final double     r2 = r1 + 1;
    static final double     r3 = r2 + 1;
    static final double     r4 = r3 + 1;
    static final double     r5 = r4 + 1;
    static final double     r6 = r5 + 1;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();
        double run = 1;
        while (run == 1) {
            double fb = 0;
            double lr = 0;
            double t = 0;
            if (opModeIsActive() && (runtime.seconds() < r1)) {
                fb = 1;
            }
            else if (opModeIsActive() && (runtime.seconds() < r2)) {
                fb = -1;
            }
            else if (opModeIsActive() && (runtime.seconds() < r3)) {
                t = 1;
            }
            else if (opModeIsActive() && (runtime.seconds() < r4)) {
                t = -1;
            }
            else if (opModeIsActive() && (runtime.seconds() < r5)) {
                lr = 1;
            }
            else if (opModeIsActive() && (runtime.seconds() < r6)) {
                lr = -1;
            }

            robot.leftFrontDrive.setPower(-fb + lr + -t);
            robot.rightFrontDrive.setPower(fb + -lr + t);
            robot.leftRearDrive.setPower(-fb + -lr + t);
            robot.rightRearDrive.setPower(fb + lr + -t);

            run = 0;
            
        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
