package frc.robot.Monty.DrivetrainMonty;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
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

public class MontySubsytem extends SubsystemBase {
    DrivetrainIOstart io;
    DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();

    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0,
            new Pose2d(0.66, 4.39, new Rotation2d()));

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(DriveConstants.m_RobotWidth);

    private Field2d field = new Field2d();

    public MontySubsytem(DrivetrainIOstart sim) {
        io = sim;
        // Auto for Monty(Config)
        AutoBuilder.configureRamsete(
                this::getPose,
                this::setPose,
                this::getSpeeds,
                this::drive,
                new ReplanningConfig(),
                () -> (DriverStation.getAlliance().isPresent()
                        && DriverStation.getAlliance().get().equals(Alliance.Blue)),
                this);
    }

    public void arcadeDrive(double speed, double rotation) { // Real Implemntation
        speed = MathUtil.applyDeadband(speed, 0.15);
        rotation = MathUtil.applyDeadband(rotation, 0.15);
        double left = speed + rotation;
        double right = speed - rotation;


        io.arcadeDrive(left, right);
    }

    @Override
    public void periodic() {
        inputs.robotPose = odometry.update(
                odometry.getPoseMeters().getRotation()
                        // Use differential drive kinematics to find the rotation rate based on the
                        .plus(Rotation2d
                                .fromRadians((inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond)
                                        * 0.020 / Units.inchesToMeters(26))),  //26
                inputs.leftPositionMeters, inputs.rightPositionMeters);
        io.updateInputs(inputs);
        Logger.processInputs("Drivetrain", inputs);
        Logger.recordOutput("Drivetrain Pose", odometry.getPoseMeters());
        field.setRobotPose(odometry.getPoseMeters());

    }

    public Pose2d getPose() {  //PathPlanner(Auto)
        return odometry.getPoseMeters();
    }

    public void setPose(Pose2d pose2d) {  //PathPlanner(Auto)
        field.setRobotPose(pose2d);
        odometry.resetPosition(odometry.getPoseMeters().getRotation(),
                new DifferentialDriveWheelPositions(inputs.leftPositionMeters, inputs.rightPositionMeters), pose2d);
    }

    public ChassisSpeeds getSpeeds() {  //PathPlanner(Auto)
        return kinematics.toChassisSpeeds(new DifferentialDriveWheelSpeeds(inputs.leftVelocityMetersPerSecond,
                inputs.rightVelocityMetersPerSecond));
    }

    public void drive(ChassisSpeeds chassisSpeeds) { // PathPlanner(Autos)
        arcadeDrive(chassisSpeeds.vxMetersPerSecond, chassisSpeeds.omegaRadiansPerSecond);
    }

    public Command ArcadeDrive(DoubleSupplier xSpeedDoubleSupplier, DoubleSupplier zRotationSupplier) {
        return new RunCommand(() -> {
            this.arcadeDrive(xSpeedDoubleSupplier.getAsDouble(), zRotationSupplier.getAsDouble());
        }, this);
    }

}
