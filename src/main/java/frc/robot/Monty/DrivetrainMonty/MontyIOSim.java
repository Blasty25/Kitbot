// package frc.robot.Monty.DrivetrainMonty;


// import edu.wpi.first.math.system.plant.DCMotor;
// import edu.wpi.first.math.util.Units;
// import edu.wpi.first.wpilibj.RobotController;
// import edu.wpi.first.wpilibj.simulation.DCMotorSim;
// import frc.robot.Constants;
// import frc.robot.Constants.DriveConstants;
// import frc.robot.Constants.MontyIntake;

// public class MontyIOSim implements DrivetrainIOstart {
//     DCMotorSim left_Motor = new DCMotorSim(DCMotor.getNEO(2), Constants.MontyIntake.gearRatio, 0.02);
//     DCMotorSim right_Motor = new DCMotorSim(DCMotor.getNEO(2), Constants.MontyIntake.gearRatio, 0.02);

//     @Override
//     public void updateInputs(DrivetrainIOInputs inputs) {
//         left_Motor.update(0.020);
//         right_Motor.update(0.020);

//         inputs.leftCurrentAmps = new double[] { left_Motor.getCurrentDrawAmps() };
//         inputs.leftPositionMeters = left_Motor.getAngularPositionRotations();
//         inputs.leftOutputVolts = left_Motor.getAngularVelocityRPM() / MontyIntake.maxRPM * RobotController.getInputVoltage();
//         inputs.leftVelocityMetersPerSecond = Units.radiansPerSecondToRotationsPerMinute(
//                 left_Motor.getAngularVelocityRPM() * MontyIntake.gearRatio) * Units.inchesToMeters(2);

//         inputs.rightCurrentAmps = new double[] { right_Motor.getCurrentDrawAmps() };
//         inputs.rightPositionMeters = right_Motor.getAngularPositionRotations();
//         inputs.rightOutputVolts = right_Motor.getAngularVelocityRPM() / MontyIntake.maxRPM * RobotController.getInputVoltage();
//         inputs.rightVelocityMetersPerSecond = Units.radiansPerSecondToRotationsPerMinute( right_Motor.getAngularVelocityRPM() * MontyIntake.gearRatio) * Units.inchesToMeters(2);

//     }

//     @Override
//     public void arcadeDrive(double left, double right) {
//         left_Motor.setState(left_Motor.getAngularPositionRad(), left);
//         right_Motor.setState(right_Motor.getAngularPositionRad(), right);
//     }
// }