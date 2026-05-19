package com.example.chermn.controller;

import com.example.chermn.model.Users;
import com.example.chermn.OnBoarding;
import com.example.chermn.Session;
import com.example.chermn.model.Student;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * Controller for the user's homepage screen.
 * Handles navigating between different screen stemming off the homepage.
 * Handles loading the different images corresponding to the user's current
 * level into the image views.
 */
public class HomepageController extends BaseController {

    // defining the buttons used in the homepage screen
    @FXML
    private Button animalButton;
    @FXML
    public Button vehicleButton;
    @FXML
    private Button cornButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button profileButton;

    // creating image views to allow swapping correlating images in and out
    @FXML
    private ImageView vehicleImageView;
    @FXML
    private ImageView natureImageView1;
    @FXML
    private ImageView natureImageView2;
    @FXML
    private ImageView natureImageView3;
    @FXML
    private ImageView natureImageView4;
    @FXML
    private ImageView animalImageView;

    // defining variable for current logged-in user
    private static Student currentUser;
    private Users user = Session.getCurrentUser();
    private static int categorySelection;
    private static String difficultySelection;

    // defining the different strings required for each image level
    // Vehicle Icon Url
    // level 1 - hoe
    String vehicle_level1 = "/com/example/chermn/assets/images/hoe_Level1.png";
    // level 2 - plow
    String vehicle_Level2 = "/com/example/chermn/assets/images/plow_Level2.png";
    // level 3 - tractor
    String vehicle_Level3 = "/com/example/chermn/assets/images/tractor_Level3.png";

    // Animal Icon Url
    // level 1 - egg
    String animal_level1 = "/com/example/chermn/assets/images/chicken_Level1.png";
    // level 2 - baby chicken
    String animal_level2 = "/com/example/chermn/assets/images/chicken_Level2.png";
    // level 3 - chicken
    String animal_level3 = "/com/example/chermn/assets/images/chicken_Level3.png";

    // Nature Icon Url
    // level 1 - corn seedling
    String nature_Level1 = "/com/example/chermn/assets/images/corn_Level1.png";
    // level 2 - baby corn
    String nature_Level2 = "/com/example/chermn/assets/images/corn_Level2.png";
    // level 3 - adult corn
    String nature_Level3 = "/com/example/chermn/assets/images/corn_Level3.png";


    /**
     * Returns the category that the user has selected.
     * @return the user's selected category.
     */
    public static int getCategorySelection() {
        return categorySelection;
    }

    /**
     * Gets the difficulty of the selected category.
     * @return the difficulty level of the user's selected category.
     */
    public static String getDifficultySelection() {
        return difficultySelection;
    }


    /**
     * Assigns the current user to the one logged in through settings.
     * @throws IllegalArgumentException if the user isn't a Student
     */
    public void setCurrentUser() {
        // check whether the user is a student
        if (user instanceof Student student) {
            this.currentUser = student;
        }
        else {
            throw new IllegalArgumentException("Homepage requires a Student user");
        }

    }

    /**
     * Initialises the controller before the screen is displayed.
     * Displays level images based on the current user's stored data.
     */
    public void initialize() {
        setCurrentUser();
        // pulls the current user's stats/levels for the images
        displayLevelImages();

        addHoverBorder(animalButton);
        addHoverBorder(vehicleButton);
        addHoverBorder(cornButton);
        addHoverBorder(settingsButton);
        addHoverBorder(profileButton);

        // waits until the screen has rendered before showing instructions
        Platform.runLater(() -> {
            GameInstuctionsController.showGameInstructions();
        });
    }

