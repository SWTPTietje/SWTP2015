package issuetracking;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionFactory;
import action.UserActionFactory;
import javax.ejb.EJB;

/**
 * Servlet implementation class Controller
 */
@WebServlet
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String USER_JSP_PATH = "/userpages";

    public static String setString(String str, int max) {
        String str2 = str.length() > max ? str.substring(0, max) : str;
        return str2;
    }
    @EJB
    private DBManager DBManager1;
    private ActionFactory actionFactory = UserActionFactory.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String requestedPage = "/user/index.jsp";
        if (request.getPathInfo() != null) {
            requestedPage = USER_JSP_PATH.concat(request.getPathInfo());
        }
        if (request.getQueryString() != null) {
            requestedPage = requestedPage.replaceAll(request.getQueryString(), "");
        }
        String action = request.getParameter("action");
        if (action != null) {
            Action aktion = actionFactory.getActionByName(action);

            if (aktion != null) {
                request.setAttribute("dao", DBManager1);
                String result = aktion.execute(request, response);
                if (result != null) {
                    requestedPage = result;
                }
            } else {
                requestedPage = "/user/index.jsp";
            }
        }
        requestedPage = requestedPage.replaceFirst(request.getServletPath() + "/", USER_JSP_PATH + "/");
        preparePage(requestedPage, request, response);
        request.getRequestDispatcher(requestedPage).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    /**
     * Bereitet die Parameter für die entsprechende Seite vor
     *
     * @param pageName Name der aufgerufenen Seite
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void preparePage(String pageName, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        if (pageName.endsWith("index.jsp")) {
            request.setAttribute("users", DBManager1.getUsers());
            request.setAttribute("tickets_open", DBManager1.getTicketsByState("open", -2));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_closed", DBManager1.getTicketsByState("closed", -2));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_inprogress", DBManager1.getTicketsByState("in_progress", -2));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_test", DBManager1.getTicketsByState("test", -2));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("thissprintsid", "-2");

            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ft.format(dNow);
            request.setAttribute("date1", date1);

            request.setAttribute("compids", DBManager1.getComponents());
        }

        if (pageName.endsWith("sprintstickets.jsp")) {
            request.setAttribute("users", DBManager1.getUsers());
            request.setAttribute("tickets_open", DBManager1.getTicketsByState("open", Integer.parseInt(request.getParameter("sprintid"))));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_closed", DBManager1.getTicketsByState("closed", Integer.parseInt(request.getParameter("sprintid"))));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_inprogress", DBManager1.getTicketsByState("in_progress", Integer.parseInt(request.getParameter("sprintid"))));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_test", DBManager1.getTicketsByState("test", Integer.parseInt(request.getParameter("sprintid"))));//,Integer.parseInt(request.getParameter("sprintid"))));

            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ft.format(dNow);
            request.setAttribute("date1", date1);

            request.setAttribute("compids", DBManager1.getComponents());
        }

        if (pageName.endsWith("sprintDetail.jsp")) {
            request.setAttribute("users", DBManager1.getUsers());
            request.setAttribute("tickets_open", DBManager1.getTicketsByState("open", Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_closed", DBManager1.getTicketsByState("closed", Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_inprogress", DBManager1.getTicketsByState("in_progress", Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("tickets_test", DBManager1.getTicketsByState("test", Integer.parseInt(request.getParameter("sprintid"))));

            request.setAttribute("nosprinttickets_open", DBManager1.getTicketsByState("open", -1));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("nosprinttickets_closed", DBManager1.getTicketsByState("closed", -1));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("nosprinttickets_inprogress", DBManager1.getTicketsByState("in_progress", -1));//,Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("nosprinttickets_test", DBManager1.getTicketsByState("test", -1));//,Integer.parseInt(request.getParameter("sprintid"))));

            request.setAttribute("thissprint", DBManager1.getSprintById(Integer.parseInt(request.getParameter("sprintid"))));
            request.setAttribute("activesprint", DBManager1.getActiveSprint());

            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ft.format(dNow);
            request.setAttribute("date1", date1);

            request.setAttribute("compids", DBManager1.getComponents());
        }

        if (pageName.endsWith("ticketview.jsp")) {
            Ticket t2 = DBManager1.getTicketById(Integer.parseInt(request.getParameter("ticket_id")));
            request.setAttribute("t1", t2);

            request.setAttribute("users", DBManager1.getUsers());

            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = ft.format(dNow);
            request.setAttribute("date1", date1);

            request.setAttribute("ticket_compids", DBManager1.getComponentsByTicket(
                    Integer.parseInt(request.getParameter("ticket_id"))));
            request.setAttribute("compids", DBManager1.getComponents());

            request.setAttribute("ticket_pictures", DBManager1.getPicturesByTicket(Integer.parseInt(request.getParameter("ticket_id"))));

            SimpleDateFormat ft2 = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            String date2 = ft2.format(dNow);
            request.setAttribute("date2", date2);
        }

        if (pageName.endsWith("userpage.jsp")) {
            User u1 = DBManager1.getUserByUserid(request.getParameter(request.getUserPrincipal().getName().toString()));
            request.setAttribute("u1", u1);
        }
        if (pageName.endsWith("login.jsp")) {

        }

        if (pageName.endsWith("components.jsp")) {
            request.setAttribute("users", DBManager1.getUsers());
            request.setAttribute("components", DBManager1.getComponents());
        }

        if (pageName.endsWith("componentview.jsp")) {
            Integer compid = Integer.parseInt(request.getParameter("compid"));
            Component c1 = DBManager1.getComponentById(compid);
            request.setAttribute("c1", c1);
        }
        if (pageName.endsWith("commentview.jsp")) {
            Comment comment1 = DBManager1.getCommentById(Integer.parseInt(request.getParameter("comment_id")));
            request.setAttribute("c1", comment1);
        }

        if (pageName.endsWith("sprints.jsp")) {
            request.setAttribute("activesprint", DBManager1.getActiveSprint());
            request.setAttribute("sprints", DBManager1.getSprints());
            request.setAttribute("nosprinttickets", DBManager1.getTicketsByState("beliebig", -1));

        }
    }



}
