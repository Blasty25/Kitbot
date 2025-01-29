// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Shooter;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.inputs.LoggableInputs;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class ShooterIONeo implements ShooterIO{
    SparkMax Shooter;
    SparkMax Feeder;

    LoggableInputs inputs;
    
    public ShooterIONeo() {
        Shooter = new SparkMax(Constants.shooterID, MotorType.kBrushless);
        Feeder = new SparkMax(Constants.feederID, MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();

        config.idleMode(IdleMode.kCoast);
        
        Shooter.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        Feeder.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

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