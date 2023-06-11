package stefanmonko.sk.servisimofrontend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import stefanmonko.sk.servisimofrontend.FeatureProperties;

@Controller
public class FeaturesController {
    
    @Autowired
    private FeatureProperties featureFlags;

    @RequestMapping("/features")
    public String featutures(Model model) {

        Map <String, Boolean> featuresValues = new HashMap<>();
        featuresValues.put("canaryflag", featureFlags.isCanaryflag());
        featuresValues.put("reportticket", featureFlags.isReportticket());
        featuresValues.put("advancefilter", featureFlags.isAdvancefilter());
        featuresValues.put("cataloglist", featureFlags.isCataloglist());


        model.addAttribute("features", featuresValues);

        return "features";
    }
}

