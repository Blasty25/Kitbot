// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Shooter;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.inputs.LoggableInputs;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class ShooterIONeo implements ShooterIO{
    CANSparkMax Shooter;
    CANSparkMax Feeder;

    LoggableInputs inputs;
    
    public ShooterIONeo() {
        Shooter = new CANSparkMax(Constants.shooterID, MotorType.kBrushless);
        Feeder = new CANSparkMax(Constants.feederID, MotorType.kBrushless);

        Shooter.restoreFactoryDefaults();
        Feeder.restoreFactoryDefaults();
        
        Shooter.setIdleMode(IdleMode.kCoast);
        Feeder.setIdleMode(IdleMode.kCoast);
        
        Shooter.setInverted(false);
        Feeder.setInverted(false);

        Shooter.burnFlash();
        Feeder.burnFlash();

    }

    @Override
    public void setFeederVolts(double volts) {
        Feeder.setVoltage(volts);
    }
    @Override
    public void setShooterVolts(double volts) {
        Shooter.setVoltage(volts);
    }

    @Override
    public void updateInputs(ShooterData shooterData){
        shooterData.FeederVolts = Feeder.getAppliedOutput();
        shooterData.ShooterVolts = Shooter.getAppliedOutput();
    }
    
}