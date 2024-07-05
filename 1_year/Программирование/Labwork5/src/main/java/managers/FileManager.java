package managers;

import collections.Console;
import collections.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import tools.Deserializer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static java.lang.System.in;

public class FileManager {
    private final String FileName;
    private Console console;
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new Deserializer())
            .setPrettyPrinting()
            .create();
    public FileManager(String FileName, Console console){
        this.FileName=FileName;
        this.console=console;
    }
    public Collection<Person> readCollection() {
        if (FileName != null && !FileName.isEmpty()) {
            try (var fileReader = new FileReader(FileName)) {
                var collectionType = new TypeToken<ArrayDeque<Person>>() {}.getType();
                var reader = new BufferedReader(fileReader);

                var jsonString = new StringBuilder();

                String line;
                while((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                }

                ArrayDeque<Person> collection = gson.fromJson(jsonString.toString(), collectionType);

                console.println("Коллекция успешна загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                console.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new ArrayDeque<>();
}}

