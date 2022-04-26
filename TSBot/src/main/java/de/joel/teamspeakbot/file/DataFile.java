package de.joel.teamspeakbot.file;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.IOException;

public class DataFile {

    private YamlFile userFile;

    public void load() {

        userFile = new YamlFile("test/channel.yml");

        if (userFile.exists()) {
            try {
                userFile.load();
            } catch (InvalidConfigurationException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                userFile.createNewFile(true);
                System.out.println("File created");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                userFile.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean userExists(String uniq) {
        return userFile.contains(uniq);
    }

    public void createUser(String uniq) {
        userFile.set(uniq, true);
        try {
            userFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
