// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Constants {
    public static class DriveConstants {
        public static final double leftP = 0;
        public static final double leftD = 0;
        public static final double leftI = 0;
        public static final double leftFF = 0;
    }
    public enum Modes{
        kSim,
        kReal,
        kReplay;
    }
    public static final Modes state = Modes.kReal;
}
