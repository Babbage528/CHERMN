package com.example.chermn.controller;

import com.example.chermn.AlertHelper;

/**
 * A public class that displays the game instructions to the screen for the user.
 */
public class GameInstructionsController
{
    /**
     * Displays a window with instructions and game objectives for the user.
     */
    public static void showGameInstructions() {

        String message =
                """
                        This game contains a variety of exciting trivia categories including Animals, Vehicles, and Science & Nature. \
                        Click on the different icons scattered throughout the homepage to begin each trivia challenge and grow your farm as you progress through the game.
                        
                        Each category contains three different levels that increase in difficulty from Easy to Medium to Hard. \
                        To progress to the next level, you must score at least 80% in the quiz.
                        
                        Every quiz contains 10 multiple-choice questions designed to test your knowledge and help you level up your farm items.
                        
                        As you successfully complete levels, your farm icons will grow and evolve:
                        - The egg will grow into a chicken and finally a chook.
                        - The seedling will grow into a plant and then into fully grown corn.
                        - The hoe will upgrade into a plough and finally into a tractor.
                        
                        You can track your level progress through the icons displayed on the homepage screen or by opening your profile using the profile button on the right-hand side of the screen.
                        
                        Within the profile screen, you can:
                        - View your current level progress
                        - View and update your user details
                        - Change your password
                        - Sign out of the game
                        
                        To customise your game settings, click the Settings button located in the top left-hand corner of the homepage. \
                        Within settings, you can adjust features such as music and text-to-speech options.
                        
                        You can also revisit these game instructions at any time through the settings menu.
                        
                        Good luck, have fun, and see how far you can grow your farm!""";


        AlertHelper.showInstructions("Game Rules", "Farmer Fred's Trivia Application: Instructions",message);


    }


    /**
     * Private constructor of game instruction controller to prevent instantiation of class.
     */
    private GameInstructionsController() {
    }

}
