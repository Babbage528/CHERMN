package com.example.chermn.controller;

import com.example.chermn.model.Users;
import com.example.chermn.OnBoarding;
import com.example.chermn.model.Student;
import com.example.chermn.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;


public class HomepageController {

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
    private Student currentUser;

    // assign the user passed through the controller to assignedUser
    public void setCurrentUser(Users user) {
        // check whether the user is a student
        if (user instanceof Student student) {
            this.currentUser = student;
        }
        else {
            throw new IllegalArgumentException("Homepage requires a Student user");
        }
        // pulls the current user's stats/levels for the images
        displayLevelImages();
    }

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


    // defining the buttons used in the homepage screen
    @FXML
    private Button animalButton;
    @FXML
    public Button vehicleButton;
    @FXML
    private Button farmHouseButton;
    @FXML
    private Button cornButton;
    @FXML
    private Button settingsButton;



    // defining the associated actions associated with the above button variables

    @FXML
    protected void animalButtonClick() throws IOException{
        Stage stage = (Stage) animalButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-begin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    protected void vehicleButtonClick() throws IOException {
        Stage stage = (Stage) vehicleButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-begin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void farmHouseButtonClick() throws IOException {
        Stage stage = (Stage) farmHouseButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-begin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void settingsButtonClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("settings-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void profileButtonClick(ActionEvent actionEvent) {
    }


    @FXML
    protected void cornButtonClick() throws IOException {
        Stage stage = (Stage) cornButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(OnBoarding.class.getResource("quiz-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), OnBoarding.WIDTH, OnBoarding.HEIGHT);
        stage.setScene(scene);
    }

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
