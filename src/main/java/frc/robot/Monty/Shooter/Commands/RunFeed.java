// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Monty.Shooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Monty.Shooter.m_ShooterSub;

public class RunFeed extends Command {
  /** Creates a new RunFeeder. */
  private final m_ShooterSub shooter;
  private final double speed;

  public RunFeed(m_ShooterSub arogya_S, double speed) {
    this.shooter = arogya_S;
    this.speed = speed;
  }

  @Override
  public void execute() {
    shooter.setFeederSpeed(speed);
  }
  @Override
  public void end(boolean interrupted) {    
    shooter.setFeederSpeed(0); 
  }


}
