package net.daw.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.daw.bean.Operacion;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gSon = new Gson();
        DecimalFormat df = new DecimalFormat("#.00");



            String sNum1 = request.getParameter("num1");
            String sNum2 = request.getParameter("num2");
            String op = request.getParameter("op");

            String res = "";

            String regExp = "-?[0-9]+([.][0-9]+)?";

            try {
                if (sNum1.matches(regExp) && sNum2.matches(regExp)) {

                    Double num1 = Double.parseDouble(sNum1);
                    Double num2 = Double.parseDouble(sNum2);

                    if (op.equalsIgnoreCase("suma")) {

                        res = df.format(num1 + num2);

                    } else if (op.equalsIgnoreCase("resta")) {
                        
                        res = df.format(num1 - num2);

                    } else if (op.equalsIgnoreCase("mult")) {

                        res = df.format(num1 * num2);

                    } else if (op.equalsIgnoreCase("divi")) {

                        if (num2 == 0) {
                            throw new NumberFormatException();
                        } else {
                            res = df.format(num1 / num2);
                        }

                    }


                    Operacion operacion = new Operacion(num1, num2, res, op);
                    String strJson = gSon.toJson(operacion);
                    out.print(strJson);

                } else {
                    throw new IllegalArgumentException();
                }

            } catch (NumberFormatException e) {
                response.setStatus(500);
                String error = "No se pueden divir números por 0";
                String strJson = gSon.toJson(error);
                out.print(strJson);
            } catch (IllegalArgumentException iae) {
                response.setStatus(500);
                String error;
                if (sNum1.equals("") || sNum2.equals("")) {
                    error = "Deben rellenarse todos los campos";
                } else {
                    error = "Debe introducir sólo numeros enteros";
                }

                String strJson = gSon.toJson(error);
                out.print(strJson);
            }


    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
