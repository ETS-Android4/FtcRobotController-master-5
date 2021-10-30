package org.firstinspires.ftc.teamcode;

public class OldCode {
    //            if ((gamepad1.left_stick_y > (controller_safe_zone / 100)) || (gamepad1.left_stick_y < (controller_safe_zone / -100)) & (gamepad1.left_stick_x > (controller_safe_zone / 100)) || (gamepad1.left_stick_x < (controller_safe_zone / -100))) {
//                    //Define Forward Backward
//                    robot.leftFrontDrive.setPower(((-forward_backward) + (-left_right)) / 2);
//                    robot.rightFrontDrive.setPower(((forward_backward) + (left_right)) / 2);
//                    robot.leftRearDrive.setPower(((-forward_backward) + (left_right)) / 2);
//                    robot.rightRearDrive.setPower(((forward_backward) + (-left_right)) / 2);
//                }
//            else {
//                //Define Forward Backward
//                robot.leftFrontDrive.setPower(-forward_backward);
//                robot.rightFrontDrive.setPower(forward_backward);
//                robot.leftRearDrive.setPower(-forward_backward);
//                robot.rightRearDrive.setPower(forward_backward);
//
//                //Define Strafe Left Right
//                robot.leftFrontDrive.setPower(-left_right);
//                robot.rightFrontDrive.setPower(left_right);
//                robot.leftRearDrive.setPower(left_right);
//                robot.rightRearDrive.setPower(-left_right);
//
//                //Define Turn Left Right
//                robot.leftFrontDrive.setPower(turn_right_left);
//                robot.rightFrontDrive.setPower(turn_right_left);
//                robot.leftRearDrive.setPower(turn_right_left);
//                robot.rightRearDrive.setPower(turn_right_left);
//                }
//            }

//        if (gamepad2.right_trigger > .1) {
//            robot.TrackMotor.setPower(gamepad2.right_trigger);
//        }
//        else {
//            robot.TrackMotor.setPower(0);
//        }
//
//        robot.SlideMotor.setPower(SlidePower / 2);
//
//        robot.Walble_Motor.setPower(gamepad2.right_stick_y / 2);
//
//        CurrentServoPos = robot.servo2.getPosition();
//
//        if (gamepad2.dpad_down) {
//            robot.servo.setPosition(.005);
//        }
//        else {
//            robot.servo.setPosition(.5);
//        }
//
//        robot.servo2.setPosition(CurrentServoPos);
//        CurrentServoPos = robot.servo.getPosition();
//
//        robot.servo.setPosition(.99);
////        if (gamepad2.right_stick_y > 0.05 && CurrentServoPos < 1.0) {
////            robot.servo.setPosition(CurrentServoPos + 0.05);
////        }
////        else if (gamepad2.right_stick_y < -0.05 && CurrentServoPos > 0.0) {
////            robot.servo.setPosition(CurrentServoPos + -0.05);
////        }

//        if (gamepad2.a) {
//            CurrentServoPos2 = .5;
//        }
//        else if (gamepad2.b) {
//            CurrentServoPos2 = .9;
//        }

//

//        telemetry.addData("Say", "Everything is fine just fine.");
//        telemetry.addData("left",  "%.2f", left);
//        telemetry.addData("right", "%.2f", right);
//        telemetry.addData("slide", "%.2f", SlidePower);
//        telemetry.addData("CurrentServoPos","%.2f", CurrentServoPos);

    //public DcMotor  SlideMotor = null;
    //public DcMotor  TrackMotor = null;

    //public Servo  servo = null;

    //SlideMotor  = hwMap.get(DcMotor.class, "scoppy_nom_nom");
    //servo = hwMap.get(Servo.class,"Servo1");
    //TrackMotor  = hwMap.get(DcMotor.class, "TrackMotor");

    //SlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    //TrackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    //SlideMotor.setPower(0);
    //TrackMotor.setPower(0);

}
