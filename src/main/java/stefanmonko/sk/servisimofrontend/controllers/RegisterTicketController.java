package stefanmonko.sk.servisimofrontend.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import stefanmonko.sk.servisimofrontend.IntegrationProperties;
import stefanmonko.sk.servisimofrontend.domain.Component;
import stefanmonko.sk.servisimofrontend.domain.Severity;
import stefanmonko.sk.servisimofrontend.domain.Status;
import stefanmonko.sk.servisimofrontend.domain.Ticket;

@Controller
public class RegisterTicketController {

    @Autowired
    private IntegrationProperties integrationProperties;

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.addDialect(new Java8TimeDialect());
    engine.setTemplateResolver(templateResolver);
    return engine;
}

    @GetMapping ("/registerticket")
    public String catalog(Model model) {

        String uriComponents= integrationProperties.getCatalog() + "/components";
        String uriStatuses= integrationProperties.getCatalog() + "/statuses";
        String uriSeverities= integrationProperties.getCatalog() + "/severities";

        RestTemplate restTemplate = new RestTemplate(); 

        ResponseEntity<List<Component>> responseComponents = restTemplate.exchange(uriComponents, HttpMethod.GET, null, new ParameterizedTypeReference<List<Component>>() {});

        ResponseEntity<List<Status>> responseStatuses = restTemplate.exchange(uriStatuses, HttpMethod.GET, null, new ParameterizedTypeReference<List<Status>>() {});

        ResponseEntity<List<Severity>> responseSeverities = restTemplate.exchange(uriSeverities, HttpMethod.GET, null, new ParameterizedTypeReference<List<Severity>>() {});

        List<Component> components = responseComponents.getBody();
        List<Status> statuses = responseStatuses.getBody();
        List<Severity> severities = responseSeverities.getBody();

        Ticket ntTicket =  new Ticket();

        model.addAttribute("components", components);
        model.addAttribute("statuses", statuses);
        model.addAttribute("severities", severities);
        model.addAttribute("ntTicket", ntTicket);
        model.addAttribute("localDate", LocalDate.now());

        return "registerticket";
        
    }

}