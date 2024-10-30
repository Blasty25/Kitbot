package frc.robot.Subsystem.Shooter.Commands;

import com.ctre.phoenix6.configs.VoltageConfigs;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Shooter.ShooterSub;

public class RunShooter extends Command {

    ShooterSub shooterSub;
    double feederSpeed;

    public RunShooter(ShooterSub shooterSub, double feederSpeed){
    this.shooterSub = shooterSub;
    this.feederSpeed = feederSpeed;
    }

    @Override
    public void execute(){
        shooterSub.setShooterOutput(feederSpeed);
    }

    @Override
    public void end(boolean interrupted){
        shooterSub.setShooterOutput(0);
    }
}