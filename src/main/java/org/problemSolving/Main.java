package org.problemSolving;

import org.problemSolving.models.Number;
import org.problemSolving.models.Square;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void process() {

        Number[][] data = {
                {new Number(1),new Number(2),new Number(3)},
                {new Number(4),new Number(5),new Number(6)},
                {new Number(9),new Number(8),new Number(7)}
        };

        Square square = new Square(data);

        System.out.println(square.isValid());

    }

    public static void main(String[] args) {

        LocalDateTime start = LocalDateTime.now();
        process();
        LocalDateTime end = LocalDateTime.now();

        System.out.println(Duration.between(start, end));
    }


}