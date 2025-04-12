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
        result.setMeltingPoint(calculateMeltingPoint(seq));
        result.setMaxHomopolymer(calculateMaxHomopolymer(seq));

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

    // Melt temperatuur berekenen met : Tm = 4*(#GC) + 2*(#AT)
    private int calculateMeltingPoint(String sequence) {
        if (sequence == null) {
            return 0;
        }
        int gcCount = 0, atCount = 0;
        for (char c : sequence.toCharArray()) {
            if (c == 'G' || c == 'C') {
                gcCount++;
            } else if (c == 'A' || c == 'T') {
                atCount++;
            }
        }
        return 4 * gcCount + 2 * atCount;
    }

    // Bereken de maximale homopolymeer stretch
    private int calculateMaxHomopolymer(String sequence) {
        if(sequence == null || sequence.isEmpty()){
            return 0;
        }
        int maxStretch = 1;
        int currentStretch = 1;
        char[] chars = sequence.toUpperCase().toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                currentStretch++;
                if (currentStretch > maxStretch) {
                    maxStretch = currentStretch;
                }
            } else {
                currentStretch = 1;
            }
        }
        return maxStretch;
    }
}