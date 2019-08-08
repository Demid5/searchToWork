package model.jobs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuperJobTest {

    static SuperJob superJob;

    @BeforeClass
    public static void init() {
        superJob = new SuperJob();
    }

    @Test
    public void organizationLinks() {
    }

    @Test
    public void getBasicURL() {
    }

    @Test
    public void setProfNull() {
        String expected = "";
        superJob.setProf(expected);
        String actual = superJob.getProf();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setProfession() {
        String input = "developer c/c++";
        String expected = "&keywords[0][srws]=60&keywords[0][skwc]=and&keywords[0][keys]=developer%20c%2Fc%2B%2B";
        superJob.setProf(input);
        String actual = superJob.getProf();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setEducationHigher() {
        String input = "высшее";
        String expected = "&education=2";
        superJob.setEducation(input);
        String actual = superJob.getEducation();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setEducationNone() {
        String input = "не имеет значения";
        String expected = "&education=6";
        superJob.setEducation(input);
        String actual = superJob.getEducation();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void setExperience() {
    }

    @Test
    public void setKeySkills() {
        String input[] = {"java", "javascript"};
        String expected = "";
        if (superJob.getProf().equals("")) {
            expected = "&keywords[0][srws]=3&keywords[0][skwc]=and&keywords[0][keys]=java&keywords[1][srws]=3&keywords[1][skwc]=and&keywords[1][keys]=javascript";
        }
        else {
            expected = "&keywords[1][srws]=3&keywords[1][skwc]=and&keywords[1][keys]=java&keywords[2][srws]=3&keywords[2][skwc]=and&keywords[2][keys]=javascript";
        }
        superJob.setKeySkills(input);
        String actual = superJob.getKeySkills();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getProf() {
    }

    @Test
    public void getEducation() {
    }

    @Test
    public void getExperience() {
    }

    @Test
    public void getKeySkills() {
    }
}