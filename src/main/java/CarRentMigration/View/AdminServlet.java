package CarRentMigration.View;

import CarRentMigration.DAO.parsers.ParserType;
import CarRentMigration.Service.AdminService;
import CarRentMigration.Service.AdminServiceImpl;
import CarRentMigration.Service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String parserType = request.getParameter("parsertype");
        AdminService adminService = new AdminServiceImpl();
        try {
            if (parserType != null) {
                switch (parserType) {
                    case "dom": {
                        request.setAttribute("admins", adminService.getAllAdminsFromPaser(ParserType.DOM));
                        request.setAttribute("parsertype", "dom");
                        break;
                    }
                    case "sax": {
                        request.setAttribute("admins", adminService.getAllAdminsFromPaser(ParserType.SAX));
                        request.setAttribute("parsertype", "sax");
                        break;
                    }
                    case "stax": {
                        request.setAttribute("admins", adminService.getAllAdminsFromPaser(ParserType.STAX));
                        request.setAttribute("parsertype", "stax");
                        break;
                    }
                    default: {
                        request.setAttribute("admins", adminService.getAllAdminsFromPaser(ParserType.DOM));
                        request.setAttribute("parsertype", "No parsertype specified... Using dom parser");
                    }
                }
            } else {
                request.setAttribute("admins", adminService.getAllAdminsFromPaser(ParserType.DOM));
                request.setAttribute("parsertype", "No parsertype specified... Using dom parser");
            }
        } catch (ServiceException s) {
            System.out.println(s.getMessage());
        }
        getServletContext().getRequestDispatcher("/admins").forward(request, response);
    }

}
