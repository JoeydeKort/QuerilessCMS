package be.intec.querilesscms.controllers.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImpl implements ErrorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error/error-400";
            }

            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error/error-401";
            }

            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/error-403";
            }

            else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }

            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            }

            log.error("Unknown error occurred!");

        }
        return "error/error";
    }

}
