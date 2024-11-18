package com.jmc.widget;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final String API_KEY = "a6b219982c18740ee3a3bc3afeee16ee";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String Location = "Paris";
    public Label title;
    public Label weatherLbl;
    public Label descLbl;
    public Label tempLbl;
    public ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText("Weather In " + Location + ":");
        try {
            retrieveWeather();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void retrieveWeather() throws IOException, URISyntaxException {
        String restURL = BASE_URL +"?q=" +Location+ "&appid=" + API_KEY;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Model model = objectMapper.readValue(new URL(restURL), Model.class);
        updateModel(model);
    }
    private void updateModel(Model model) throws MalformedURLException, URISyntaxException {
        if (model != null) {
            if (!model.getWeather().isEmpty()){
                Weather w = model.getWeather().get(0);
                imageView.setImage(new Image(new URL("http://openweathermap.org/img/wn/" + w.getIcon() + "@2x.png").toURI().toString()));
                weatherLbl.setText(w.getMain());
                descLbl.setText(w.getDescription());
            }
            tempLbl.setText(String.format("%.2f ℃ - %.1f%%", model.getMain().getTemp() - 273.15, model.getMain().getHumidity()));
        }
    }
}



























