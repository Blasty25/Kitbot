package frc.robot.Monty.DrivetrainMonty;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DrivetrainIOMonty extends SubsystemBase implements DrivetrainIOstart {

    public CANSparkMax frontLeft = new CANSparkMax(DriveConstants.fL, MotorType.kBrushless);
    public CANSparkMax frontRight = new CANSparkMax(DriveConstants.fR, MotorType.kBrushless);
    public CANSparkMax backLeft = new CANSparkMax(DriveConstants.bL, MotorType.kBrushless);
    public CANSparkMax backRight = new CANSparkMax(DriveConstants.bR, MotorType.kBrushless);

    public DrivetrainIOMonty() {
        frontLeft.restoreFactoryDefaults();
        frontLeft.setIdleMode(IdleMode.kBrake);
        frontLeft.setSmartCurrentLimit(45);
        frontLeft.burnFlash();

        backLeft.restoreFactoryDefaults();
        backLeft.setIdleMode(IdleMode.kBrake);
        backLeft.setSmartCurrentLimit(45);
        backLeft.follow(frontRight);
        backLeft.burnFlash();

        frontRight.restoreFactoryDefaults();
        frontRight.setIdleMode(IdleMode.kBrake);
        frontRight.setSmartCurrentLimit(45);
        frontRight.burnFlash();

        backRight.restoreFactoryDefaults();
        backRight.setIdleMode(IdleMode.kBrake);
        backRight.setSmartCurrentLimit(45);
        backRight.follow(frontLeft);
        backRight.burnFlash();
    }

    public void arcadeDrive(double xSpeed, double turn) {

        // Applying the max speeds to motors
        xSpeed *= DriveConstants.maxDriveSpeed;
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
        frontLeft.set(leftSpeed);
        frontRight.set(rightSpeed);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = frontLeft.getBusVoltage() * frontLeft.getAppliedOutput();
        inputs.rightOutputVolts = frontRight.getBusVoltage() * frontRight.getAppliedOutput();
    }
}
