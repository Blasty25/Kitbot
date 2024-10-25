package frc.robot.Subsystem.Drivetrain;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DrivetrainIOsparkmax implements DrivetrainIO{
   CANSparkMax frontLeft;
   CANSparkMax frontRight;
   CANSparkMax backRight;
   CANSparkMax backLeft;
   public DrivetrainIOsparkmax(){
      frontLeft = new CANSparkMax(1, MotorType.kBrushless);
      frontRight = new CANSparkMax(3,MotorType.kBrushless);
      backRight = new CANSparkMax(4, MotorType.kBrushless);
      backLeft = new CANSparkMax(2, MotorType.kBrushless);
      frontLeft.setInverted(true);
      backLeft.setInverted(true);
   }
   
   public void setVolts(double left, double right){
      
   }

   @Override
   public void updateInputs(DrivetrainIOInputs inputs) {
      return;
   }

}
