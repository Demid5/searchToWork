package model.jobs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class HHRUTest {

    static HHRU hhru;

    @BeforeClass
    public static void init() {
        hhru = new HHRU();
    }

    @Test
    public void setProfNull() {
        String expected = "";
        hhru.setProf(expected);
        String actual = hhru.getProf();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setProfession() {
        String input = "developer c/c++";
        String expected = "&text=developer+c%2Fc%2B%2B&logic=normal&pos=position&exp_period=all_time";
        hhru.setProf(input);
        String actual = hhru.getProf();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setEducationHigher() {
        String input = "высшее";
        String expected = "&education=higher";
        hhru.setEducation(input);
        String actual = hhru.getEducation();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setEducationNone() {
        String input = "не имеет значения";
        String expected = "&education=none";
        hhru.setEducation(input);
        String actual = hhru.getEducation();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void setKeySkills() {
        String input[] = {"java", "javascript"};
        String expected = "&text=java&logic=normal&pos=keywords&exp_period=all_time&text=javascript&logic=normal&pos=keywords&exp_period=all_time";
        hhru.setKeySkills(input);
        String actual = hhru.getKeySkills();
        Assert.assertEquals(expected, actual);
    }
}