package au.andrew.controller;


import au.andrew.dbProc.DataProc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class IndexController {

    // JDBC variables for opening and managing connection
    private static ResultSet rs;
    private String query = "";
    private DataProc dataProc = new DataProc();
    private boolean matchUpdated = false;


    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username", required = false) String text) {

        int score = 0;

        ModelAndView model = new ModelAndView();
        StringBuilder statScore = new StringBuilder();
        StringBuilder statDates = new StringBuilder();
        String bufScore, bufDate;
        System.out.println(text);
        //query = "delete from matches where team1score = -1 or team2score = -1;";
        //dataProc.putData(query);
        query = "SELECT * FROM matches where team1score != -1 or team2score != -1;";
        rs = dataProc.getData(query);

        try {
            while (rs.next()) {
                for (String st : rs.getString(3).split(";")) {
                    if (text.toLowerCase().equals(st.toLowerCase())) {
                        score += rs.getInt(5);
                        bufDate = rs.getString(2) + ";";
                        bufScore = score + ";";
                        statScore.append(bufScore);
                        statDates.append(bufDate);
                    }
                }
                for (String st : rs.getString(4).split(";")) {
                    if (text.toLowerCase().equals(st.toLowerCase())) {
                        score += rs.getInt(6);
                        bufDate = rs.getString(2) + ";";
                        bufScore = score + ";";
                        statScore.append(bufScore);
                        statDates.append(bufDate);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataProc.conClose();
        }

        /*for (String str : stat) {
            System.out.println(str);
        }*/
//statScore.clear(); statDates.clear();
//statScore.add("'1'"); statScore.add("'5'"); statScore.add("'3'");
//statDates.add("'2016-10-04 22:23:00'"); statDates.add("'2016-10-04 22:23:00'");
//statDates.add("'2016-10-04 22:23:00'");
        System.out.println(statDates.length());
        System.out.println(statScore.length());


        //StringBuilder statScore1 = new StringBuilder("1;0;");
        //StringBuilder statDates1 = new StringBuilder("2018-01-10 10:00:00.0;2018-01-01 01:46:53.0;");

        //System.out.println(statDates1);
        //System.out.println(statScore1);
        //String re = statDates.toString();

        String separator = System.getProperty("line.separator");
        if (statDates.length() == 0)
            if (text.length() != 0) text = "Hello, " + text + "!" +
                    separator + "You are unregistered player!"
                    + separator + "You need to play at lest 1 match.";
            else text = "Hello!"
                    + separator + "You are unregistered player!"
                    + separator + "You need to play at lest 1 match.";
        else text = "Hello, " + text + "!";

        System.out.println(text);
        model.addObject("someAttribute", text);
        model.addObject("someAttribute2", statScore.toString());
        model.addObject("someAttribute3", statDates.toString());
        model.setViewName("login");
        return model;
    }


    @RequestMapping(value = "/newMatch", method = RequestMethod.POST)
    public ModelAndView newMatch(@RequestParam(value = "username", required = false) String text) {

        ModelAndView model = new ModelAndView();
        query = "SELECT * FROM matches ORDER BY id DESC LIMIT 1;";
        rs = dataProc.getData(query);

        try {
            while (rs.next()) {

                if (rs.getString(5).equals("-1") || rs.getString(6).equals("-1")) {
                    model.addObject("team1Attribute", rs.getString(3));
                    model.addObject("team2Attribute", rs.getString(4));
                    model.addObject("team1scoreAttribute", " ");
                    model.addObject("team2scoreAttribute", " ");
                    model.addObject("message", "You need to fill the previous match results!");
                    matchUpdated = true;
                } else {
                    model.addObject("team1scoreAttribute", " ");
                    model.addObject("team2scoreAttribute", " ");
                    matchUpdated = false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dataProc.conClose();
        }
        model.setViewName("newMatch");
        return model;
    }


    @RequestMapping(value = "/saveMatch", method = RequestMethod.POST)
    public ModelAndView saveMatch(@RequestParam(value = "team1", required = true) String team1,
                                  @RequestParam(value = "team2", required = true) String team2,
                                  @RequestParam(value = "date", required = true) String date,
                                  @RequestParam(value = "team1score", required = true) String team1score,
                                  @RequestParam(value = "team2score", required = true) String team2score,
                                  @RequestParam(value = "matchDetails", required = false) String matchDetails) {

        ModelAndView model = new ModelAndView();
        System.out.println("matchDetails = " + matchDetails);

        if (team1score.equals(" ")) team1score = "-1";
        if (team2score.equals(" ")) team2score = "-1";

        System.out.println("team1score = " + team1score);
        System.out.println("team2score = " + team2score);

        if (!team1score.equals("-1") && !team2score.equals("-1") && matchUpdated) {
            query = "delete from matches where team1score = -1 or team2score = -1;";
            dataProc.putData(query);
            matchUpdated = false;
        }

        if (!team1score.equals("-1") || !team2score.equals("-1")) {
            if (Integer.valueOf(team1score.trim()) > Integer.valueOf(team2score.trim())) {
                team1score = "1";
                team2score = "0";
            } else if (Integer.valueOf(team1score.trim()) < Integer.valueOf(team2score.trim())) {
                team1score = "0";
                team2score = "1";
            } else {
                team1score = "0";
                team2score = "0";
            }
        }


        //System.out.println(team1score);

        query = "INSERT INTO matches(gameTime, team1, team2, team1score, team2score, details) " +
                "VALUES ('" + date + "', '" + team1 + "', '" + team2 + "', " + team1score + ", " + team2score + ", '" + matchDetails + "');";
        //System.out.println(query);
        dataProc.putData(query);

        model.addObject("someAttribute", "matches");
        model.setViewName("index");
        return model;
    }
}
