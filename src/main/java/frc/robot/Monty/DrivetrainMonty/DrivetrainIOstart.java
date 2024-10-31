package frc.robot.Monty.DrivetrainMonty;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Pose2d;

public interface DrivetrainIOstart {
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
        public double[] rightCurrentAmps = new double[0];
    }



    public void updateInputs(DrivetrainIOInputs inputs);
    public void arcadeDrive(double xSpeed, double turn);

}