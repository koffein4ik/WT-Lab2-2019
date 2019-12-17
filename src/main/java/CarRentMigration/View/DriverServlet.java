package CarRentMigration.View;

import CarRentMigration.DAO.parsers.ParserType;
import CarRentMigration.Service.DriverService;
import CarRentMigration.Service.DriverServiceImpl;
import CarRentMigration.Service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/driver")
public class DriverServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String parserType = request.getParameter("parsertype");
        DriverService driverService = new DriverServiceImpl();
        try {
            if (parserType != null) {
                switch (parserType) {
                    case "dom": {
                        request.setAttribute("drivers", driverService.getAllDriversFromPaser(ParserType.DOM));
                        request.setAttribute("parsertype", "dom");
                        break;
                    }
                    case "sax": {
                        request.setAttribute("drivers", driverService.getAllDriversFromPaser(ParserType.SAX));
                        request.setAttribute("parsertype", "sax");
                        break;
                    }
                    case "stax": {
                        request.setAttribute("drivers", driverService.getAllDriversFromPaser(ParserType.STAX));
                        request.setAttribute("parsertype", "stax");
                        break;
                    }
                    default: {
                        request.setAttribute("drivers", driverService.getAllDriversFromPaser(ParserType.DOM));
                        request.setAttribute("parsertype", "No parsertype specified... Using dom parser");
                    }
                }
            } else {
                request.setAttribute("drivers", driverService.getAllDriversFromPaser(ParserType.DOM));
                request.setAttribute("parsertype", "No parsertype specified... Using dom parser");
            }
        } catch (ServiceException s) {
            System.out.println(s.getMessage());
        }
        getServletContext().getRequestDispatcher("/drivers").forward(request, response);
    }

}
