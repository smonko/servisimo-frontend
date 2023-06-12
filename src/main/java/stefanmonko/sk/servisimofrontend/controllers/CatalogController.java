package stefanmonko.sk.servisimofrontend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import stefanmonko.sk.servisimofrontend.IntegrationProperties;
import stefanmonko.sk.servisimofrontend.domain.Component;
import stefanmonko.sk.servisimofrontend.domain.Severity;
import stefanmonko.sk.servisimofrontend.domain.Status;
import stefanmonko.sk.servisimofrontend.domain.Worker;

@Controller
public class CatalogController {

    @Autowired
    private IntegrationProperties integrationProperties;

    @GetMapping ("/catalog")
    public String catalog(Model model) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String uriComponents= integrationProperties.getCatalog() + "/components";
        String uriStatuses= integrationProperties.getCatalog() + "/statuses";
        String uriSeverities= integrationProperties.getCatalog() + "/severities";
        String uriWorkers= integrationProperties.getCatalog() + "/workers";

        RestTemplate restTemplate = new RestTemplate(); 

        ResponseEntity<List<Component>> responseComponents = restTemplate.exchange(uriComponents, HttpMethod.GET, null, new ParameterizedTypeReference<List<Component>>() {});

        ResponseEntity<List<Status>> responseStatuses = restTemplate.exchange(uriStatuses, HttpMethod.GET, null, new ParameterizedTypeReference<List<Status>>() {});

        ResponseEntity<List<Severity>> responseSeverities = restTemplate.exchange(uriSeverities, HttpMethod.GET, null, new ParameterizedTypeReference<List<Severity>>() {});

        ResponseEntity<List<Worker>> responseWorkers = restTemplate.exchange(uriWorkers, HttpMethod.GET, null, new ParameterizedTypeReference<List<Worker>>() {});

        List<Component> components = responseComponents.getBody();
        List<Status> statuses = responseStatuses.getBody();
        List<Severity> severities = responseSeverities.getBody();
        List<Worker> workers = responseWorkers.getBody();

        model.addAttribute("components", components);
        model.addAttribute("statuses", statuses);
        model.addAttribute("severities", severities);
        model.addAttribute("workers", workers);
        model.addAttribute("loggedUser", authentication.getName());

        return "catalog";
        
    }

}
