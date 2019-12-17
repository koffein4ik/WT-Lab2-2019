package CarRentMigration.View;

import CarRentMigration.DAO.parsers.ParserType;
import CarRentMigration.Service.CarService;
import CarRentMigration.Service.CarServiceImpl;
import CarRentMigration.Service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/car")
public class CarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String parserType = request.getParameter("parsertype");
        CarService carService = new CarServiceImpl();
        try {
            if (parserType != null) {
                switch (parserType) {
                    case "dom": {
                        request.setAttribute("cars", carService.getAllCarsFromPaser(ParserType.DOM));
                        request.setAttribute("parsertype", "dom");
                        break;
                    }
                    case "sax": {
                        request.setAttribute("cars", carService.getAllCarsFromPaser(ParserType.SAX));
                        request.setAttribute("parsertype", "sax");
                        break;
                    }
                    case "stax": {
                        request.setAttribute("cars", carService.getAllCarsFromPaser(ParserType.STAX));
                        request.setAttribute("parsertype", "stax");
                        break;
                    }
                    default: {
                        request.setAttribute("cars", carService.getAllCarsFromPaser(ParserType.DOM));
                        request.setAttribute("parsertype", "No parsertype specified... Using dom parser");
                    }
                }
            } else {
                request.setAttribute("cars", carService.getAllCarsFromPaser(ParserType.DOM));
                request.setAttribute("parsertype", "No parsertype specified... Using dom parser");
            }
        } catch (ServiceException s) {
            System.out.println(s.getMessage());
        }
        getServletContext().getRequestDispatcher("/cars").forward(request, response);
    }

}
