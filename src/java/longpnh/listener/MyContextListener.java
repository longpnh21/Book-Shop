/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longpnh.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.spi.http.HttpContext;

/**
 * Web application lifecycle listener.
 *
 * @author arceu
 */
public class MyContextListener implements ServletContextListener {

    private final String PATH = "WEB-INF\\pathMap.txt";

    public void contextInitialized(ServletContextEvent sce) {
        try {
            File pathFile = new File(sce.getServletContext().getRealPath("/") + PATH);
            Scanner myReader = new Scanner(pathFile);
            Map<String, String> urlMap = new HashMap<String, String>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] entry = data.split(" ");
                urlMap.put(entry[0], entry[1]);
            }
            myReader.close();
            sce.getServletContext().setAttribute("URLMAP", urlMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("URLMAP");
    }
}
