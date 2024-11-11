package frc.robot.Monty.DrivetrainMonty;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

public class MontySubsytem extends SubsystemBase {
    DrivetrainIOstart io;
    DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0);

    public MontySubsytem(DrivetrainIOstart motor) {
        io = motor;
    }

    public void arcadeDrive(double xSpeed, double turn) {
        io.arcadeDrive(xSpeed, turn);
    } 


    @Override
    public void periodic() {
        inputs.robotPose = odometry.update(
                odometry.getPoseMeters().getRotation()
                        // Use differential drive kinematics to find the rotation rate based on the
                        // wheel speeds and distance between wheels
                        .plus(Rotation2d
                                .fromRadians((inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond)
                                        * 0.020 / Units.inchesToMeters(26))),
                inputs.leftPositionMeters, inputs.rightPositionMeters);
        io.updateInputs(inputs);
        Logger.processInputs("Drivetrain", inputs);
        Logger.recordOutput("Drivetrain Pose", odometry.getPoseMeters());

    }

        public Command ArcadeDrive(DoubleSupplier xSpeedDoubleSupplier, DoubleSupplier zRotationSupplier) {
        return new RunCommand(() -> {
            double Speed = xSpeedDoubleSupplier.getAsDouble();
            double Rotation = zRotationSupplier.getAsDouble();
            Speed = MathUtil.applyDeadband(Speed, 0.15);
            Rotation = MathUtil.applyDeadband(Rotation, 0.15);
            
            this.arcadeDrive(Speed, Rotation);
        }, this);
    }

}
