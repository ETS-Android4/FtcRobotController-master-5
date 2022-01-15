//Autonomys Engine V1

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="If Robot can not move Autonomys Engine V1 Change Z Red 1 New", group="Red")
//@Disabled
public class Autonomys_Engine_V1_Change_Z_Red1_New2 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotV3 robot   = new HardwarePushbotV3();   // Use a Pushbot's hardware
    private final ElapsedTime     runtime = new ElapsedTime();

    static final double     r1 = 0;
    static final double     r2 = 1;
    static final double     r3 = 4.5;
    static final double     r4 = 1.8;
    static final double     r5 = 0.5;
    static final double     r6 = 1;
    static final double     r7 = 1.2;
    static final double     r8 = 1;
    static final double     r9 = 1;
    static final double     r10 = 2;
    static final double     r11 = 0.8;
    static final double     r12 = 0;
    static final double     r13= 0;
    static final double     r14 = 0;
// Arm stuff

    static final double     C_P_R = 28;
    static final double     G_R   = 99.5;
    static final double     Tic_Per_Rev = C_P_R * G_R;
    static final double     arm_up = -Tic_Per_Rev/4.25;
    static final double     arm_down = -Tic_Per_Rev/4.25;

    double old = 0;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        //Step 1
        runtime.reset();
        driveMode(0, -0.5, 0);
        runtime.reset();

        while(runtime.seconds() < r1 && opModeIsActive()){
            telemetry.addData("Step 1", "Side Left");
//            robot.clawL.setPosition(0.4);
        }

        //Step 2
        driveMode(-0.5, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r2 && opModeIsActive()){
            telemetry.addData("Step 2", "Drive backward");
            telemetry.update();
        }

        //Step 3
        driveMode(0, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r3 && opModeIsActive()){
            telemetry.addData("Step 3", "Spin");
            telemetry.update();
            robot.Spin_fun.setPower(-.75);
        }

        //Step 4
        driveMode(0, -0.5, 0);
        runtime.reset();
        while(runtime.seconds() < r4 && opModeIsActive()){
            telemetry.addData("Step 4", "Side Left");
            telemetry.update();
            robot.Spin_fun.setPower(0);
        }

        //Step 5
        driveMode(-0.5, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r5 && opModeIsActive()){
            telemetry.addData("Step 5", "drive forward");
            telemetry.update();
        }

        //Step 6
        driveMode(0, 0, 0);
        runtime.reset();
        int NewTarget = robot.arm_rot.getCurrentPosition() + (int)arm_up;
        robot.arm_rot.setTargetPosition(NewTarget);
        robot.arm_rot.setPower(.5);
        robot.arm_rot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(runtime.seconds() < r6 && opModeIsActive()){
            telemetry.addData("Step 6", "arm up");
            telemetry.update();

            telemetry.addData("arm moving","%7d", (int)arm_up);
            telemetry.addData("Current Pos", "%7d", robot.arm_rot.getCurrentPosition());
            telemetry.addData("Timer", "%.2f", runtime.seconds());
            telemetry.update();
        }

        //Step 7
        driveMode(0.5, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r7 && opModeIsActive()){
            telemetry.addData("Step 7", "drive forward");
            telemetry.update();
        }

        //Step 8
        driveMode(0, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r8 && opModeIsActive()){
            telemetry.addData("Step 8", "Arm sorta down");
            telemetry.update();
        }

        //Step 9
        driveMode(0, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r9 && opModeIsActive()){
            telemetry.addData("Step 9", "let go");
            telemetry.update();
            robot.clawL.setPosition(0);
        }

        //Step 10
        driveMode(-0.5, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r10 && opModeIsActive()){
            telemetry.addData("Step 10", "drive backward");
            telemetry.update();
        }

        //Step 11
        driveMode(0, 0.5, 0);
        runtime.reset();
        while(runtime.seconds() < r11 && opModeIsActive()){
            telemetry.addData("Step 11", "drive Right");
            telemetry.update();
        }

        //Step 12
        driveMode(0, -0.5, 0);
        runtime.reset();
        while(runtime.seconds() < r12 && opModeIsActive()){
            telemetry.addData("Step 12", "arm down");
            telemetry.update();
        }

        //Step 13
        driveMode(1, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r13 && opModeIsActive()){
            telemetry.addData("Step 13", "drive forward");
            telemetry.update();
        }

        //Step 14
        AllStop();
        runtime.reset();
        int NewTarget_2 = robot.arm_rot.getCurrentPosition() + (int)arm_down;
        robot.arm_rot.setTargetPosition(NewTarget_2);
        robot.arm_rot.setPower(.5);
        robot.arm_rot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(runtime.seconds() < r14 && opModeIsActive()){
            telemetry.addData("Step 14", "Stop and Done");
            telemetry.update();

            telemetry.addData("arm moving","%7d", (int)arm_down);
            telemetry.addData("Current Pos", "%7d", robot.arm_rot.getCurrentPosition());
            telemetry.addData("Timer", "%.2f", runtime.seconds());
            telemetry.update();
        }

    }
    public void AllStop(){
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftRearDrive.setPower(0);
        robot.rightRearDrive.setPower(0);
    }

    public void driveMode(double fb, double lr, double t){
        // send power settings to drive motors
        robot.leftFrontDrive.setPower(-fb + lr + -t);
        robot.rightFrontDrive.setPower(fb + -lr + -t);
        robot.leftRearDrive.setPower(-fb + -lr + -t);
        robot.rightRearDrive.setPower(fb + lr + -t);
    }
}
