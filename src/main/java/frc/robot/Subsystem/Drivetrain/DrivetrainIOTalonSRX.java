package frc.robot.Subsystem.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.RobotController;

import frc.robot.Constants;

public class DrivetrainIOTalonSRX implements DrivetrainIO {

    TalonSRX backLeft = new TalonSRX(Constants.DriveConstants.backLeft);
    TalonSRX backRight = new TalonSRX(Constants.DriveConstants.backRight);
    TalonSRX frontLeft = new TalonSRX(Constants.DriveConstants.frontLeft);
    TalonSRX frontRight = new TalonSRX(Constants.DriveConstants.frontRight);

    public DrivetrainIOTalonSRX() {
        backLeft.configFactoryDefault();
        backRight.configFactoryDefault();
        frontLeft.configFactoryDefault();
        frontRight.configFactoryDefault();
        // invert the left side motors
        backLeft.setInverted(true);
        frontLeft.setInverted(true);
        backRight.setInverted(false);
        frontRight.setInverted(false);
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        backLeft.setNeutralMode(NeutralMode.Coast);
        backRight.setNeutralMode(NeutralMode.Coast);
        frontLeft.setNeutralMode(NeutralMode.Coast);
        frontRight.setNeutralMode(NeutralMode.Coast);

    }

    @Override
    public void setVolts(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left / RobotController.getInputVoltage());
        frontRight.set(ControlMode.PercentOutput, right / RobotController.getInputVoltage());
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = frontLeft.getMotorOutputVoltage();
        inputs.rightOutputVolts = frontRight.getMotorOutputVoltage();
    }
}
