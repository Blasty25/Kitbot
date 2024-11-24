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
        backLeft.setInverted(false);
        frontLeft.setInverted(false);
        backRight.setInverted(true);
        frontRight.setInverted(true);
        backRight.follow(frontRight);
        backLeft.follow(frontLeft);

        backLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);

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
