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
    static private String basicURL = "https://hh.ru";

    private static TreeMap<String, Integer> skill;
    private static TreeMap<String, String> educationMap;
    private static TreeMap<String, String> experienceMap;

    private static String[] key_skills;
    private static String prof;
    private static String opit;
    private static String education;

    public HHRU() {
        skill = new TreeMap<>();
        skill.put("Java", 3093);
        skill.put("Python", 1114);
        skill.put("C++", 231);
        skill.put("C#", 230);
        skill.put("PHP", 3750);
        skill.put("JavaScript", 674);
        skill.put("HTML", 598);
        skill.put("HTML5", 7161);
        skill.put("CSS3", 3512);
        skill.put("CSS", 351);
        skill.put("Android", 7892);
        skill.put("Spring Framework", 1250);
        skill.put("Git", 3215);
        skill.put("SQL", 1252);
        skill.put("MySQL", 893);
        skill.put("MS SQL", 871);
        skill.put("PostgreSQL", 1063);
        skill.put("Hibernate", 585);
        skill.put("Управление проектами", 2924);
        skill.put("Работа в команде", 4118);
        skill.put("Английский язык", 5488);
        skill.put("ООП", 2350);
        skill.put("Linux", 730);
        skill.put("Adobe Photoshop", 87);
        skill.put("XML", 1460);
        skill.put("jQuery", 687);
        skill.put("Apache Maven", 106482);
        skill.put("Altassian Jira", 142);
        skill.put("BootStrap", 3641);
        skill.put("Ajax", 3640);
        skill.put("REST", 30565);

        educationMap = new TreeMap<>();
        educationMap.put("высшее", "higher");
        educationMap.put("среднее", "secondary");
        educationMap.put("не имеет значения", "none");

        experienceMap = new TreeMap<>();
        experienceMap.put("нет опыта", "noExperience");
        experienceMap.put("От 1 года до 3 лет", "between1And3");
        experienceMap.put("От 3 до 6 лет", "between3And6");
        experienceMap.put("Более 6 лет", "moreThan6");
        experienceMap.put("не имеет значения", "noExperience");
    }


    @Override
    public void setKey_skills(String[] skills) {
        this.key_skills = new String[skills.length];
        for (int i = 0; i < skills.length; ++i) {
            key_skills[i] = String.valueOf(skill.get(skills[i]));
        }
    }

    @Override
    public void setProf(String prof) {
        this.prof = prof;
    }

    @Override
    public void setOpit(String opit) {
        this.opit = experienceMap.get(opit);
    }

    @Override
    public void setEducation(String education) {
        this.education = educationMap.get(education);
    }

    @Override
    public String getBasicURL() {
        return basicURL;
    }

    @Override
    public String buildURL() {
        StringBuilder current = new StringBuilder("https://hh.ru/search/resume?");
        current.append(getProf()).append(getKey_skills()).append(getEducation()).append(getOpit());
        return current.toString();
    }

    @Override
    public String getEducation() {
        return "&education=" + this.education;
    }

    @Override
    public String getKey_skills() {
        StringBuilder resultSkill = new StringBuilder();
        for (int i = 0; i < key_skills.length; ++i) {
            resultSkill.append("&skill=" + key_skills[i]);
        }
        return resultSkill.toString();
    }

    @Override
    public String getProf() {
        return "&text=" + this.prof;
    }

    @Override
    public String getOpit() {
        return "&experience=" + this.opit;
    }


    public List<String> run() {
        String url = buildURL();
        List<String> resultLinks = new LinkedList<>();
        boolean flag = true;
        do {
            try {

                Document document = Jsoup.connect(url).userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements element = document.select("body > div.HH-MainContent.HH-Supernova-MainContent > div > div > div.bloko-columns-wrapper > div > div > div.bloko-gap.bloko-gap_top > div > div > div.bloko-column.bloko-column_l-13.bloko-column_m-9 > div:nth-child(2)");

                for (Element elem : element.select("a")) {
                    if (!elem.text().equals("")) {
                         resultLinks.add(basicURL + elem.attr("href"));
                    }
                }

                try {
                    url = basicURL + document.selectFirst("body > div.HH-MainContent.HH-Supernova-MainContent > div > div > div.bloko-columns-wrapper > div > div > div.bloko-gap.bloko-gap_top > div > div > div.bloko-column.bloko-column_l-13.bloko-column_m-9 > div.bloko-gap.bloko-gap_top > div > a").attr("href");
                }catch (NullPointerException e) {
                    flag = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (flag);
        return resultLinks;
    }
}
