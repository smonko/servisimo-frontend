package stefanmonko.sk.servisimofrontend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import stefanmonko.sk.servisimofrontend.FeatureProperties;
import stefanmonko.sk.servisimofrontend.IntegrationProperties;
import stefanmonko.sk.servisimofrontend.domain.Ticket;



@Controller
public class TicketsController {

    @Autowired
    private FeatureProperties featureFlags;

    @Autowired
    private IntegrationProperties integrationProperties;


    @GetMapping ("/tickets")
    public String tickets(Model model) {

        String uriViewerTicketsAll= integrationProperties.getViewer() + "/tickets";

        RestTemplate restTemplate = new RestTemplate(); 
        ResponseEntity<List<Ticket>> response = 
            restTemplate.exchange(uriViewerTicketsAll, HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {});

        List<Ticket> tickets = response.getBody();

        System.out.println("tickets: " + tickets);

        model.addAttribute("tickets", tickets);

        return "tickets";
    }

}
    

