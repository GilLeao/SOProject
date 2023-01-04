import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class User
{
    private String name, password;

    public User() {}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public User readFromJsonFile() throws IOException {
        String path = "src/info/users.json";
        ObjectMapper objectMapper = new ObjectMapper();

        User credentials = objectMapper.readValue(new File(path), User.class);

        return credentials;
    }

    public boolean checkCredentials(String name, String password) throws IOException {
        if(readFromJsonFile().getName().equals(name) && readFromJsonFile().getPassword().equals(password))
        {
            return true;

        } else {

            return false;
        }
    }
}
