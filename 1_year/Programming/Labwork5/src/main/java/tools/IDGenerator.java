package tools;

import java.util.HashSet;
import java.util.Random;

public class IDGenerator {
    private static final Random random=new Random();
    private static final HashSet<Integer> arg = new HashSet<>();
    public static int GenerateID(){
        int id= random.nextInt(Integer.MAX_VALUE);
        while (arg.contains(id)) {
            id = random.nextInt(Integer.MAX_VALUE);
        }
        arg.add(id);
        return id;
}}
