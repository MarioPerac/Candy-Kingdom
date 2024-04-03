package factory.repository;

import com.google.gson.Gson;
import factory.model.Operator;
import factory.properties.ConfigProperties;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperatorRepository {

    private final Gson gson = new Gson();
    ConfigProperties prop = new ConfigProperties();


    public List<Operator> getAll() {

        Operator[] operators = null;
        try (BufferedReader in = new BufferedReader(new FileReader(prop.getFactoryUsersPath()))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                jsonContent.append(line);
            }

            operators = gson.fromJson(jsonContent.toString(), Operator[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (operators == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(operators));
    }

    public boolean authentication(String name) {

        List<Operator> operators = getAll();

        for (Operator o : operators) {
            if (o.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

}
