package frc.robot.Monty.Intake.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Monty.Intake.Intake;

public class SetIntakeState extends Command {
    public final Intake intake;
    public final boolean state;

    public SetIntakeState(Intake intake, boolean state) {
        this.intake = intake;
        this.state = state;
    }

    @Override
    public void execute() {
        intake.setState(state);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(false);
    }
}