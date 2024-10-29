// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Shooter;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import frc.robot.Constants;
import frc.robot.Subsystem.Shooter.ShooterIO.ShooterData;


public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */

  ShooterIO io;

  public ShooterSub(ShooterIO shoot) {
    io = shoot;
  }

  public void setFeederOutput(double volts){
    io.setFeederVolts(volts);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
