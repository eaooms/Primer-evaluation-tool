package nl.bioinf_eao_primer.primerTool.service;

import nl.bioinf_eao_primer.primerTool.model.Primer;
import nl.bioinf_eao_primer.primerTool.model.PrimerAnalysisResult;

import org.springframework.stereotype.Service;

@Service
public class PrimerAnalysisService {

    public PrimerAnalysisResult analyze(Primer forward, Primer reverse) {
        PrimerAnalysisResult result = new PrimerAnalysisResult();
        result.setForward(forward);
        result.setReverse(reverse);

        String seq = forward.getSequence();
        result.setGcPercentage(calculateGCPercentage(seq));

        return result;
    }

    // Bereken GC percentage
    private double calculateGCPercentage(String sequence) {
        if(sequence == null || sequence.isEmpty()){
            return 0;
        }
        int gcCount = 0;
        for(char c : sequence.toUpperCase().toCharArray()){
            if(c == 'G' || c == 'C'){
                gcCount++;
            }
        }
        return 100.0 * gcCount / sequence.length();
    }
}