    private void addHoverBorder(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-border-color: #4A90E2;" +
                            "-fx-border-width: 3;" +
                            "-fx-background-color: transparent;" +
                            "-fx-border-radius: 8;" +
                            "-fx-background-radius: 8;"
            );
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent;");
        });
    }

    /**
     * Handles the animal button click event.
     * Loads the quiz begin screen and corresponding controller.
     * Sets the difficulty text of the quiz begin screen based off the user's data.
     * @throws IllegalArgumentException if the quiz screen couldn't be loaded
     */
    @FXML
    protected void animalButtonClick() {
        try {
            categorySelection = 1;
            Stage stage = (Stage) animalButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-begin.fxml"));
            Parent root = fxmlLoader.load();
            QuizBeginController quizController = fxmlLoader.getController();
            quizController.setCategoryText("Category: Animals");
            if (getQuizLevel("Animal") == 1) {
                quizController.setDifficultyText("Difficulty: Easy");
                difficultySelection = "Easy";
            }
            else if (getQuizLevel("Animal") == 2) {
                quizController.setDifficultyText("Difficulty: Medium");
                difficultySelection = "Medium";
            }
            else if (getQuizLevel("Animal") == 3) {
                quizController.setDifficultyText("Difficulty: Hard");
                difficultySelection = "Hard";
            }
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
        }
        // otherwise catches any exceptions thrown
        catch (IllegalArgumentException | IOException exception)
        {
            throw new IllegalArgumentException(exception);
        }

    }


    /**
     * Handles the vehicle button click event.
     * Loads the quiz begin screen and corresponding controller.
     * Sets the difficulty text of the quiz begin screen based off the user's data.
     * @throws IllegalArgumentException if the quiz screen couldn't be loaded
     */
    @FXML
    protected void vehicleButtonClick() {
        try {
            categorySelection = 2;
            Stage stage = (Stage) vehicleButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-begin.fxml"));
            Parent root = fxmlLoader.load();
            QuizBeginController quizController = fxmlLoader.getController();
            quizController.setCategoryText("Category: Vehicles");
            if (getQuizLevel("Vehicle") == 1) {
                quizController.setDifficultyText("Difficulty: Easy");
                difficultySelection = "Easy";
            } else if (getQuizLevel("Vehicle") == 2) {
                quizController.setDifficultyText("Difficulty: Medium");
                difficultySelection = "Medium";
            } else if (getQuizLevel("Vehicle") == 3) {
                quizController.setDifficultyText("Difficulty: Hard");
                difficultySelection = "Hard";
            }
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
        }
        // otherwise catches any exceptions thrown
        catch (IllegalArgumentException | IOException exception)
        {
            throw new IllegalArgumentException(exception);
        }
    }

    /**
     * Handles the settings button click event.
     * Loads the generic settings screen and corresponding controller.
     * @throws IllegalArgumentException if the settings screen couldn't be loaded
     */
    @FXML
    protected void settingsButtonClick() {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("settings-selection.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
        }
        // otherwise catches any exceptions thrown
        catch (IllegalArgumentException | IOException exception)
        {
            throw new IllegalArgumentException(exception);
        }
    }

    /**
     * Handles the profile button click event.
     * Loads the user's profile screen and corresponding controller.
     * @throws IllegalArgumentException if the profile screen couldn't be loaded
     */
    @FXML
    protected void profileButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("profile-screen.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Handles the corn button click event.
     * Loads the quiz begin screen and corresponding controller.
     * Sets the difficulty text of the quiz begin screen based off the user's data.
     * @throws IllegalArgumentException if the quiz screen couldn't be loaded
     */
    @FXML
    protected void cornButtonClick() {
        try {
            categorySelection = 3;
            Stage stage = (Stage) cornButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-begin.fxml"));
            Parent root = fxmlLoader.load();
            QuizBeginController quizController = fxmlLoader.getController();
            quizController.setCategoryText("Category: Science and Nature");
            if (getQuizLevel("Nature") == 1) {
                quizController.setDifficultyText("Difficulty: Easy");
                difficultySelection = "Easy";
            } else if (getQuizLevel("Nature") == 2) {
                quizController.setDifficultyText("Difficulty: Medium");
                difficultySelection = "Medium";
            } else if (getQuizLevel("Nature") == 3) {
                quizController.setDifficultyText("Difficulty: Hard");
                difficultySelection = "Hard";
            }
            Scene scene = new Scene(root, OnBoarding.WIDTH, OnBoarding.HEIGHT);
            stage.setScene(scene);
        }
        // otherwise catches any exceptions thrown
        catch (IllegalArgumentException | IOException exception)
        {
            throw new IllegalArgumentException(exception);
        }
    }

    /** Returns the current user's quiz level for a specified category.
     * @param levelType the category whose level should be retrieved
     * @return the user's level for the specified category
     * @throws IllegalArgumentException if the category doesn't exist
     */
    public static int getQuizLevel(String levelType) {
        try {
            String methodName = "get" + levelType + "Level";
            Method method = currentUser.getClass().getMethod(methodName);
            return (int) method.invoke(currentUser);
        }
        catch (Exception exception) {
            throw new IllegalArgumentException("Invalid quiz level type: " + levelType);
        }

    }

    /**
     * Decides the vehicle URL based on the current user's stored
     * vehicle level in the database.
     * @return the vehicle image URL
     * @throws IllegalArgumentException if the inputted vehicle level is invalid
     */
    private String getVehicleURL() {
        switch(currentUser.getVehicleLevel()) {
            case 1:
                return vehicle_level1;
            case 2:
                return vehicle_Level2;
            case 3:
                return vehicle_Level3;
            default:
                throw new IllegalArgumentException("Students level wasn't set to 1, 2, or 3");
        }
    }

    /**
     * Decides the animal URL based on the current user's stored
     * animal level in the database.
     * @return the animal image URL
     * @throws IllegalArgumentException if the inputted animal level is invalid
     */
    private String getAnimalURL() {
        switch(currentUser.getAnimalLevel()) {
            case 1:
                return animal_level1;
            case 2:
                return animal_level2;
            case 3:
                return animal_level3;
            default:
                throw new IllegalArgumentException("Students level wasn't set to 1, 2, or 3");
        }
    }

    /**
     * Decides the nature URL based on the current user's stored
     * nature level in the database.
     * @return the nature image URL
     * @throws IllegalArgumentException if the inputted nature level is invalid
     */
    private String getNatureURL() {
        switch(currentUser.getNatureLevel()) {
            case 1:
                return nature_Level1;
            case 2:
                return nature_Level2;
            case 3:
                return nature_Level3;
            default:
                throw new IllegalArgumentException("Students level wasn't set to 1, 2, or 3");
        }
    }

    /**
     * Displays the user's current level images for each category.
     * Changes the size of the vehicle image depending on the level.
     */
    @FXML
    protected void displayLevelImages() {
        String animal_url = getAnimalURL();
        String vehicle_url = getVehicleURL();
        String nature_url = getNatureURL();

        // sets the corn image to use multiple times
        Image cornImage = new Image(getClass().getResource(nature_url).toExternalForm());

        // sets the size of the vehicle depending on the image
        int level = currentUser.getVehicleLevel();
        if (level == 3) {
            vehicleImageView.setFitWidth(275); // big one (tractor)
            vehicleImageView.setFitHeight(275);
        }
        else if (level == 2){
            vehicleImageView.setFitWidth(220); // smaller one (plow)
            vehicleImageView.setFitHeight(220);
        }
        else {
            vehicleImageView.setFitWidth(180); // smaller one (hoe)
            vehicleImageView.setFitHeight(180);
        }


        // sets the corresponding image view to the url listed above
        // toExternalForm() constructs a string representation of the url
        animalImageView.setImage(new Image(getClass().getResource(animal_url).toExternalForm()));
        vehicleImageView.setImage(new Image(getClass().getResource(vehicle_url).toExternalForm()));
        natureImageView1.setImage(cornImage);
        natureImageView2.setImage(cornImage);
        natureImageView3.setImage(cornImage);
        natureImageView4.setImage(cornImage);
    }


}
