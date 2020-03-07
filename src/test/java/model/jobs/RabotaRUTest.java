package model.jobs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RabotaRUTest {


    static RabotaRU rabotaRU;

    @BeforeClass
    public static void init() {
        rabotaRU = new RabotaRU();
    }

    @Test
    public void organizationLinks() {
        String url = rabotaRU.buildURL();
        List<String> expected = rabotaRU.organizationLinks();
        List<String> actual = rabotaRU.organizationLinks();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setKeySkills() {
        String input[] = {"Java", "JavaScript"};
        String expected = "&qk%5B1%5D=Java&qot%5B1%5D=1&qsa%5B1%5D%5B%5D=4" +
                "&qk%5B2%5D=JavaScript&qot%5B2%5D=1&qsa%5B2%5D%5B%5D=4";
        rabotaRU.setKeySkills(input);
        String actual = rabotaRU.getKeySkills();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void setProf() {
        String input = "developer java";
        String expected = "&qk%5B0%5D=developer+java&qot%5B0%5D=1&qsa%5B0%5D%5B%5D=2";
        rabotaRU.setProf(input);
        String actual = rabotaRU.getProf();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setProfNull() {
        String input = "";
        String expected = "&qk%5B0%5D=" + "&qot%5B0%5D=1&qsa%5B0%5D%5B%5D=2";
        rabotaRU.setProf(input);
        String actual = rabotaRU.getProf();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void setEducationHigher() {
        String input = "высшее";
        String expected = "&e%5B%5D=4";
        rabotaRU.setEducation(input);
        String actual = rabotaRU.getEducation();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setExperience() {
        String input = "От 1 года до 3 лет";
        String expected = "&eylo=1";
        rabotaRU.setExperience(input);
        String actual = rabotaRU.getExperience();
        Assert.assertEquals(expected, actual);
    }
}