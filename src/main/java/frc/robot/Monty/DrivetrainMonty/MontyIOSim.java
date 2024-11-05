package frc.robot.Monty.DrivetrainMonty;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;

public class MontyIOSim implements DrivetrainIOstart {
    DifferentialDrivetrainSim physicsSim = DifferentialDrivetrainSim.createKitbotSim(
            KitbotMotor.kDoubleNEOPerSide,
            KitbotGearing.k8p45,
            KitbotWheelSize.kSixInch,
            null);
        public static double leftVolts, rightVolts;



    @Override
    public void updateInputs(DrivetrainIOInputs inputs) 
    {
        physicsSim.update(0.020);
        
        inputs.leftCurrentAmps = new double[]{physicsSim.getLeftCurrentDrawAmps()};
        inputs.leftPositionMeters = physicsSim.getLeftPositionMeters();
        inputs.leftOutputVolts = leftVolts;
        inputs.leftVelocityMetersPerSecond = physicsSim.getLeftVelocityMetersPerSecond();
        inputs.rightCurrentAmps = new double[]{physicsSim.getRightCurrentDrawAmps()};
        inputs.rightPositionMeters = physicsSim.getRightPositionMeters();
        inputs.rightOutputVolts = rightVolts;
        inputs.rightVelocityMetersPerSecond = physicsSim.getRightVelocityMetersPerSecond();

    }


    @Override
    public void arcadeDrive(double xSpeed, double turn) {
        // Applying the max speeds to motors
        xSpeed *= -DriveConstants.maxDriveSpeed;
        turn *= DriveConstants.maxTurnSpeed;

        if (DriveConstants.squareInputs) {
            xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
            turn = Math.copySign(turn * turn, turn);
        }

        // Creates the saturated speeds of the motors
        double leftSpeed = xSpeed - turn;
        double rightSpeed = xSpeed + turn;

        // Finds the maximum possible value of throttle + turn along the vector that the
        // joystick is pointing, and then desaturates the wheel speeds.
        double greaterInput = Math.max(Math.abs(xSpeed), Math.abs(turn));
        double lesserInput = Math.min(Math.abs(xSpeed), Math.abs(turn));
        if (greaterInput == 0.0) {
            leftSpeed = 0;
            rightSpeed = 0;
        } else {
            double saturatedInput = (greaterInput + lesserInput) / greaterInput;
            leftSpeed /= saturatedInput;
            rightSpeed /= saturatedInput;
        }

        // Puts the left and right speeds onto SmartDashboard.
        SmartDashboard.putNumber("LeftSpeed", leftSpeed);
        SmartDashboard.putNumber("RightSpeed", rightSpeed);

        // Sets the speed of the motors.
        physicsSim.setInputs(leftSpeed * RobotController.getInputVoltage(), rightSpeed * RobotController.getInputVoltage());
        
        leftVolts = leftSpeed * RobotController.getInputVoltage();
        rightVolts = rightSpeed * RobotController.getInputVoltage();
    }
}