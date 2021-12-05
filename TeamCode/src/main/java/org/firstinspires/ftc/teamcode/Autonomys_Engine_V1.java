//Autonomys Engine V1

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomys Engine V1", group="Red")
//@Disabled
public class Autonomys_Engine_V1 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbotV3 robot   = new HardwarePushbotV3();   // Use a Pushbot's hardware
    private final ElapsedTime     runtime = new ElapsedTime();
    static final double     r0 = 0;

    static final double     r1 = r0 + 1;
    static final double     r2 = r1 + 1;
    static final double     r3 = r2 + 1;
    static final double     r4 = r3 + 1;
    static final double     r5 = r4 + 1;
    static final double     r6 = r5 + 1;
    double old = 0;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        double run = 1;
        
        waitForStart();
        
        runtime.reset();
        while (run == 1) {

            double pps = (1 / (runtime.seconds() - old));
            old = runtime.seconds();

            double fb = 0;
            double lr = 0;
            double t = 0;

            if (runtime.seconds() < r1) {
                fb = 0.3;
            } else if (runtime.seconds() < r2) {
                fb = -0.3;
            } else if (runtime.seconds() < r3) {
                lr = 0.3;
            } else if (runtime.seconds() < r4) {
                lr = -0.3;
            } else if (runtime.seconds() < r5) {
                t = 0.3;
            } else if (runtime.seconds() < r6) {
                t = -0.3;
            } else {
                run = 0;
            }
            robot.leftFrontDrive.setPower(-fb + lr + -t);
            robot.rightFrontDrive.setPower(fb + -lr + -t);
            robot.leftRearDrive.setPower(-fb + -lr + -t);
            robot.rightRearDrive.setPower(fb + lr + -t);

            telemetry.addData("PPS", pps);
            telemetry.update();

        }
        telemetry.update();
        sleep(1000);
    }
}