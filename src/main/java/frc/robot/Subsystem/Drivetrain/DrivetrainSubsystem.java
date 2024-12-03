
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Drivetrain;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.proto.Kinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.math.MathUtil;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.ReplanningConfig;

public class DrivetrainSubsystem extends SubsystemBase {
  DrivetrainIO io;
  DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();
  Field2d field2d = new Field2d();
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(DriveConstants.m_RobotWidth);
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0,
      new Pose2d(0.66, 4.39, new Rotation2d()));

  public DrivetrainSubsystem(DrivetrainIO motors) {
    io = motors;

    AutoBuilder.configureRamsete(
        this::getPose2D,
        this::resetPose2d,
        this::speedSupplier,
        this::setSpeeds,
        new ReplanningConfig(),
        () -> (DriverStation.getAlliance().isPresent()
            && DriverStation.getAlliance().get().equals(Alliance.Blue)),
        this);
  }

  private Pose2d getPose2D() {
    return odometry.getPoseMeters();
  }

  private void resetPose2d(Pose2d pose) {
    field2d.getRobotPose();
    odometry.resetPosition(odometry.getPoseMeters().getRotation(),
        new DifferentialDriveWheelPositions(inputs.leftPositionMeters, inputs.rightPositionMeters), pose);
  }

  private ChassisSpeeds speedSupplier() {
    return kinematics.toChassisSpeeds(new DifferentialDriveWheelSpeeds(inputs.leftVelocityMetersPerSecond,
        inputs.rightVelocityMetersPerSecond));
  }

  private void setSpeeds(ChassisSpeeds chassisSpeeds) {
    kitbotArcade(chassisSpeeds.vxMetersPerSecond, chassisSpeeds.omegaRadiansPerSecond);
  }

  public void setVoltages(double left, double right) {
    io.setVolts(left * RobotController.getInputVoltage(), right * RobotController.getInputVoltage());
  }

  @Override
  public void periodic() {
    inputs.robotPose = odometry.update(
        odometry.getPoseMeters().getRotation()
            // Use differential drive kinematics to find the rotation rate based on the
            // wheel speeds and distance between wheels
            .plus(Rotation2d.fromRadians((inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond)
                * 0.020 / Units.inchesToMeters(26))),
        inputs.leftPositionMeters, inputs.rightPositionMeters);
    io.updateInputs(inputs);
    Logger.processInputs("Drivetrain", inputs);
    Logger.recordOutput("Drivetrain Pose", odometry.getPoseMeters());

  }

  public Command voltagesTankCommand(DoubleSupplier driveSupplier, DoubleSupplier steerSupplier) {
    return new RunCommand(() -> {
      double drive = driveSupplier.getAsDouble();
      double steer = steerSupplier.getAsDouble();
      drive = MathUtil.applyDeadband(drive, 0.1);
      steer = MathUtil.applyDeadband(steer, 0.1);
      double left = drive;
      double right = steer;

      this.setVoltages(left, right);

    }, this);
  }

  public void kitbotArcade(double drive, double steer) {
    drive = MathUtil.applyDeadband(drive, 0.15);
    steer = MathUtil.applyDeadband(steer, 0.15);
    double left = drive - steer;
    double right = drive + steer;

    this.setVoltages(left, right);
  }

  public Command voltagesArcadeCommand(DoubleSupplier driveSupplier, DoubleSupplier steerSupplier) {
    return new RunCommand(() -> {
      this.kitbotArcade(driveSupplier.getAsDouble(), steerSupplier.getAsDouble());
    }, this);
  }
}