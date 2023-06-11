package stefanmonko.sk.servisimofrontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import stefanmonko.sk.servisimofrontend.FeatureProperties;

@RestController
public class FeaturesConfigController {
    @Autowired
    private FeatureProperties featureFlags;

    @RequestMapping(path = "/feature-flags", produces="application/json")
    public Map<String, String> showFeatures() {
    HashMap<String, String> map = new HashMap<>();

    String canaryFlag = String.valueOf(featureFlags.isCanaryflag());
    String reportTicket = String.valueOf(featureFlags.isReportticket());
    String advanceFilter = String.valueOf(featureFlags.isAdvancefilter());

    map.put("canaryflag", canaryFlag);
    map.put("reportticket", reportTicket);
    map.put("advancefilter", advanceFilter);

    return map;

    }
}
