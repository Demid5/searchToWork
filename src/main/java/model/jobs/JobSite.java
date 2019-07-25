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

public abstract class JobSite {

    private static HttpURLConnection connection;
    private static String openBracket = "%5B";
    private static String closeBracket = "%5D";


    // следующие абстрактные методы нужны для составления url-запроса
    // для каждого сайта будет реализован в соотвествии с требованиями документации API
    abstract public String getBasicURL();

    abstract public String buildURL() ;

    abstract public String getEducation();

    abstract public String keyWords();

    abstract public String keySkills();
    //

    private static String requestToWebsait(String urlToRead) {
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
            System.out.println("response code = " + status);


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



    public List<String> startSearch() {
        String urlToRead = buildURL();
        String responseBody = requestToWebsait(urlToRead);
        return organizationLinks(responseBody);
    }



    private static List<String> organizationLinks(String  responseBody) {
        List<String> resultLinks = new ArrayList<>();
        JSONObject object = new JSONObject(responseBody);
        JSONArray albom = object.getJSONArray("objects");
        for (int i = 0; i < albom.length(); ++i) {
            JSONObject obj = albom.getJSONObject(i);
            resultLinks.add(obj.getString("link"));
        }
        return resultLinks;
    }
}
