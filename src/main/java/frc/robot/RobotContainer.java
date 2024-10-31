// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Modes;
import frc.robot.Monty.Commands.ArcadeDrive;
import frc.robot.Monty.DrivetrainMonty.DrivetrainIOMonty;
import frc.robot.Monty.DrivetrainMonty.MontyIOSim;
import frc.robot.Monty.DrivetrainMonty.MontySubsytem;
import frc.robot.Subsystem.Drivetrain.DrivetrainIOSim;
import frc.robot.Subsystem.Drivetrain.DrivetrainIOTalonSRX;
import frc.robot.Subsystem.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystem.Shooter.ShooterIO;
import frc.robot.Subsystem.Shooter.ShooterIONeo;
import frc.robot.Subsystem.Shooter.ShooterIOSim;
import frc.robot.Subsystem.Shooter.ShooterSub;
import frc.robot.Subsystem.Shooter.Commands.RunFeeder;
import frc.robot.Subsystem.Shooter.Commands.RunShooter;

public class RobotContainer {

  private final PneumaticHub hub = new PneumaticHub(31);
  // Create a new Xbox controller on port 0
  CommandXboxController controller = new CommandXboxController(0);
  DrivetrainSubsystem drivetrainSubsystem;
  MontySubsytem montySubsytem;
  ShooterSub shootersub;
  RunShooter runShooter; 
  RunFeeder runFeeder;

  public RobotContainer() {
    if (Constants.state == Modes.kSim) {
      drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainIOSim());
      montySubsytem = new MontySubsytem(new MontyIOSim());
      shootersub = new ShooterSub(new ShooterIOSim());
    }
    if (Constants.state == Modes.kReal) {
      drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainIOTalonSRX());
      montySubsytem = new MontySubsytem(new DrivetrainIOMonty());
      shootersub = new ShooterSub(new ShooterIONeo());
    }
    if (Constants.state == Modes.kReplay) {
      drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainIOSim());
      montySubsytem = new MontySubsytem(new MontyIOSim());
      shootersub = new ShooterSub(new ShooterIONeo());
    }
    configureBindings();
  }

  private void configureBindings() {
    switch (Constants.type) {
      case Monty:

      montySubsytem.setDefaultCommand(
        montySubsytem.ArcadeDrive(
          () -> controller.getLeftY(),
          () -> controller.getRightX()));

        break;

      case Kitbot:

         drivetrainSubsystem.setDefaultCommand(
            drivetrainSubsystem.setVoltagesArcadeCommand(
                () -> controller.getLeftY(),
                () -> controller.getRightX()));

        controller.a().whileTrue(new RunFeeder(shootersub, 0));
        controller.leftTrigger(0).whileTrue(new RunShooter(shootersub, 0));
        break;

      default:

    }
  }

  public Command getAutonomousCommand() {
    return new PrintCommand("Nah cya chat");
  }
}
