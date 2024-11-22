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
        frontLeft.setSmartCurrentLimit(50);
        frontLeft.setInverted(false);
        frontLeft.burnFlash();

        backLeft.restoreFactoryDefaults();
        backLeft.setIdleMode(IdleMode.kBrake);
        backLeft.setSmartCurrentLimit(50);
        backLeft.setInverted(false);
        backLeft.follow(frontLeft);
        backLeft.burnFlash();

        frontRight.restoreFactoryDefaults();
        frontRight.setIdleMode(IdleMode.kBrake);
        frontRight.setSmartCurrentLimit(50);
        frontRight.setInverted(false);
        frontRight.burnFlash();

        backRight.restoreFactoryDefaults();
        backRight.setIdleMode(IdleMode.kBrake);
        backRight.setSmartCurrentLimit(50);
        backRight.setInverted(false);
        backRight.follow(frontRight);
        backRight.burnFlash();

        SmartDashboard.putNumber("FrontLeftId", frontLeft.getDeviceId());
        SmartDashboard.putNumber("FrontRightId", frontRight.getDeviceId());
        SmartDashboard.putNumber("BackLeftId", backLeft.getDeviceId());
        SmartDashboard.putNumber("BackRightId", backRight.getDeviceId());
    }

    public void arcadeDrive(double left, double right) {
        frontLeft.setVoltage(left);
        frontRight.setVoltage(right);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = frontLeft.getBusVoltage() * frontLeft.getAppliedOutput();
        inputs.rightOutputVolts = frontRight.getBusVoltage() * frontRight.getAppliedOutput();
    }
}
