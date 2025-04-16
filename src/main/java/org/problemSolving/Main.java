package org.problemSolving;

import org.problemSolving.models.Grid;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void process() {

        Grid grid = new Grid();

        grid.get(2,1).get(0,2).number = 3;

        System.out.println(grid);
        System.out.println(grid.isValid());

    }

    public static void main(String[] args) {

        LocalDateTime start = LocalDateTime.now();
        process();
        LocalDateTime end = LocalDateTime.now();

        System.out.println(Duration.between(start, end));
    }


}