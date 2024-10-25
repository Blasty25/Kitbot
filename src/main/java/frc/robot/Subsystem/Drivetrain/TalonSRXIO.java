package frc.robot.Subsystem.Drivetrain;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.RobotController;


public class TalonSRXIO implements DrivetrainIO {

    public TalonSRXIO(){

    TalonFX backLeft = new TalonFX(2);
    TalonFX backRight = new TalonFX(4);
    TalonFX frontLeft = new TalonFX(1);
    TalonFX frontRight = new TalonFX(3);

    
    //invert the left side motors
    backLeft.setInverted(true);
    frontLeft.setInverted(true);
    backRight.setInverted(false);
    frontRight.setInverted(false);
    }

    TalonSRXIO backLeft = new TalonSRXIO();
    TalonSRXIO backRight = new TalonSRXIO();
    TalonSRXIO frontLeft = new TalonSRXIO();
    TalonSRXIO frontRight = new TalonSRXIO();



    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {

    }


    @Override
    public void setVolts(double left, double right) {
    backLeft.set();
    }



}

