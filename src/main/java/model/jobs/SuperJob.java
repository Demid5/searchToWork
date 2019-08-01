package model.jobs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SuperJob extends JobSite {
    private static final String basicURL = "https://api.superjob.ru/2.0/v3.h.3701842.65a8ac506fde19dbb50a8f9c5f1c4ef12228ed09.3f7d4147d9503b8799041689f2347d5406ca7e79/resumes/?";

    private static HttpURLConnection connection;


    private static int countCondition;
    private static String keySkills;
    private static String profession;
    private static String experience;
    private static String education;

    private String requestToWebsait(String urlToRead) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL(urlToRead);
            connection = (HttpURLConnection) url.openConnection();
            // request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();


            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent.toString();
    }


    public List<String> organizationLinks() {
        String urlToRead = buildURL();
        String responseBody = requestToWebsait(urlToRead);
        List<String> resultLinks = new ArrayList<>();
        JSONObject object = new JSONObject(responseBody);
        JSONArray albom = object.getJSONArray("objects");
        for (int i = 0; i < albom.length(); ++i) {
            JSONObject obj = albom.getJSONObject(i);
            resultLinks.add(obj.getString("link"));
        }
        return resultLinks;
    }

    protected String getBasicURL() {
        return basicURL;
    }



    private static Map<String, Integer> educationMap;
    private static Map<String, String> experienceMap;


    public SuperJob() {
        educationMap = new TreeMap<>();
        educationMap.put("высшее", 2);
        educationMap.put("среднее", 5);
        educationMap.put("не имеет значения", 6);

        experienceMap = new TreeMap<>();
        experienceMap.put("не имеет значения", null);
        experienceMap.put("От 1 года до 3 лет", "&experience_from=1&experience_to=3");
        experienceMap.put("От 3 до 6 лет", "&experience_from=3&experience_to=6");
        experienceMap.put("нет опыта", null);

    }


    /*getters and setters*/

    static private String convertStringText(String text) {
        String plusSign = "\\u002B";
        String resultText = text.replaceAll(" ", "%20");
        resultText = resultText.replaceAll(plusSign, "%2B");
        return resultText.replaceAll("/", "%2F");
    }



    @Override
    public void setProf(String prof) {
        this.profession = "&keywords[" + countCondition + "][srws]=60&keywords[" + countCondition + "" +
                "][skwc]=and&keywords[" + countCondition + "][keys]=" + convertStringText(prof);
        countCondition++;
    }


    @Override
    public void setEducation(String education) {
        this.education = "&education=" + educationMap.get(education) + "&moveable=2&moveable=2";
    }


    @Override
    public void setExperience(String experience) {
        this.experience = experienceMap.get(experience);
    }


    @Override
    public void setKeySkills(String[] skills) {
        StringBuilder builderSkills = new StringBuilder();
        countCondition += (skills.length - 1);
        for (String skill: skills) {
            builderSkills.append("&keywords[" + countCondition + "][srws]=3&keywords[" + countCondition +
                    "][skwc]=and&keywords[" + countCondition + "][keys]=" + skill);
            countCondition--;
        }
        this.keySkills = builderSkills.toString();
    }




    @Override
    public String getProf() {
        return this.profession;
    }

    @Override
    public String getEducation() {
        return this.education;
    }

    @Override
    public String getExperience() {
        return this.experience;
    }


    @Override
    public String getKeySkills() {
        return this.keySkills;
    }

}
