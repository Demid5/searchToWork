package model.jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class HHRU extends JobSite {
    private static final String SEARCH_HH_URL = "https://hh.ru/search/resume?";

    private  TreeMap<String, String> educationMap;
    private  TreeMap<String, String> experienceMap;

    private  String keySkills = "";
    private  String profession = "";
    private  String experience = "";
    private  String education = "";

    public HHRU() {
        setBasicUrl("https://hh.ru");
        educationMap = new TreeMap<>();
        educationMap.put("высшее", "higher");
        educationMap.put("среднее", "secondary");
        educationMap.put("не имеет значения", "none");

        experienceMap = new TreeMap<>();
        experienceMap.put("нет опыта", "noExperience");
        experienceMap.put("От 1 года до 3 лет", "between1And3");
        experienceMap.put("От 3 до 6 лет", "between3And6");
        experienceMap.put("Более 6 лет", "moreThan6");
        experienceMap.put("не имеет значения", "");
    }

    public List<String> organizationLinks() {
        String url = buildURL();
        List<String> resultLinks = new LinkedList<>();
        int countResume = 0;
        boolean flag = true;
        do {
            try {
                Document document = Jsoup.connect(url).userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements element = document.select("body > div.HH-MainContent.HH-Supernova-MainContent > div > div > div.bloko-columns-wrapper > div > div > div.bloko-gap.bloko-gap_top > div > div > div.bloko-column.bloko-column_l-13.bloko-column_m-9 > div:nth-child(2)");

                for (Element elem : element.select("a")) {
                    if (!elem.text().equals("")) {
                        resultLinks.add(getBasicURL() + elem.attr("href"));
                    }
                    countResume++;
                }

                url = getSearchHhUrl() + document.selectFirst("body > div.HH-MainContent.HH-Supernova-MainContent > div > div > div.bloko-columns-wrapper > div > div > div.bloko-gap.bloko-gap_top > div > div > div.bloko-column.bloko-column_l-13.bloko-column_m-9 > div.bloko-gap.bloko-gap_top > div > a").attr("href");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                flag = false;
            }
        } while (flag && countResume < 20);
        return resultLinks;
    }

    static private String convertStringText(String text) {
        String plusSign = "\\u002B";
        String resultText = text.replaceAll(plusSign, "%2B");
        resultText = resultText.replace(" ", "+");
        return resultText.replace("/", "%2F");
    }

    public String getSearchHhUrl() {
        return SEARCH_HH_URL;
    }

    /*
        * getters and setters */
    @Override
    public void setProf(String prof) {
        if (!prof.equals("")) {
            this.profession = "&text=" + convertStringText(prof) + "&logic=normal&pos=position&exp_period=all_time";
        }
        else {
            this.profession = "";
        }
    }


    @Override
    public void setEducation(String education) {
        this.education = "&education=" + educationMap.get(education);
    }


    @Override
    public void setExperience(String experience) {
        this.experience = "&experience=" + experienceMap.get(experience);
    }



    @Override
    public void setKeySkills(String[] skills) {
        if (skills != null) {
            StringBuilder builderSkills = new StringBuilder();
            for (String skill: skills) {
                builderSkills.append("&text=" + convertStringText(skill) + "&logic=normal&pos=keywords&exp_period=all_time");
            }
            this.keySkills = builderSkills.toString();
        }
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
