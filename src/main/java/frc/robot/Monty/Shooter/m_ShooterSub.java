package frc.robot.Monty.Shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.Constants;


public class m_ShooterSub {
    TalonFX rightLaunch = new TalonFX(Constants.MontyIntake.RightLaunchRollerID);
    TalonFX leftLaunch = new TalonFX(Constants.MontyIntake.LeftLaunchRollerID);
    TalonSRX feedMe = new TalonSRX(Constants.MontyIntake.FeedRollerID);

    public m_ShooterSub() {
        leftLaunch.getConfigurator().apply(new TalonFXConfiguration());
        rightLaunch.getConfigurator().apply(new TalonFXConfiguration());
        feedMe.configFactoryDefault();

        // Making the right motor follow the left one.
        rightLaunch.setControl(new Follower(leftLaunch.getDeviceID(), true));
    }

    public void setLaunch(double speed){
        leftLaunch.set(speed * Constants.MontyIntake.maxLaunchSpeed);
    }

    public void setFeederSpeed(double speed){
        feedMe.set(ControlMode.PercentOutput, -speed * Constants.MontyIntake.maxFeedSpeed);
    }

    public double getLaunchingSpeed() {
        return(leftLaunch.get() + rightLaunch.get());
    }

    public double getFeedSpeed(double speed){
        return feedMe.getMotorOutputPercent();
    }
}
