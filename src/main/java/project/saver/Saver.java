package project.saver;


import project.level.Level;
import project.models.Model;

import javax.swing.*;
import java.io.*;

/**
 * @author Alexander Naumov on 24.10.2017
 * @version 1.0.0
 */

public class Saver {

    private String path;
    private File file;
    private Object[] objects;
    private static Saver saver;

    private Saver(){
        String osProperties = System.getProperty("os.name").toLowerCase();
        String osName = osProperties.split(" ")[0];
        if (osName.equals("windows")){
            path = "C:\\mines.dat";
        }
        else {
            path = "usr/Documents/mines.dat";
        }
        file = new File(path);
    }

    public static Saver getSaver(){
        if (saver == null){
            saver = new Saver();
        }
        return saver;
    }

    public void save(Model model, JButton[][] buttons, JLabel timer, JLabel flags, Level level){
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file))){
            objects = new Object[5];
            objects[0] = model;
            objects[1] = buttons;
            objects[2]= timer;
            objects[3] = flags;
            objects[4] = level;
            ous.writeObject(objects);
        }
        catch (Exception e){
            System.out.println("saving game files failed.");
        }
    }

    public Object[] read(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))){
            objects = (Object[]) ois.readObject();
        }
        catch (Exception e){
            System.out.println("restoring game files failed.");
        }
        return objects;
    }

    public boolean deleteSaves() {
        return file.exists() && file.delete();
    }

    public File getFile() {
        return file;
    }
}
