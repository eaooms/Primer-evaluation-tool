package nl.bioinf_eao_primer.primerTool.service;

import nl.bioinf_eao_primer.primerTool.model.Primer;
import nl.bioinf_eao_primer.primerTool.model.PrimerAnalysisResult;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PrimerAnalysisService {

    private static final int HISTORY_LIMIT = 5;
    private LinkedList<PrimerAnalysisResult> history = new LinkedList<>();

    public PrimerAnalysisResult analyze(Primer forward, Primer reverse) {
        PrimerAnalysisResult result = new PrimerAnalysisResult();
        result.setForward(forward);
        result.setReverse(reverse);

        // Make sure the forward primer is present
        if (forward != null && forward.getSequence() != null) {
            String seqF = forward.getSequence().toUpperCase();
            result.setGcPercentage(calculateGCPercentage(seqF));
            result.setMeltingPoint(calculateMeltingPoint(seqF));
            result.setMaxHomopolymer(calculateMaxHomopolymer(seqF));
            result.setMax3IntramolecularIdentity(calculateMax3IntramolecularIdentity(seqF));
        }

        // If both primers are provided, calculate 3'-intermolecular identity
        if (forward != null && reverse != null &&
                forward.getSequence() != null && reverse.getSequence() != null) {
            String seqF = forward.getSequence().toUpperCase();
            String seqR = reverse.getSequence().toUpperCase();
            result.setMax3IntermolecularIdentity(calculateMax3IntermolecularIdentity(seqF, seqR));
        }

        // Add to history
        addToHistory(result);

        return result;
    }

    private void addToHistory(PrimerAnalysisResult result) {
        history.addFirst(result);
        if (history.size() > HISTORY_LIMIT) {
            history.removeLast();
        }
    }

    public List<PrimerAnalysisResult> getHistory() {
        return history;
    }

    // Calculate GC percentage
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

    // Calculate melting temperature using: Tm = 4*(#GC) + 2*(#AT)
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

    // Calculate the maximum homopolymer stretch
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

    // Calculate maximum 3'-intermolecular identity
    private int calculateMax3IntermolecularIdentity(String seqForward, String seqReverse) {
        String seqR_rc = reverseComplement(seqReverse);
        int maxMatch = 0;
        int maxPossible = Math.min(seqForward.length(), seqR_rc.length());

        for (int n = 1; n <= maxPossible; n++) {
            String tailF = seqForward.substring(seqForward.length() - n);
            String headR = seqR_rc.substring(0, n);
            if (tailF.equals(headR)) {
                maxMatch = n;
            }
        }
        return maxMatch;
    }

    // Calculate maximum 3'-intramolecular identity
    private int calculateMax3IntramolecularIdentity(String seq) {
        String rc = reverseComplement(seq);
        int n = seq.length();
        int maxMatch = 0;

        for (int overlap = 1; overlap < n; overlap++) {
            String tailSeq = seq.substring(n - overlap);
            String headRC  = rc.substring(0, overlap);

            if (tailSeq.equals(headRC)) {
                maxMatch = overlap;
            }
        }
        return maxMatch;
    }

    // Calculate the reverse complement of a DNA sequence.
    private String reverseComplement(String seq) {
        StringBuilder sb = new StringBuilder();
        for (int i = seq.length() - 1; i >= 0; i--) {
            char c = seq.charAt(i);
            switch (c) {
                case 'A':
                    sb.append('T');
                    break;
                case 'T':
                    sb.append('A');
                    break;
                case 'C':
                    sb.append('G');
                    break;
                case 'G':
                    sb.append('C');
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
}