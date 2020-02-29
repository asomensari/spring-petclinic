
package org.springframework.samples.petclinic.system;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.newrelic.api.agent.NewRelic;

@Controller
class WelcomeController {

	// Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@GetMapping("/")
	public String welcome() {

		NewRelic.addCustomParameter("user", "joedoe");
		NewRelic.addCustomParameter("randomVal", Math.random() * 100);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", "testUser");
		params.put("another", "value");
		params.put("number", 123);

		NewRelic.getAgent().getInsights().recordCustomEvent("MyCustomEvent", params);

		NewRelic.noticeError("Expected Error", params, true);

		logger.debug("**************************************************** welcome debug log");
		logger.info("**************************************************** welcome debug log");
		logger.warn("**************************************************** welcome debug log");

		return "welcome";
	}

}
