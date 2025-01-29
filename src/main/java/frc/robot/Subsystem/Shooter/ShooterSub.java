// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Shooter;

import java.io.Console;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.inputs.LoggableInputs;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */

 ShooterIO io;
 ShooterIOSim sim;
 ShooterDataAutoLogged inputs = new ShooterDataAutoLogged();

  public ShooterSub(ShooterIO shoot) {
    io = shoot;
  }


  public void setFeederOutput(double volts){
    io.setFeederVolts(volts * Constants.maxFeederSpeed);
  }

  public void setShooterOutput(double volts) {
    io.setShooterVolts(volts * Constants.maxShooterSpeed);
  }


  @Override
  public void periodic() {
    Logger.processInputs("Kitbot Shooter", inputs);
  }



}