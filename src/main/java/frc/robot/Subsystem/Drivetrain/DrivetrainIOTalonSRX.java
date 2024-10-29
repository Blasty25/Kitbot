package frc.robot.Subsystem.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.RobotController;



public class DrivetrainIOTalonSRX implements DrivetrainIO{

    TalonSRX backLeft = new TalonSRX(2);
    TalonSRX backRight = new TalonSRX(4);
    TalonSRX frontLeft = new TalonSRX(1);
    TalonSRX frontRight = new TalonSRX(3);

    public DrivetrainIOTalonSRX(){

    //invert the left side motors
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
    public void setVolts(double left, double right) {   //setting the volts from DrivetraionIOSim, however I don't know what ControlMode is. So I don't know what to set it as.
    frontLeft.set(ControlMode.PercentOutput, left / RobotController.getInputVoltage());
    frontRight.set(ControlMode.PercentOutput, right / RobotController.getInputVoltage());
    }
    
    @Override   
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = frontLeft.getMotorOutputVoltage();
        inputs.rightOutputVolts = frontRight.getMotorOutputVoltage();
    }
}

