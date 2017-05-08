/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Welcome controller.
 */
@Controller
public class WelcomeController {

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping({"/","/welcome"})
	public String index() {
		return "welcome";
	}

    /**
     * Kon string.
     *
     * @return the string
     */
    @RequestMapping("/kontakt")
	public String kon() {
		return "kontakt";
	}

    /**
     * Such string.
     *
     * @return the string
     */
    @RequestMapping("/suche")
	public String such() {
		return "suche";
	}

    /**
     * Imp string.
     *
     * @return the string
     */
    @RequestMapping("/impressum")
	public String imp() {
		return "impressum";
	}

    /**
     * Wegbeschr string.
     *
     * @return the string
     */
    @RequestMapping("/wegbeschreibung")
	public String wegbeschr() {
		return "wegbeschreibung";
	}

    /**
     * Ser string.
     *
     * @return the string
     */
    @RequestMapping("/service")
	public String ser() {
		return "service";
	}
}
