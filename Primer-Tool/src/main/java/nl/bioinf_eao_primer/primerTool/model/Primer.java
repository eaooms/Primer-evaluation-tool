package nl.bioinf_eao_primer.primerTool.model;

public class Primer {
    private String name;
    private String sequence;

    public Primer() {}

    public Primer(String name, String sequence) {
        this.name = name;
        this.sequence = cleanSequence(sequence);
    }

    // Remove the 5' and 3' from the sequence, if present
    public String cleanSequence(String sequence) {
        if (sequence == null) return "";
        sequence = sequence.replaceFirst("^5?[’']?-?", "");
        sequence = sequence.replaceFirst("-?3?[’']?$", "");
        return sequence.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = cleanSequence(sequence);
    }
}