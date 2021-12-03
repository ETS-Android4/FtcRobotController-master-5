//Autonomys Engine V1

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomys Engine V1 Change Z", group="Red")
//@Disabled
public class Autonomys_Engine_V1_Change_Z extends LinearOpMode {

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
        double run = 1;
        
        waitForStart();
        
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
                arm_power = 0.4;
            }

            else {
                run = 0;
            }

            robot.leftFrontDrive.setPower(-fb + lr + -t);
            robot.rightFrontDrive.setPower(fb + -lr + -t);
            robot.leftRearDrive.setPower(-fb + -lr + -t);
            robot.rightRearDrive.setPower(fb + lr + -t);
            robot.arm_rot.setPower(arm_power);
            robot.Spin_fun.setPower(spin);

            telemetry.addData("PPS", pps);
            telemetry.update();

        }
        telemetry.update();
        sleep(1000);
    }
}
