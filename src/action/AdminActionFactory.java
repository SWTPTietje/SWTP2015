/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

/**
 *
 * @author erfier
 */
public class AdminActionFactory implements ActionFactory {

    public static AdminActionFactory getInstance() {
        return AdminActionFactoryHolder.INSTANCE;
    }

    private AdminActionFactory() {
    }

    @Override
    public Action getActionByName(String actionName) {
        switch (actionName) {
            case "register_from_users":
                return new RegisterAction();
            case "deleteUser":
                return new DeleteUserAction();
            case "changeUser":
                return new ChangeUserAction();
            case "changefilespath":
            	return new ChangeFilespathAction();
            default:
                return null;
        }
    }

    private static class AdminActionFactoryHolder {

        private static final AdminActionFactory INSTANCE = new AdminActionFactory();
    }
}
