package nl.bioinf_eao_primer.primerTool.webcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import nl.bioinf_eao_primer.primerTool.model.Primer;
import nl.bioinf_eao_primer.primerTool.model.PrimerAnalysisResult;
import nl.bioinf_eao_primer.primerTool.service.PrimerAnalysisService;

@Controller
public class PrimerController {

    @Autowired
    private PrimerAnalysisService analysisService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("forwardPrimer", new Primer());
        model.addAttribute("reversePrimer", new Primer());
        model.addAttribute("history", analysisService.getHistory());
        return "index";
    }

    @PostMapping("/analyze")
    public String analyze(
            @RequestParam("forwardName") String forwardName,
            @RequestParam("forwardSequence") String forwardSequence,
            @RequestParam(value = "reverseName", required = false) String reverseName,
            @RequestParam(value = "reverseSequence", required = false) String reverseSequence,
            @RequestParam(value = "isRerun", defaultValue = "false") boolean isRerun,
            Model model) {

        Primer forwardPrimer = new Primer(forwardName, forwardSequence);
        Primer reversePrimer = new Primer(reverseName, reverseSequence);

        if (!isValidSequence(forwardPrimer.getSequence()) ||
                (!reversePrimer.getSequence().isEmpty() && !isValidSequence(reversePrimer.getSequence()))) {
            model.addAttribute("error", "The forward or reverse primer contains an invalid sequence. Only the letters A, C, G, and T are allowed.");
            model.addAttribute("history", analysisService.getHistory());
            return "index";
        }

        PrimerAnalysisResult result = analysisService.analyze(forwardPrimer, reversePrimer, isRerun);
        model.addAttribute("result", result);
        model.addAttribute("history", analysisService.getHistory());
        return "index";
    }

    public boolean isValidSequence(String sequence) {
        return sequence != null && sequence.matches("[atcgACGT]+$");
    }
}