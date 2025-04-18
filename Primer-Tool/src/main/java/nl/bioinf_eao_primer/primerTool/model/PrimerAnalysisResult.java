package nl.bioinf_eao_primer.primerTool.model;

public class PrimerAnalysisResult {
    private Primer forward;
    private Primer reverse;
    private double gcPercentage;
    private int meltingPoint;
    private int maxHomopolymer;
    private int max3IntermolecularIdentity;
    private int max3IntramolecularIdentity;

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

    public int getMeltingPoint() {return meltingPoint;}

    public void setMeltingPoint(int meltingPoint) {this.meltingPoint = meltingPoint;}

    public int getMaxHomopolymer() {return maxHomopolymer;}

    public void setMaxHomopolymer(int maxHomopolymer) {this.maxHomopolymer = maxHomopolymer;}

    public int getMax3IntermolecularIdentity() {
        return max3IntermolecularIdentity;
    }

    public void setMax3IntermolecularIdentity(int max3IntermolecularIdentity) {
        this.max3IntermolecularIdentity = max3IntermolecularIdentity;
    }

    public int getMax3IntramolecularIdentity() {
        return max3IntramolecularIdentity;
    }

    public void setMax3IntramolecularIdentity(int max3IntramolecularIdentity) {
        this.max3IntramolecularIdentity = max3IntramolecularIdentity;
    }
}