package com.belykh.finalProj.command.impl.admin;

import com.belykh.finalProj.command.ActionCommand;
import com.belykh.finalProj.constant.PathPage;
import com.belykh.finalProj.controller.AuctionServlet;
import com.belykh.finalProj.entity.UserInfo;
import com.belykh.finalProj.exception.CommandException;
import com.belykh.finalProj.exception.ServiceException;
import com.belykh.finalProj.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class UserListCommand.
 */
public class UserListCommand implements ActionCommand {
    
    /* (non-Javadoc)
     * @see com.belykh.finalProj.command.ActionCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = null;

        UserService service = serviceFactory.getUserService();
        try {
            List<UserInfo> userList=service.findUsersInfo();
            if (userList.isEmpty()) {
                request.setAttribute("userList", null);
                request.setAttribute("errorUserListIsEmpty", AuctionServlet.messageManager.getProperty("message.errorUserListIsEmpty"));
            } else {
                request.setAttribute("errorUserListIsEmpty", null);
                request.setAttribute("userList", userList);
            }
            result = PathPage.USER_LIST.getPath();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return result;
    }
}
