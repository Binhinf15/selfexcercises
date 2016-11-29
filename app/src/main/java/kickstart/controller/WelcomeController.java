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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kickstart.person.Kunde;
import kickstart.person.KundenFormular;

@Controller
public class WelcomeController {

	@RequestMapping({"/","/welcome"})
	public String index() {
		return "welcome";
	}

	@RequestMapping("/kontakt")
	public String kon() {
		return "kontakt";
	}

	@RequestMapping("/suche")
	public String such() {
		return "suche";
	}

	@RequestMapping("/impressum")
	public String imp() {
		return "impressum";
	}

	@RequestMapping("/wegbeschreibung")
	public String wegbeschr() {
		return "wegbeschreibung";
	}






	

	@RequestMapping("/personal")
	public String per() {
		return "personal";
	}



	@RequestMapping("/lager")
	public String lag() {
		return "lager";
	}

	@RequestMapping("/mein-bereich")
	public String main_mb() {
		return "mein-bereich";
	}

	@RequestMapping("/service")
	public String ser() {
		return "service";
	}



	@RequestMapping("/bestellung")
	public String ser_b() {
		return "bestellung";
	}



}
