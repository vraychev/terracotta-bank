package com.joshcummings.codeplay.terracotta.app;

import javax.servlet.http.HttpServlet;

public abstract class ApplicationAwareServlet extends HttpServlet {
    protected ApplicationContext context;

    @Override
    public void init() {
        context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
    }
}
