// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Constants {
    public static class DriveConstants {
        // talons
        public static final int backLeft = 2;
        public static final int frontLeft = 1;
        public static final int backRight = 4;
        public static final int frontRight = 3;

        // Monty SparkMaxes

        public static final int bL = 3;
        public static final int bR = 4;
        public static final int fL = 1;
        public static final int fR = 2;

        // Other Monty Constants
        public static final double maxDriveSpeed = 0.6;
        public static final double maxTurnSpeed = 0.6;
        public static final boolean squareInputs = true;

    }

    // Sparks
    public static final int shooterID = 12;
    public static final int feederID = 11;

    public enum Modes {
        kSim,
        kReal,
        kReplay;
    }

    public enum RobotType {
        Monty,
        Kitbot;
    }

    public enum Control {
        Keyboard,
        Controller,
    }

    public static final RobotType type = RobotType.Monty;
    public static final Modes state = Modes.kSim;
    public static final Control currentMode = Control.Controller;
}
