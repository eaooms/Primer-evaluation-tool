package nl.bioinf_eao_primer.primerTool.model;

public class PrimerAnalysisResult {
    private Primer forward;
    private Primer reverse;
    private double gcPercentage;

    public PrimerAnalysisResult() {}

    // Getters en setters

    public Primer getForward() {
        return forward;
    }

    public void setForward(Primer forward) {
        this.forward = forward;
    }

    public Primer getReverse() {
        return reverse;
    }

    public void setReverse(Primer reverse) {
        this.reverse = reverse;
    }

    public double getGcPercentage() {
        return gcPercentage;
    }

    public void setGcPercentage(double gcPercentage) {
        this.gcPercentage = gcPercentage;
    }
}