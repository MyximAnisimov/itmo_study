package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Collection;

public class SomeClass{
    private String fileName;
    private StandartConsole console;
    public SomeClass(String fileName, StandartConsole console){
        this.fileName=fileName;
        this.console=console;
    }
public Collection<Person>readCollection() {
    GsonBuilder gb=new GsonBuilder();
    Gson gson = gb.create();
    try(var fileReader=new FileReader(fileName)){
    var reader=new BufferedReader(fileReader);
    var jsonString = new StringBuilder();
    String line;
    while((line=reader.readLine())!=null){
        line = line.trim();
        if (!line.equals("")) {
            jsonString.append(line);
        }
Person Maxim = gson.fromJson(jsonString.toString(),Person.class);}
}
    catch(IOException exception){
console.printError("Ошибка!");
    }
    return null;}}
