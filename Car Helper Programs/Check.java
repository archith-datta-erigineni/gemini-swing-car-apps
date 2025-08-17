package com.example;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Check {
    String make;
    String model;
    String year;

    Check(String make, String model, String year) {
        this.make=make;
        this.model=model;
        this.year=year;
    }

    public boolean verifyModelYear() {
        try {
            String apiUrl = String.format(
                "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear/make/%s/modelyear/%s?format=json",
                make, year
            );
            @SuppressWarnings("deprecation")
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                @SuppressWarnings("deprecation")
                com.google.gson.JsonObject jsonObject = new com.google.gson.JsonParser().parse(response.toString()).getAsJsonObject();
                com.google.gson.JsonArray results = jsonObject.getAsJsonArray("Results");
                boolean found = false;
                for (int i = 0; i < results.size(); i++) {
                    com.google.gson.JsonObject obj = results.get(i).getAsJsonObject();
                    String modelName = obj.get("Model_Name").getAsString();
                    if (modelName.equalsIgnoreCase(model)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("Valid combination: " + make + " " + model + " " + year);
                    return true;
                } else {
                    System.out.println("Invalid combination: " + make + " " + model + " " + year);
                    return false;
                }
            } else {
                System.out.println("API request failed.");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
