package com.example.chermn.controller;

import com.example.chermn.AlertHelper;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * A public class that displays the game instructions to the screen for the user.
 */
public class GameInstuctionsController
{
    /**
     * Displays a window with instructions and game objectives for the user.
     */
    public static void showGameInstructions() {

        String message =
                "This game contains a variety of exciting trivia categories including Animals, Vehicles, and Science & Nature. " +
                        "Click on the different icons scattered throughout the homepage to begin each trivia challenge and grow your farm as you progress through the game.\n\n" +

                        "Each category contains three different levels that increase in difficulty from Easy to Medium to Hard. " +
                        "To progress to the next level, you must score at least 80% in the quiz.\n\n" +

                        "Every quiz contains 10 multiple-choice questions designed to test your knowledge and help you level up your farm items.\n\n" +

                        "As you successfully complete levels, your farm icons will grow and evolve:\n" +
                        "• The egg will grow into a chicken and finally a chook.\n" +
                        "• The seedling will grow into a plant and then into fully grown corn.\n" +
                        "• The hoe will upgrade into a plough and finally into a tractor.\n\n" +

                        "You can track your level progress through the icons displayed on the homepage screen or by opening your profile using the profile button on the right-hand side of the screen.\n\n" +

                        "Within the profile screen, you can:\n" +
                        "• View your current level progress\n" +
                        "• View and update your user details\n" +
                        "• Change your password\n" +
                        "• Sign out of the game\n\n" +

                        "To customise your game settings, click the Settings button located in the top left-hand corner of the homepage. " +
                        "Within settings, you can adjust features such as music and text-to-speech options.\n\n" +

                        "You can also revisit these game instructions at any time through the settings menu.\n\n" +

                        "Good luck, have fun, and see how far you can grow your farm!";


        AlertHelper.showInstructions("Game Rules", "Farmer Fred's Trivia Application: Instructions",message);


    }

}
