package com.daisukeyamashita.anytime_call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CreateEngineerMtgLogServlet  extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CreateEngineerMtgLogServlet.class.getName());
    private static final String WEBHOOK_ENDPOINT = "https://webhook.example.com";
    private static final String DATE_PATTERN = "yyyyMMdd";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Japan"));
        cal.add(Calendar.DAY_OF_MONTH, 7);
        String date = new SimpleDateFormat(DATE_PATTERN).format(cal.getTime());
        try {
            URL url = new URL(WEBHOOK_ENDPOINT + "?date=" + date);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
                resp.getWriter().println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
