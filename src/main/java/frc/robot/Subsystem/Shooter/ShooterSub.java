// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */

 ShooterIO io;

  public ShooterSub(ShooterIO shoot) {
    io = shoot;
  }


  public void setFeederOutput(double volts){
    io.setFeederVolts(volts);
  }


  public void setShooterOutput(double volts) {
    io.setShooterVolts(volts);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }



}