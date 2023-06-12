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

import stefanmonko.sk.servisimofrontend.FeatureProperties;
import stefanmonko.sk.servisimofrontend.IntegrationProperties;
import stefanmonko.sk.servisimofrontend.domain.Severity;
import stefanmonko.sk.servisimofrontend.domain.Status;
import stefanmonko.sk.servisimofrontend.domain.Ticket;



@Controller
public class TicketsController {

    @Autowired
    private FeatureProperties featureFlags;

    @Autowired
    private IntegrationProperties integrationProperties;


    @GetMapping ("/tickets")
    public String tickets(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String uriViewerTicketsAll= integrationProperties.getViewer() + "/tickets";
        String uriStatuses= integrationProperties.getCatalog() + "/statuses";
        String uriSeverities= integrationProperties.getCatalog() + "/severities";

        RestTemplate restTemplate = new RestTemplate(); 

        ResponseEntity<List<Ticket>> responseTickets = restTemplate.exchange(uriViewerTicketsAll, HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {});
        ResponseEntity<List<Status>> responseStatuses = restTemplate.exchange(uriStatuses, HttpMethod.GET, null, new ParameterizedTypeReference<List<Status>>() {});
        ResponseEntity<List<Severity>> responseSeverities = restTemplate.exchange(uriSeverities, HttpMethod.GET, null, new ParameterizedTypeReference<List<Severity>>() {});
        
        List<Ticket> tickets = responseTickets.getBody();
        List<Status> statuses = responseStatuses.getBody();
        List<Severity> severities = responseSeverities.getBody();

        // System.out.println("tickets: " + tickets);

        model.addAttribute("tickets", tickets);
        model.addAttribute("statuses", statuses);
        model.addAttribute("severities", severities);
        model.addAttribute("cataloglist", featureFlags.isCataloglist());
        model.addAttribute("reportTicket", featureFlags.isReportticket());
        model.addAttribute("loggedUser", authentication.getName());

        return "tickets";
    }

}
    

