// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

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
        public static final double maxLeftSpeed = Units.radiansPerSecondToRotationsPerMinute(2 * Math.PI);  
        public static final double maxRightSpeed = Units.radiansPerSecondToRotationsPerMinute(2 * Math.PI);

    }


    public class MontyIntake {
        public static final int frontRoller = 11;

        public static final int leftIndexxer = 12;
        public static final int rightIndexxer = 13;

        public static final int bottomTrack = 14;

        public static final int LeftLaunchRollerID = 21;
        public static final int RightLaunchRollerID = 22;

        public static final int FeedRollerID = 23;

        public static final double maxIntakeSpeed = 0.6;
        public static final double maxLaunchSpeed = 0.6;

        public static final double maxFeedSpeed = 0.6;

        public static final int intakeSolenoid = 15;
        public static final int feedersolenoid = 7;
        
		public static final double gearRatio = 34;  //34

        public static final double maxRPM = 100;
    }

////////////////KITBOT/////////////////////////////////////////
    // Sparks                                            //////
    public static final int shooterID = 11; //KITBOT    ///////
    public static final int feederID = 12;             ///////
    public static final int maxFeederSpeed = 12;       ///////
    public static final int maxShooterSpeed = 12;       //////
//////////////////////////////////////////////////////////////

    public static final boolean isReplay = true;

    public enum RobotType {
        Monty,
        Kitbot;
    }

    public static final RobotType type = RobotType.Monty;
  
}
