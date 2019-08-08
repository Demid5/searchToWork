package model.jobs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RabotaRUTest {


    static RabotaRU rabotaRU;

    @BeforeClass
    public static void init() {
        rabotaRU = new RabotaRU();
    }

    @Test
    public void buildURL() {
    }

    @Test
    public void getBasicURL() {
    }

    @Test
    public void organizationLinks() {
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

    }

    @Test
    public void setEducation() {
    }

    @Test
    public void getEducation() {
    }

    @Test
    public void getKeySkills() {
    }

    @Test
    public void getProf() {
    }

    @Test
    public void getExperience() {
    }
}