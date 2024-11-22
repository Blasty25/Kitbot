// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.RobotType;
import frc.robot.Monty.DrivetrainMonty.DrivetrainIOMonty;
import frc.robot.Monty.DrivetrainMonty.MontyIOSim;
import frc.robot.Monty.DrivetrainMonty.MontySubsytem;
import frc.robot.Monty.Intake.Intake;
import frc.robot.Monty.Intake.Commands.RunIntake;
import frc.robot.Monty.Intake.Commands.SetIntakeState;
import frc.robot.Monty.Shooter.m_ShooterSub;
import frc.robot.Monty.Shooter.Commands.RunFeed;
import frc.robot.Monty.Shooter.Commands.RunLaunch;
import frc.robot.Subsystem.Drivetrain.DrivetrainIOSim;
import frc.robot.Subsystem.Drivetrain.DrivetrainIOTalonSRX;
import frc.robot.Subsystem.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystem.Shooter.ShooterIONeo;
import frc.robot.Subsystem.Shooter.ShooterIOSim;
import frc.robot.Subsystem.Shooter.ShooterSub;
import frc.robot.Subsystem.Shooter.Commands.RunFeeder;
import frc.robot.Subsystem.Shooter.Commands.RunShooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {

    private final PneumaticHub hub = new PneumaticHub(31);
    // Create a new Xbox controller on port 0
    CommandXboxController controller = new CommandXboxController(0);
    DrivetrainSubsystem drivetrainSubsystem;
    MontySubsytem montySubsytem;
    ShooterSub shootersub;
    Intake m_intake;

    //Multiple commands at once
    ParallelCommandGroup runIntake = new ParallelCommandGroup(new RunFeeder(shootersub, -5), new RunShooter(shootersub, -5)); //Run Kitbot intake
    ParallelCommandGroup runBackward = new ParallelCommandGroup(new RunIntake(m_intake, -1), new RunFeed(new m_ShooterSub(), -1)); //Run Monty run feed backwards

    public RobotContainer(boolean isReal) {
        if (isReal) {
            if (Constants.type == RobotType.Kitbot) {
                shootersub = new ShooterSub(new ShooterIONeo());
                drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainIOTalonSRX());
            }

            if (Constants.type == RobotType.Monty) {
                montySubsytem = new MontySubsytem(new DrivetrainIOMonty());
                m_intake = new Intake(hub);
            }
        } else {
            if (Constants.type == RobotType.Kitbot) {
                shootersub = new ShooterSub(new ShooterIOSim());
                drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainIOSim());
            }

            if (Constants.type == RobotType.Monty) {
                montySubsytem = new MontySubsytem(new MontyIOSim());
            }
        }

        configureBindings();
    }

    private void configureBindings() {
        switch (Constants.type) {

            case Kitbot:
                drivetrainSubsystem.setDefaultCommand(
                        drivetrainSubsystem.voltagesArcadeCommand(
                                () -> -controller.getLeftY(),
                                () -> -controller.getRightX()));
                 controller.a().whileTrue(new RunFeeder(shootersub, 12)); // A to run feeder motors
                 controller.leftTrigger(0.5).whileTrue(new RunShooter(shootersub, 12)); // Left trigger to shoot notes
                 controller.b().whileTrue(runIntake);  //When on B runs shooter and feeder motor backwards(Intake motor)
                break;

            case Monty:
                montySubsytem.setDefaultCommand(
                        montySubsytem.ArcadeDrive(
                                () -> -controller.getLeftY(),
                                () -> -controller.getRightX()));
                controller.x().whileTrue(new RunIntake(m_intake, 1));    //Run intake motors
                controller.b().toggleOnTrue(new SetIntakeState(m_intake, true));  //Move the intake down
                controller.rightTrigger(0.5).whileTrue(new RunFeed(new m_ShooterSub(), 1));   //shoot the ball after flyweels are spunt
                controller.y().toggleOnTrue(new RunLaunch(new m_ShooterSub(), 1));  //toggles the flyweels    
                
                controller.leftBumper().whileTrue(runBackward);
                break;
            default:

        }
    }

    public Command getAutonomousCommand() {
        
        NamedCommands.registerCommand("RunShooter", new RunShooter(shootersub, RobotController.getInputVoltage()));
        NamedCommands.registerCommand("RunFeeder", new RunFeeder(shootersub, RobotController.getInputVoltage()));
        
        return new PathPlannerAuto("Soham");
    }
}