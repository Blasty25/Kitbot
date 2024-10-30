package frc.robot.Subsystem.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;
public class ShooterIOSim implements ShooterIO {

    CANSparkMax ShooterM;
    CANSparkMax FeederM;


    VoltageOut ShooterV;
    VoltageOut FeederV;

    public ShooterIOSim(){

        ShooterM = new CANSparkMax(Constants.DriveConstants.shooterID, MotorType.kBrushless);
        FeederM = new CANSparkMax(Constants.DriveConstants.feederID, MotorType.kBrushless);

        ShooterM.setInverted(true);
        FeederM.setInverted(true);

        ShooterV = new VoltageOut(0.0);
        FeederV = new VoltageOut(0.0);
    }

    
    @Override
    public void getData(ShooterData data) {
    }

    @Override
    public void setFeederVolts(double volts) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFeederVolts'");
    }

    @Override
    public void setShooterVolts(double volts) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setShooterVolts'");
    }
    
}
