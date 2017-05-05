/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "RESTServlet", urlPatterns = {"/user"})
public class RESTServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder.create();
            final PrintWriter writer = response.getWriter();
            HashMap<String, String> map = new HashMap<>();
        String message = null;
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            message = "no auth";
        } else if (!auth.toUpperCase().startsWith("BASIC ")) {
            message = "we only do BASIC";
        } else {
            // Get encoded user and password, comes after "BASIC "
            String userpassEncoded = auth.substring(6);

            // Decode it, using any base 64 decoder
            sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
            message = new String(dec.decodeBuffer(userpassEncoded));
        }
        map.put("error", "error");
                map.put("message", message);
                String json = gson.toJson(map);
                response.getWriter().write(json);
    }
}
