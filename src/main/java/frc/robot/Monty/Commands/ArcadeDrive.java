package frc.robot.Monty.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Monty.DrivetrainMonty.DrivetrainIOMonty;

/** An example command that uses an example subsystem. */
public class ArcadeDrive extends Command {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final DrivetrainIOMonty drivetrainIOMonty;
    private final DoubleSupplier xSpeedSupplier;
    private final DoubleSupplier zRotationSupplier;

    /**
     * Creates a new ArcadeDrive command.
     * This command uses the arcadeDrive function of the Drivetrain class to drive
     * the robot in all sorts of directions.
     */
    public ArcadeDrive(DrivetrainIOMonty drivetrain, DoubleSupplier xSpeedSupplier, DoubleSupplier zRotationSupplier) {
        this.drivetrainIOMonty = drivetrain;
        this.xSpeedSupplier = xSpeedSupplier;
        this.zRotationSupplier = zRotationSupplier;
        //addRequirements(drivetrain);
    }

    @Override
    /** Runs every cycle. Contains the code that the command will execute. */
    public void execute() {
        drivetrainIOMonty.arcadeDrive(xSpeedSupplier.getAsDouble(), zRotationSupplier.getAsDouble());
    }
}