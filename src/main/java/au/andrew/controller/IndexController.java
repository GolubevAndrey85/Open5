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
//@RequestMapping("/hello")
//@RequestMapping("/")
public class IndexController {

    private static final String url = "jdbc:mysql://localhost:3306/foosball";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    PreparedStatement preparedStmt;
    String query = "";
    DataProc dataProc = new DataProc();


    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/")
    public String index(Model m) {
        m.addAttribute("someAttribute", "someValue");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username", required = false) String text) {

        ModelAndView model = new ModelAndView();
        ArrayList<String> stat = new ArrayList<>();
        System.out.println(text);
        query = "SELECT * FROM matches";
        rs = dataProc.getData(query);

       try {
            while (rs.next()) {
                for (String st:rs.getString(3).split(";")) {
                    if (text.toLowerCase().equals(st.toLowerCase())){
                        stat.add(rs.getString(2) + ";" + rs.getString(5));
                    }
                }
                for (String st:rs.getString(4).split(";")) {
                    if (text.toLowerCase().equals(st.toLowerCase())){
                        stat.add(rs.getString(2) + ";" + rs.getString(6));
                    }
                }
            }
        } catch (SQLException e) {e.printStackTrace();
        } finally {
            dataProc.conClose();
        }

        for (String str : stat) {
            System.out.println(str);
        }

        text = "Hello, " + text + "!";
        model.addObject("someAttribute", text);
        model.addObject("someAttribute2", stat);
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
                } else {
                    model.addObject("team1scoreAttribute", " ");
                    model.addObject("team2scoreAttribute", " ");
                }
            }

        } catch (SQLException e) { e.printStackTrace();

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
                                  @RequestParam(value = "team2score", required = true) String team2score) {

        ModelAndView model = new ModelAndView();

        if (team1score.equals(" ")) team1score = "-1";
        if (team2score.equals(" ")) team2score = "-1";

        System.out.println("team1score = " + team1score);
        System.out.println("team2score = " + team2score);

        if (!team1score.equals("-1")||!team2score.equals("-1")){
            if (Integer.valueOf(team1score.trim())>Integer.valueOf(team2score.trim())){
                team1score = "1"; team2score = "0";
            } else if (Integer.valueOf(team1score.trim())<Integer.valueOf(team2score.trim())){
                team1score = "0"; team2score = "1";
            } else {team1score = "0"; team2score = "0";}}
        //System.out.println(team1score);

        query = "INSERT INTO matches(gameTime, team1, team2, team1score, team2score) " +
                "VALUES ('" + date + "', '" + team1 + "', '"  + team2 + "', " + team1score + ", " + team2score + ");";
        //System.out.println(query);
        dataProc.putData(query);

        model.addObject("someAttribute", "matches");
        model.setViewName("index");
        return model;
    }
}
