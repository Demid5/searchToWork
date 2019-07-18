import java.util.Map;

public class SuperJob extends JobSite {
    private static final String basicURL = "https://api.superjob.ru/2.0/v3.h.3701842.65a8ac506fde19dbb50a8f9c5f1c4ef12228ed09.3f7d4147d9503b8799041689f2347d5406ca7e79/resumes/";

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
        return basicURL + "?education=" + Search.getIdEducation();
    }

    @Override
    public String getBasicURL() {
        return basicURL;
    }

    @Override
    public String getEducation() {
        return null;
    }

    @Override
    public String keyWords() {
        return null;
    }

    @Override
    public String keySkills() {
        return null;
    }

}
