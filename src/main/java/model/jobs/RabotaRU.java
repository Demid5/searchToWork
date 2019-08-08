package model.jobs;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class RabotaRU extends JobSite {

    static private final String basicURL = "https://www.rabota.ru/v3_searchResumeByParamsResults.html?action=search&area=v3_searchResumeByParamsResults&p=-2005&w=&qk%5B0%5D=";

    private TreeMap<String, String> educationMap;
    private  TreeMap<String, String> experienceMap;

    private  String keySkills = "";
    private  String profession = "";
    private  String experience = "";
    private  String education = "";
    private int countCondition;

    public RabotaRU() {
        countCondition = 0;

        educationMap = new TreeMap<String, String>();
        educationMap.put("высшее", "&e%5B%5D=4");
        educationMap.put("среднее", "&e%5B%5D=1");
        educationMap.put("не имеет значения", "");

        experienceMap = new TreeMap<String, String>();
        experienceMap.put("нет опыта", "&eylo=-1");
        experienceMap.put("От 1 года до 3 лет", "&eylo=1");
        experienceMap.put("От 3 до 6 лет", "&eylo=3");
        experienceMap.put("Более 6 лет", "&eylo=6");
        experienceMap.put("не имеет значения", "");
    }

    @Override
    public String buildURL() {
        return getBasicURL() + getProf() + getKeySkills()  + getEducation() + getExperience() + "&qot%5B" + countCondition + "%5D=1&qsa%5B" + countCondition + "%5D%5B%5D=1&sf=&st=&cu=2&krl%5B%5D=3&krl%5B%5D=1229&krl%5B%5D=1242&krl%5B%5D=1207&krl%5B%5D=182&krl%5B%5D=1231&krl%5B%5D=1209&krl%5B%5D=1211&krl%5B%5D=1227&krl%5B%5D=1258&krl%5B%5D=1221&krl%5B%5D=203&krl%5B%5D=1213&krl%5B%5D=208&krl%5B%5D=181&krl%5B%5D=1263&krl%5B%5D=183&krl%5B%5D=1216&krl%5B%5D=185&krl%5B%5D=1218&krl%5B%5D=186&krl%5B%5D=1950&krl%5B%5D=187&krl%5B%5D=1220&krl%5B%5D=1223&krl%5B%5D=188&krl%5B%5D=1226&krl%5B%5D=1248&krl%5B%5D=206&krl%5B%5D=189&krl%5B%5D=211&krl%5B%5D=190&krl%5B%5D=1275&krl%5B%5D=191&krl%5B%5D=1259&krl%5B%5D=1264&krl%5B%5D=1265&krl%5B%5D=192&krl%5B%5D=210&krl%5B%5D=1233&krl%5B%5D=193&krl%5B%5D=1235&krl%5B%5D=1237&krl%5B%5D=1239&krl%5B%5D=1241&krl%5B%5D=1968&krl%5B%5D=1252&krl%5B%5D=1257&krl%5B%5D=1224&krl%5B%5D=1261&krl%5B%5D=205&krl%5B%5D=1267&krl%5B%5D=1277&krl%5B%5D=204&krl%5B%5D=194&krl%5B%5D=1269&krl%5B%5D=195&krl%5B%5D=1271&krl%5B%5D=196&krl%5B%5D=197&krl%5B%5D=1273&krl%5B%5D=1249&krl%5B%5D=1278&krl%5B%5D=207&krl%5B%5D=1280&krl%5B%5D=1255&krl%5B%5D=1286&krl%5B%5D=1288&krl%5B%5D=198&krl%5B%5D=199&krl%5B%5D=209&krl%5B%5D=1276&krl%5B%5D=1254&krl%5B%5D=1290&krl%5B%5D=1300&krl%5B%5D=1302&krl%5B%5D=200&krl%5B%5D=212&krl%5B%5D=201&krl%5B%5D=1253&krl%5B%5D=202&krl%5B%5D=1214&krl%5B%5D=4&krl%5B%5D=1147&krl%5B%5D=1150&krl%5B%5D=1152&krl%5B%5D=1156&krl%5B%5D=1159&krl%5B%5D=1160&krl%5B%5D=1165&krl%5B%5D=1776&krl%5B%5D=1169&krl%5B%5D=1161&krl%5B%5D=1168&krl%5B%5D=1171&krl%5B%5D=1173&krl%5B%5D=1777&krl%5B%5D=1166&krl%5B%5D=1778&krl%5B%5D=1779&krl%5B%5D=1177&krl%5B%5D=1780&krl%5B%5D=1179&krl%5B%5D=1191&krl%5B%5D=1190&krl%5B%5D=1153&krl%5B%5D=1174&krl%5B%5D=1781&krl%5B%5D=1782&krl%5B%5D=1148&krl%5B%5D=1181&krl%5B%5D=1162&krl%5B%5D=1183&krl%5B%5D=1783&krl%5B%5D=1790&krl%5B%5D=1163&krl%5B%5D=1157&krl%5B%5D=1784&krl%5B%5D=1185&krl%5B%5D=174&krl%5B%5D=1154&krl%5B%5D=1187&krl%5B%5D=1189&krl%5B%5D=1175&krl%5B%5D=1243&krl%5B%5D=7&krl%5B%5D=8&krl%5B%5D=9&krl%5B%5D=10&krl%5B%5D=689&krl%5B%5D=15&krl%5B%5D=687&krl%5B%5D=11&krl%5B%5D=12&krl%5B%5D=13&krl%5B%5D=14&krl%5B%5D=16&krl%5B%5D=121&krl%5B%5D=974&krl%5B%5D=122&krl%5B%5D=977&krl%5B%5D=979&krl%5B%5D=124&krl%5B%5D=123&krl%5B%5D=968&krl%5B%5D=972&krl%5B%5D=125&krl%5B%5D=970&krl%5B%5D=126&krl%5B%5D=120&krl%5B%5D=134&krl%5B%5D=976&krl%5B%5D=127&krl%5B%5D=128&krl%5B%5D=129&krl%5B%5D=130&krl%5B%5D=131&krl%5B%5D=132&krl%5B%5D=133&krl%5B%5D=25&krl%5B%5D=734&krl%5B%5D=33&krl%5B%5D=34&krl%5B%5D=26&krl%5B%5D=27&krl%5B%5D=744&krl%5B%5D=35&krl%5B%5D=28&krl%5B%5D=736&krl%5B%5D=29&krl%5B%5D=738&krl%5B%5D=730&krl%5B%5D=36&krl%5B%5D=732&krl%5B%5D=30&krl%5B%5D=37&krl%5B%5D=38&krl%5B%5D=31&krl%5B%5D=32&krl%5B%5D=740&krl%5B%5D=742&krl%5B%5D=39&krl%5B%5D=18&krl%5B%5D=691&krl%5B%5D=693&krl%5B%5D=695&krl%5B%5D=19&krl%5B%5D=698&krl%5B%5D=20&krl%5B%5D=21&krl%5B%5D=700&krl%5B%5D=1979&krl%5B%5D=702&krl%5B%5D=706&krl%5B%5D=704&krl%5B%5D=708&krl%5B%5D=710&krl%5B%5D=712&krl%5B%5D=714&krl%5B%5D=22&krl%5B%5D=716&krl%5B%5D=718&krl%5B%5D=23&krl%5B%5D=720&krl%5B%5D=722&krl%5B%5D=724&krl%5B%5D=726&krl%5B%5D=728&krl%5B%5D=696&krl%5B%5D=299&krl%5B%5D=300&krl%5B%5D=308&krl%5B%5D=1517&krl%5B%5D=301&krl%5B%5D=302&krl%5B%5D=303&krl%5B%5D=307&krl%5B%5D=1970&krl%5B%5D=306&krl%5B%5D=305&krl%5B%5D=304&krl%5B%5D=240&krl%5B%5D=243&krl%5B%5D=241&krl%5B%5D=1370&krl%5B%5D=244&krl%5B%5D=1372&krl%5B%5D=1374&krl%5B%5D=245&krl%5B%5D=1376&krl%5B%5D=242&krl%5B%5D=246&krl%5B%5D=1378&krl%5B%5D=1380&krl%5B%5D=1382&af=&at=&sex=&la=&nex=true&id=8409748";
    }


    @Override
    protected String getBasicURL() {
        return basicURL;
    }

    @Override
    public List<String> organizationLinks() {
        String url = buildURL();
        List<String> resultLinks = new LinkedList<String>();
        int countResume = 0;
        boolean flag = true;
        try {
            Document document = Jsoup.connect(url).userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements element = document.select("#js-search-results-wrapper > div > div > div.resume-search-left");
            for (Element elem : element.select("a")) {
                if (!elem.text().equals("")) {
                    resultLinks.add(elem.attr("href"));
                }
                countResume++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultLinks;
    }

    @Override
    public void setKeySkills(String[] skills) {
        if (skills != null) {
            StringBuilder builderSkills = new StringBuilder();
            for (String skill: skills) {
                countCondition++;
                builderSkills.append("&qk%5B" + countCondition + "%5D=" + convertStringText(skill) +
                        "&qot%5B" + countCondition + "%5D=1&qsa%5B" + countCondition + "%5D%5B%5D=4");
            }
            this.keySkills = builderSkills.toString();
        }
    }

    static private String convertStringText(String text) {
        String plusSign = "\\u002B";
        String resultText = text.replaceAll(plusSign, "%2B");
        resultText = resultText.replaceAll(" ", "+");
        return resultText.replaceAll("/", "%2F");
    }

    @Override
    public void setProf(String prof) {
        this.profession = "&qk%5B0%5D=" + convertStringText(prof) + "&qot%5B0%5D=1&qsa%5B0%5D%5B%5D=2";
    }

    @Override
    public void setExperience(String experience) {
        this.experience = experienceMap.get(experience);
    }

    @Override
    public void setEducation(String education) {
        this.education = educationMap.get(education);
    }

    @Override
    public String getEducation() {
        return this.education;
    }

    @Override
    public String getKeySkills() {
        return this.keySkills;
    }

    @Override
    public String getProf() {
        return this.profession;
    }

    @Override
    public String getExperience() {
        return this.experience;
    }
}
