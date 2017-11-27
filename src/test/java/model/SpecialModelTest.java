package model;

import org.junit.Test;
import project.models.Cell;
import project.models.Model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class SpecialModelTest {

    private int size = 450;
    private int mines = 414;
    private int width = 30;
    private int height = 15;

    @Test
    public void createNumbers() {
        Random random = new Random();
        int X = random.nextInt(width) + 1;
        int Y = random.nextInt(height) + 1;
        Model model = new Model(X, Y, height, width, mines, 5000);
        model.initialCell();
        model.createMines();
        model.removeInitialCell();
        model.createNumbers();
        model.crateEmpties();
        List<Cell> mines = new ArrayList<>();
        List<Cell> numbers = new ArrayList<>();
        List<Cell> empties = new ArrayList<>();
        for(Cell cell: model.getCells()){
            switch (cell.getStatus()){
                case NUMBER:
                    numbers.add(cell);
                    break;
                case EMPTY:
                    empties.add(cell);
                    break;
                case MINE:
                    mines.add(cell);
                    break;
            }
        }
        assertEquals((mines.size() + numbers.size() + empties.size()), size);
    }

    @Test
    public void serializable() throws IOException {
        Random random = new Random();
        int X = random.nextInt(width) + 1;
        int Y = random.nextInt(height) + 1;
        Model model = new Model(X, Y, height, width, mines, 5000);
        model.initialCell();
        model.createMines();
        model.removeInitialCell();
        model.createNumbers();
        model.crateEmpties();

        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(new File("D:\\model.dat")))) {
            ous.writeObject(model);
        }
    }

    @Test
    public void deserializable() throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("C:\\Users\\shura\\Desktop\\model.dat")))) {
            Model model = (Model) ois.readObject();

            model.getCells().forEach(System.out::println);
        }

    }

    @Test
    public void readButtons() throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("C:\\Users\\shura\\Desktop\\model.dat")))) {

            Object[] objects = (Object[]) ois.readObject();

            Model model = (Model) objects[0];

            model.getCells().forEach(System.out::println);

            System.out.println("======================================================");

            JButton[][] buttons = (JButton[][]) objects[1];
            for (JButton[] array : buttons) {
                for(JButton button: array){
                    if (button.getDisabledIcon() != null){
                        System.out.println(button.getDisabledIcon());
                    }
                    else {
                        System.out.println(button.getIcon());
                    }
                }
            }
        }

    }
}
