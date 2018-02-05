package com.belykh.finalProj.command.impl.lot;

import com.belykh.finalProj.command.ActionCommand;
import com.belykh.finalProj.constant.PathPage;
import com.belykh.finalProj.exception.CommandException;
import com.belykh.finalProj.exception.ServiceException;
import com.belykh.finalProj.service.LotService;
import com.belykh.finalProj.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;

// TODO: Auto-generated Javadoc
/**
 * The Class PayLotCommand.
 */
public class PayLotCommand implements ActionCommand{
    private static final String PARAM_HEADER_REFERER = "referer";

    private static final String PARAM_NAME_SERVLET = "auction?";

    /* (non-Javadoc)
     * @see com.belykh.finalProj.command.ActionCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = null;
        HttpSession session = request.getSession(false);
        Long ownerId = (Long) session.getAttribute("userId");
        String lotId = request.getParameter("id");

        if(ParameterValidator.getInstance().validateId(lotId)) {
            LotService service = serviceFactory.getLotService();
            try {
                service.payLot(Long.decode(lotId));
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }

        try {
            URL url = new URL(request.getHeader(PARAM_HEADER_REFERER));

            request.setAttribute("redirect", PARAM_NAME_SERVLET + url.getQuery());

        } catch (MalformedURLException e) {
            throw new CommandException(e);
        }

        result = PathPage.MAIN.getPath();
        return result;
    }
}
