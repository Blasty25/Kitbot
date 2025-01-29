package frc.robot.Monty.DrivetrainMonty;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;

public class DrivetrainIOMonty extends SubsystemBase implements DrivetrainIOstart {

    public SparkMax frontLeft = new SparkMax(DriveConstants.fL, MotorType.kBrushless);
    public SparkMax frontRight = new SparkMax(DriveConstants.fR, MotorType.kBrushless);
    public SparkMax backLeft = new SparkMax(DriveConstants.bL, MotorType.kBrushless);
    public SparkMax backRight = new SparkMax(DriveConstants.bR, MotorType.kBrushless);


    public DrivetrainIOMonty() {
        SparkMaxConfig config = new SparkMaxConfig();

        config.idleMode(IdleMode.kBrake);
        config.smartCurrentLimit(50);
        config.inverted(true);

        frontLeft.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontRight.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        config.follow(frontLeft, true);

        backLeft.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        config.follow(frontRight, true);

        backRight.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SmartDashboard.putNumber("FrontLeftId", frontLeft.getDeviceId());
        SmartDashboard.putNumber("FrontRightId", frontRight.getDeviceId());
        SmartDashboard.putNumber("BackLeftId", backLeft.getDeviceId());
        SmartDashboard.putNumber("BackRightId", backRight.getDeviceId());
    }

    public void arcadeDrive(double left, double right) {
        frontLeft.set(left);
        frontRight.set(right);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = frontLeft.getBusVoltage() * frontLeft.getAppliedOutput();
        inputs.rightOutputVolts = frontRight.getBusVoltage() * frontRight.getAppliedOutput();
    }
}
