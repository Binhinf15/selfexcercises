package kickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Programm controller.
 */
@Controller
public class ProgrammController {

    /**
     * Login string.
     *
     * @return the string
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Main string.
     *
     * @return the string
     */
    @RequestMapping("/main")
    public String main() {
        return "main";
    }

}
