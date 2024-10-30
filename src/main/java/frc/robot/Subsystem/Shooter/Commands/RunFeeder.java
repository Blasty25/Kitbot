package frc.robot.Subsystem.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Shooter.ShooterSub;

public class RunFeeder extends Command {

    ShooterSub shooterSub;
    double feederSpeed;

    public RunFeeder(ShooterSub shooterSub, double feederSpeed){
    this.shooterSub = shooterSub;
    this.feederSpeed = feederSpeed;
    }

    public void execute(){
        
    }
}