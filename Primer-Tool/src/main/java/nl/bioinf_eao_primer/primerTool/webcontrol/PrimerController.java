package nl.bioinf_eao_primer.primerTool.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import nl.bioinf_eao_primer.primerTool.model.Primer;
import nl.bioinf_eao_primer.primerTool.model.PrimerAnalysisResult;
import nl.bioinf_eao_primer.primerTool.service.PrimerAnalysisService;

@Controller
public class PrimerController {

    @Autowired
    private PrimerAnalysisService analysisService;

    @GetMapping("/")
    public String showForm(Model model) {
        //Lege primer objecten
        model.addAttribute("forwardPrimer", new Primer());
        model.addAttribute("reversePrimer", new Primer());
        return "index";
    }

    @PostMapping("/analyze")
    public String analyze(
            @RequestParam("forwardName") String forwardName,
            @RequestParam("forwardSequence") String forwardSequence,
            @RequestParam(value = "reverseName", required = false) String reverseName,
            @RequestParam(value = "reverseSequence", required = false) String reverseSequence,
            Model model) {

        // Validatie: sequenties mogen alleen acgt/ACGT bevatten
        if(!isValidSequence(forwardSequence) || (!StringUtils.isEmpty(reverseSequence) && !isValidSequence(reverseSequence))) {
            model.addAttribute("error", "Ongeldige sequentie ingevoerd. Alleen acgt/ACGT is toegestaan.");
            return "index";
        }

        Primer forwardPrimer = new Primer(forwardName, forwardSequence);
        Primer reversePrimer = new Primer(reverseName, reverseSequence);

        PrimerAnalysisResult result = analysisService.analyze(forwardPrimer, reversePrimer);
        model.addAttribute("result", result);
        return "index";
    }

    private boolean isValidSequence(String sequence) {
        return sequence != null && sequence.matches("[acgtACGT]+");
    }
}