// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem.Drivetrain;

import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

public class DrivetrainIOSim implements DrivetrainIO {

    DifferentialDrivetrainSim physicsSim = DifferentialDrivetrainSim.createKitbotSim(
            KitbotMotor.kDoubleFalcon500PerSide,
            KitbotGearing.k8p45,
            KitbotWheelSize.kSixInch,
            null);
    public static double leftVolts, rightVolts;



    @Override
    public void updateInputs(DrivetrainIOInputs inputs) 
    {
        //Add Velocity Position temperature current amps volts simstate?
        physicsSim.update(0.020);
        
        inputs.leftCurrentAmps = new double[]{physicsSim.getLeftCurrentDrawAmps()};
        inputs.leftOutputVolts = leftVolts;
        inputs.leftPositionMeters = physicsSim.getLeftPositionMeters();
        inputs.leftVelocityMetersPerSecond = physicsSim.getLeftVelocityMetersPerSecond();
        inputs.rightCurrentAmps = new double[]{physicsSim.getRightCurrentDrawAmps()};
        inputs.rightOutputVolts = rightVolts;
        inputs.rightPositionMeters = physicsSim.getRightPositionMeters();
        inputs.rightVelocityMetersPerSecond = physicsSim.getRightVelocityMetersPerSecond();

    }


    @Override
    public void setVolts(double left, double right) {
        physicsSim.setInputs(left, right);
        leftVolts = left;
        rightVolts = right;
        
    }
}