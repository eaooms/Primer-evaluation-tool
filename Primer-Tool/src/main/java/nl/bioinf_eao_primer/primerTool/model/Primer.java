package nl.bioinf_eao_primer.primerTool.model;

public class Primer {
    private String name;
    private String sequence;

    public Primer() {}

    public Primer(String name, String sequence) {
        this.name = name;
        this.sequence = sequence;
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
        this.sequence = sequence;
    }
}