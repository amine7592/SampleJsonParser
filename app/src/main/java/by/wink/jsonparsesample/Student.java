package by.wink.jsonparsesample;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amine on 27/02/17.
 */
public class Student {

    private static final String NAME_KEY = "nome";
    private static final String EMAIL_KEY = "email";
    private static final String GITHUB_KEY = "github";

    String name, email, github;

    public Student(JSONObject jsonStudent) {
        try {
            name = jsonStudent.getString(NAME_KEY);
            email = jsonStudent.getString(EMAIL_KEY);
            github = buildGithubUrl(jsonStudent.optString(GITHUB_KEY,""));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }


    private String buildGithubUrl(String username){
        username = username.replace("@","");
        return "https://github.com/" + username;
    }

}
