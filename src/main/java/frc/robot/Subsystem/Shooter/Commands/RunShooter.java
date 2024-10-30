package frc.robot.Subsystem.Shooter.Commands;

import com.ctre.phoenix6.configs.VoltageConfigs;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Shooter.ShooterSub;

public class RunShooter extends Command {

    ShooterSub shooterSub;
    double feederSpeed;
    double shooterSpeed;

    public RunShooter(ShooterSub shooterSub, double shooterSpeed){
    this.shooterSub = shooterSub;
    this.shooterSpeed = shooterSpeed;
    }

    @Override
    public void execute(){
        shooterSub.setShooterOutput(shooterSpeed);
    }

    @Override
    public void end(boolean interrupted){
        shooterSub.setShooterOutput(0);
    }
}