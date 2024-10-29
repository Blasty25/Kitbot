package frc.robot.Subsystem.Shooter;

public interface ShooterIO {
    public static class ShooterData{
    public double ShooterV = 0.0;
    public double FeederV = 0.0;

    public double ShooterVolts = 0.0;
    public double FeederVolts = 0.0;

    public double ShooterOut = 0.0;
    public double FeederOut = 0.0;
    }
    public abstract void getFeederVolts(double volts);
    public abstract void getShooterVolts(double volts);
    public abstract void getData(ShooterData data);
}
