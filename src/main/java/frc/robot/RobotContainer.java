// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Modes;
import frc.robot.Subsystem.Drivetrain.DrivetrainIOSim;
import frc.robot.Subsystem.Drivetrain.DrivetrainSubsystem;

public class RobotContainer {
  // Create a new Xbox controller on port 0
  CommandXboxController controller = new CommandXboxController(0);
  DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();

  public RobotContainer() {
    configureBindings();
    if(Constants.state == Modes.kReal){
    new DrivetrainSubsystem(new DrivetrainIOSim());
    }
  }

  private void configureBindings() {
    drivetrainSubsystem.setDefaultCommand(
        drivetrainSubsystem.setVoltagesArcadeCommand(
            () -> controller.getLeftY(),
            () -> controller.getRightX()));
  }

  public Command getAutonomousCommand() {
    return new PrintCommand("Nah cya chat");
  }
}
