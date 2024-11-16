package frc.robot.Subsystem.Shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {

    @AutoLog
    public static class ShooterData{
    public double ShooterV = 0.0;
    public double FeederV = 0.0;

    public double ShooterVolts = 0.0;
    public double FeederVolts = 0.0;

    public double ShooterOut = 0.0;
    public double FeederOut = 0.0;
    }
    public abstract void setFeederVolts(double volts);
    public abstract void setShooterVolts(double volts);
    public abstract void getData(ShooterData data);
}
