package frc.robot.Subsystem.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;
public class ShooterIOSim implements ShooterIO {

    TalonFX ShooterM;
    TalonFX FeederM;


    VoltageOut ShooterV;
    VoltageOut FeederV;

    public ShooterIOSim(){

        ShooterM = new TalonFX(Constants.shooterID);
        FeederM = new TalonFX(Constants.feederID);

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
        FeederM.setControl(FeederV.withOutput(volts));
    }

    @Override
    public void setShooterVolts(double volts) {
        ShooterM.setControl(ShooterV.withOutput(volts));
    }
    
}
