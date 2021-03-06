package action;

import issuetracking.Component;
import issuetracking.DBManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddComponentAction implements Action {

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {

        DBManager DBManager1 = (DBManager) request.getAttribute("dao");
        Map<String, String> errorMsgs = new HashMap<String, String>();

        String nameinput = request.getParameter("name");
        String descriptioninput = request.getParameter("description");
        Component comp = new Component(nameinput, descriptioninput);

        errorMsgs = comp.validate();

        if (errorMsgs.isEmpty()) {
            DBManager1.saveComponent(comp);
        }

        request.setAttribute("errorMsgs", errorMsgs);
        return "/user/components.jsp";
    }

}
