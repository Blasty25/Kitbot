package frc.robot.Subsystem.Shooter;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.controls.VoltageOut;

import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import frc.robot.Constants;

public class ShooterIOSim implements ShooterIO {
    TalonSRX ShooterM;
    TalonSRX FeederM;

    VoltageOut ShooterV;
    VoltageOut FeederV;

        DifferentialDrivetrainSim physicsSim = DifferentialDrivetrainSim.createKitbotSim(
            KitbotMotor.kDoubleNEOPerSide,
            KitbotGearing.k8p45,
            KitbotWheelSize.kSixInch,
            null);

    public ShooterIOSim() {
        
        ShooterM = new TalonSRX(Constants.shooterID);
        FeederM = new TalonSRX(Constants.feederID);

        ShooterM.setInverted(true);
        FeederM.setInverted(true);
    }

    @Override
    public void updateInputs(ShooterData shooterData) {

        physicsSim.update(0.020);
        shooterData.FeederOut = FeederM.getMotorOutputVoltage();
        shooterData.ShooterOut = ShooterM.getMotorOutputVoltage();

        shooterData.feederCurrent = FeederM.getSupplyCurrent();
        shooterData.shooterCurrent = ShooterM.getSupplyCurrent();

        shooterData.feederTemp = FeederM.getTemperature();
        shooterData.shooterTem = ShooterM.getTemperature();
    }

    @Override
    public void setFeederVolts(double volts) {
        FeederM.set(ControlMode.PercentOutput, volts);
        Logger.recordOutput("Feed Volts", volts);
    }

    @Override
    public void setShooterVolts(double volts) {
        ShooterM.set(ControlMode.PercentOutput, volts);
        Logger.recordOutput("Shooter Volts", volts);
    }

}
