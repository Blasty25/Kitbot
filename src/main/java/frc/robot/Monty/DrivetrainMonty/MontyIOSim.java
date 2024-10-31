package frc.robot.Monty.DrivetrainMonty;

import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

public class MontyIOSim implements DrivetrainIOstart {
    DifferentialDrivetrainSim physicsSim = DifferentialDrivetrainSim.createKitbotSim(
            KitbotMotor.kDoubleNEOPerSide,
            KitbotGearing.k8p45,
            KitbotWheelSize.kSixInch,
            null);
        public static double leftVolts, rightVolts;



    @Override
    public void updateInputs(DrivetrainIOInputs inputs) 
    {
        physicsSim.update(0.020);
        
        inputs.leftCurrentAmps = new double[]{physicsSim.getLeftCurrentDrawAmps()};
        inputs.leftPositionMeters = physicsSim.getLeftPositionMeters();
        inputs.leftOutputVolts = leftVolts;
        inputs.leftVelocityMetersPerSecond = physicsSim.getLeftVelocityMetersPerSecond();
        inputs.rightCurrentAmps = new double[]{physicsSim.getRightCurrentDrawAmps()};
        inputs.rightPositionMeters = physicsSim.getRightPositionMeters();
        inputs.rightOutputVolts = rightVolts;
        inputs.rightVelocityMetersPerSecond = physicsSim.getRightVelocityMetersPerSecond();

    }


    @Override
    public void arcadeDrive(double xSpeed, double turn) {
        physicsSim.setInputs(xSpeed, turn);
        leftVolts = xSpeed;
        rightVolts = turn;
        
    }

    
}
