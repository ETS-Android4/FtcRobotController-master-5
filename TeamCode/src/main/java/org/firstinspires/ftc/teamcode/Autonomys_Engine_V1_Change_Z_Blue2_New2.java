//Autonomys Engine V1

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomys Engine V1 Change Z Blue 2 New2", group="Blue")
//@Disabled
public class Autonomys_Engine_V1_Change_Z_Blue2_New2 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotV3 robot   = new HardwarePushbotV3();   // Use a Pushbot's hardware
    private final ElapsedTime     runtime = new ElapsedTime();

    static final double     r1 = 1.9;
    static final double     r2 = 1;
    static final double     r3 = 1;
    static final double     r4 = 1.5;
    static final double     r5 = .5;
    static final double     r6 = 0;

    // Arm stuff
    static final double     C_P_R = 28;
    static final double     G_R   = 99.5;
    static final double     Tic_Per_Rev = C_P_R * G_R;
    static final double     QUARTER_TURN = -Tic_Per_Rev/1.75;
    static final double     die = -Tic_Per_Rev/-3;
    static final double     as = -Tic_Per_Rev/2;


    double old = 0;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        double run = 1;

        waitForStart();

        //Step 1
        runtime.reset();
        driveMode(0, 0.5, 0);
        runtime.reset();

        int NewTarget = robot.arm_rot.getCurrentPosition() + (int)QUARTER_TURN;
        robot.arm_rot.setTargetPosition(NewTarget);
        robot.arm_rot.setPower(.5);
        robot.arm_rot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(runtime.seconds() < r1 && opModeIsActive()){
            telemetry.addData("Step 1", "drive forward");

            telemetry.addData("arm moving","%7d", (int)QUARTER_TURN);
            telemetry.addData("Current Pos", "%7d", robot.arm_rot.getCurrentPosition());
            telemetry.addData("Timer", "%.2f", runtime.seconds());
            telemetry.update();
        }

        //Step 2
        driveMode(0, 0, 0);
        runtime.reset();
        while(runtime.seconds() < r2 && opModeIsActive()){
            telemetry.addData("Step 2", "drive forward");
            telemetry.update();
            robot.clawL.setPosition(0);
        }

        //Step 3
        driveMode(0, -0.5, 0);
        runtime.reset();

        int NewTarget2 = robot.arm_rot.getCurrentPosition() + (int)die;
        robot.arm_rot.setTargetPosition(NewTarget2);
        robot.arm_rot.setPower(-.5);
        robot.arm_rot.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(runtime.seconds() < r3 && opModeIsActive()){
            telemetry.addData("Step 3", "drive forward");
            telemetry.update();
            telemetry.addData("arm moving","%7d", (int)die);
            telemetry.addData("Current Pos", "%7d", robot.arm_rot.getCurrentPosition());
            telemetry.addData("Timer", "%.2f", runtime.seconds());
            telemetry.update();
        }

        //Step 4
        driveMode(.75, 0, 0);
        runtime.reset();

        while(runtime.seconds() < r4 && opModeIsActive()){
            telemetry.addData("Step 4", "drive forward");
            telemetry.update();
        }

        //Step 5
        driveMode(.75, 0, 0);
        runtime.reset();

        int NewTarget3 = robot.arm_rot.getCurrentPosition() + (int)as;
        robot.arm_rot.setTargetPosition(NewTarget3);
        robot.arm_rot.setPower(.5);
        robot.arm_rot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(runtime.seconds() < r5 && opModeIsActive()){
            telemetry.addData("Step 5", "drive forward");
            telemetry.update();
            telemetry.addData("arm moving","%7d", (int)as);
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
