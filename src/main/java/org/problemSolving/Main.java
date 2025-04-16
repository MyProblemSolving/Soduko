package org.problemSolving;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.problemSolving.models.Grid;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void process() throws IOException {

        Grid grid = new Grid();

        try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sudoku-9x9-solved-example-0.json")){

            JSONObject obj = new JSONObject(new JSONTokener(in));

            JSONArray jsonArray = obj.getJSONArray("sodukos");

            for(Object squareObject :  jsonArray) {

                JSONObject squareJson = (JSONObject) squareObject;

                int xGrid = squareJson.getInt("x");
                int yGrid = squareJson.getInt("y");
                int[] numbers = squareJson.getJSONArray("numbers").toList().stream().mapToInt(i -> (int) i).toArray();

                for(int i = 0; i < numbers.length; i++) {
                    grid.get(xGrid,yGrid).get(i/3,i%3).number = numbers[i];
                }
            }

        }

//        grid.get(2,1).get(1,2).number = 3;

        System.out.println(grid);
        System.out.println(grid.isValid());

    }

    public static void main(String[] args) throws IOException {

        LocalDateTime start = LocalDateTime.now();
        process();
        LocalDateTime end = LocalDateTime.now();

        System.out.println(Duration.between(start, end));
    }


}