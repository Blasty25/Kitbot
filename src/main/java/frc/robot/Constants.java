// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Constants {
    public static class DriveConstants {
        //talons
        public static final int backLeft = 2;
        public static final int frontLeft = 1;
        public static final int backRight = 4;
        public static final int frontRight = 3;

        //Sparks
       public static final int shooterID = 12;
       public static final int feederID = 11; 
    }
    public enum Modes{
        kSim,
        kReal,
        kReplay;
    }
    public static final Modes state = Modes.kSim;
}
