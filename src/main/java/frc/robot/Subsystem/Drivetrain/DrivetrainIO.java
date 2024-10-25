package frc.robot.Subsystem.Drivetrain;


import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Pose2d;

public interface DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        public Pose2d robotPose = new Pose2d();
        public double leftOutputVolts = 0.0;
        public double rightOutputVolts = 0.0;

        public double leftVelocityMetersPerSecond = 0.0;
        public double rightVelocityMetersPerSecond = 0.0;

        public double leftPositionMeters = 0.0;
        public double rightPositionMeters = 0.0;

        public double[] leftCurrentAmps = new double[0];
        public double[] leftTempCelsius = new double[0];
        public double[] rightCurrentAmps = new double[0];
        public double[] rightTempCelsius = new double[0];
    }

    public abstract void updateInputs(DrivetrainIOInputs inputs);

    public abstract void setVolts(double left, double right);
}