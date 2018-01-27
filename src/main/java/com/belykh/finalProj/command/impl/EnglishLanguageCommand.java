package com.belykh.finalProj.command.impl;


import com.belykh.finalProj.command.ActionCommand;
import com.belykh.finalProj.controller.AuctionServlet;
import com.belykh.finalProj.exception.CommandException;
import com.belykh.finalProj.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by panda on 6.12.17.
 */
public class EnglishLanguageCommand implements ActionCommand {
    private final static Logger LOG = LogManager.getLogger(EnglishLanguageCommand.class);

    private final static String COOKIE_NAME = "locale";

    private final static String COOKIE_VALUE = "en_US";

    private final static int COOKIE_AGE_IN_SEC = 86_400;

    private static final String PARAM_HEADER_REFERER = "referer";

    private static final String PARAM_NAME_SERVLET = "auction?";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Locale.setDefault(Locale.US);
        AuctionServlet.resourceManager.changeLocale(Locale.US);
        AuctionServlet.messageManager.changeLocale(Locale.US);
        Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
        cookie.setMaxAge(COOKIE_AGE_IN_SEC);
        response.addCookie(cookie);
        HttpSession session = request.getSession(true);
        session.setAttribute("lang", COOKIE_VALUE);
        request.setAttribute("lang", COOKIE_VALUE);

        try {
            URL url = new URL(request.getHeader(PARAM_HEADER_REFERER));

            request.setAttribute("redirect", PARAM_NAME_SERVLET + url.getQuery());

        } catch (MalformedURLException e) {
            throw new CommandException(e);
        }
        if (session.getAttribute("user") != null) {
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}