package org.problemSolving;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.problemSolving.models.Grid9x9;
import org.problemSolving.services.ConvertImageToBlackAndWhite;
import org.problemSolving.services.Grid9x9Solver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws IOException, TesseractException {

        LocalDateTime start = LocalDateTime.now();

        String inputFile = "C:\\Projects\\problem-solving\\soduko\\src\\main\\resources\\sudoku-9x9-unsolved-example-1.png";

        String outputFile = "C:\\Projects\\problem-solving\\soduko\\src\\main\\resources\\sudoku-9x9-unsolved-example-1-black-&-white.png";

        ConvertImageToBlackAndWhite.convert(inputFile, outputFile, "png");

        File file = new File(outputFile);

        Tesseract tesseract = new Tesseract();

        tesseract.setVariable("tessedit_char_whitelist", "123456789");

        tesseract.setDatapath("C:\\Projects\\problem-solving\\soduko\\src\\main\\resources\\traineddata");

        tesseract.setPageSegMode(6);

        tesseract.setOcrEngineMode(1);

        String result = tesseract.doOCR(file);

        System.out.println(result);

//        checkIfGridIsSolved("sudoku-9x9-solved-example-0.json");
//        solveGrid("sudoku-9x9-unsolved-example-0.json");


        LocalDateTime end = LocalDateTime.now();

        System.out.println(Duration.between(start, end));

    }

    public static void solveGrid(String jsonNameFile) throws IOException {

        Grid9x9 grid9x9 = loadGridFromJson(jsonNameFile);

        System.out.println("Grid before solving:");
        System.out.println(grid9x9);

        Grid9x9Solver grid9x9Solver = new Grid9x9Solver();
        grid9x9Solver.solve(grid9x9);

        System.out.println("Grid after solving:");
        System.out.println(grid9x9);

    }

    public static void checkIfGridIsSolved(String jsonNameFile) throws IOException {

        Grid9x9 grid9x9 = loadGridFromJson(jsonNameFile);

        System.out.println(grid9x9);
        System.out.println(grid9x9.isValid());

    }

    public static Grid9x9 loadGridFromJson(String jsonNameFile) throws IOException {

        Grid9x9 grid9x9 = new Grid9x9();

        try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonNameFile)){

            JSONObject obj = new JSONObject(new JSONTokener(in));

            JSONArray jsonArray = obj.getJSONArray("sudoku");

            for(Object squareObject :  jsonArray) {

                JSONObject squareJson = (JSONObject) squareObject;

                int xGrid = squareJson.getInt("x");
                int yGrid = squareJson.getInt("y");
                int[] numbers = squareJson.getJSONArray("numbers").toList().stream().mapToInt(i -> (int) i).toArray();

                for(int i = 0; i < numbers.length; i++) {
                    grid9x9.get(xGrid,yGrid).get(i/3,i%3).number = numbers[i];
                }
            }

        }

        return grid9x9;

    }

}
