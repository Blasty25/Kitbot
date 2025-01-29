//YAP YAP YAP YIPPE
//YAP YAP YAP YIPPEE V2

package frc.robot;

import edu.wpi.first.math.util.Units;
import frc.robot.Monty.DrivetrainMonty.MontySubsytem;

/** Add your docs here. */
public class Constants {
    // public static class Kitbot {}

    public static class DriveConstants {
        // talons
        public static final int backLeft = 3;
        public static final int frontLeft = 1;
        public static final int backRight = 4;
        public static final int frontRight = 2;

        // Monty SparkMaxes

        public static final int bL = 3;
        public static final int bR = 4;
        public static final int fL = 1;
        public static final int fR = 2;

        public static final double m_RobotWidth = Units.inchesToMeters(15);

        // Other Monty Constants
        public static final double maxSpeed = 0.4;

        public static final double driveSpeed = maxSpeed * (Units.radiansPerSecondToRotationsPerMinute(2 * Math.PI));

    }

    public class MontyIntake {
        public static final int frontRoller = 11;

        public static final int leftIndexxer = 12;
        public static final int rightIndexxer = 13;

        public static final int bottomTrack = 14;

        public static final int LeftLaunchRollerID = 21;
        public static final int RightLaunchRollerID = 22;

        public static final int FeedRollerID = 23;

        public static final double maxIntakeSpeed = 0.5;
        public static final double maxLaunchSpeed = 0.6;

        public static final double maxFeedSpeed = 0.5;

        public static final int intakeSolenoid = 15;
        public static final int feedersolenoid = 7;

        public static final double gearRatio = 34; // 34

        public static final double maxRPM = 100;
    }

    //////////////// KITBOT/////////////////////////////////////////
    // Sparks                                                 ////// 
    public static final int shooterID = 11;                  ///////
    public static final int feederID = 12;                  ///////   Change if this doesn't work
    public static final double maxFeederSpeed = 1;//Set from 0-1///////
    public static final double maxShooterSpeed = 1; //Set from 0-1//////   
    //////////////////////////////////////////////////////////////

    public static final boolean isReplay = false;

    public enum RobotType {
        Monty,
        Kitbot;
    }

    public static final RobotType type = RobotType.Kitbot; 

} 
//Me just adding fun comments all around the my code! go to Monty Subsystem to read more