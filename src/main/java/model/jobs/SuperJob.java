package model.jobs;

import java.util.Map;

public class SuperJob extends JobSite {
    private static final String basicURL = "https://api.superjob.ru/2.0/v3.h.3701842.65a8ac506fde19dbb50a8f9c5f1c4ef12228ed09.3f7d4147d9503b8799041689f2347d5406ca7e79/resumes/";

    private static String key_skills;
    private static String prof;
    private static String[] opit;
    private static String education;

    public SuperJob() {
        opit = new String[2];
        opit[0] = "0";
        opit[1] = "36";
    }

    private static Map<String, Integer> educationSelectListMap;
     /*   2 — высшее
            3 — неполное высшее
             4 — средне-специальное
             5 — среднее
            6 — учащийся*/
/*    educationSelectListMap = new TreeMap<>();
        educationSelectListMap.put("высшее", 2);
        educationSelectListMap.put("неполное высшее", 3);
        educationSelectListMap.put("средне-специальное", 4);
        educationSelectListMap.put("Неважно", null);*/

    @Override
    public String buildURL() {
        // тут потом будет сформированно как то нормально url-запрос
        // путем сложения параметров
        // basicURL + /? + getEducation + keyWords + keySkills
        // keywords[0][srws]=60&keywords[0][skwc]=and&keywords[0][keys]=программист java&keywords[1][skwc]=or&keywords[1][keys]=Spring, javaskript, python&experience_from=12&experience_to=36
        String resultURL = basicURL + "?" + "keywords[0][srws]=60&keywords[0][skwc]=and&keywords[0][keys]=" +
                getProf() + "&keywords[1][srws]=3keywords[1][skwc]=and&keywords[1][keys]=" +
                getKey_skills() + "&experience_from=" + getOpit()[0] + "&experience_to=" + getOpit()[1] +
                "&education=" + getEducation();
        // https://api.superjob.ru/2.0/v3.h.3701842.65a8ac506fde19dbb50a8f9c5f1c4ef12228ed09.3f7d4147d9503b8799041689f2347d5406ca7e79/resumes/?keywords[0][srws]=60&keywords[0][skwc]=and&
        // keywords[0][keys]=%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%81%D1%82%20java
        // &keywords[1][srws]=3keywords[1][skwc]=and&keywords[1][keys]=java,spring&experience_from=12
        // &experience_to=36&education=2
        return resultURL;
    }

    @Override
    public void setKey_skills(String skills) {
        this.key_skills = skills;
    }

    @Override
    public void setProf(String prof) {
        this.prof = prof;
    }

    @Override
    public void setOpit(String opit) {
        if (opit.equals("От 1 года до 3 лет")) {
            this.opit[0] = "12";
            this.opit[1] = "36";
        }
        if (opit.equals("От 3 до 6 лет") || opit.equals("Более 6 лет")) {
            this.opit[0] = "36";
        }
    }

    @Override
    public void setEducation(String education) {
        if (education.equals("высшее")) {
            this.education = "2";
        }
        if (education.equals("среднее")) {
            this.education = "3";
        }
    }

    @Override
    public String getBasicURL() {
        return basicURL;
    }

    @Override
    public String getEducation() {
        return this.education;
    }

    @Override
    public String getKey_skills() {
        return this.key_skills;
    }

    @Override
    public String getProf() {
        return this.prof;
    }

    @Override
    public String[] getOpit() {
        return this.opit;
    }


}
