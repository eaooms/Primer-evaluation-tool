package nl.bioinf_eao_primer.primerTool;

import nl.bioinf_eao_primer.primerTool.service.PrimerAnalysisService;
import nl.bioinf_eao_primer.primerTool.webcontrol.PrimerController;
import org.junit.jupiter.api.Test;
import nl.bioinf_eao_primer.primerTool.model.Primer;
import nl.bioinf_eao_primer.primerTool.model.PrimerAnalysisResult;

import static org.junit.jupiter.api.Assertions.*;

class PrimerAnalysisServiceTest {

    PrimerAnalysisService service = new PrimerAnalysisService();
    PrimerController controller = new PrimerController();
    Primer primer = new Primer();

    @Test
    public void testValidSequences() {
        primer.setSequence("5’-ACTGCATGGCATCGCAGCT-3’");
        assertTrue(controller.isValidSequence(primer.getSequence()));

        primer.setSequence("5'-ACTGCATGGCATCGCAGCT");
        assertTrue(controller.isValidSequence(primer.getSequence()));

        primer.setSequence("5’-ACTGCATGGCATCGCBBBB-3’");
        assertFalse(controller.isValidSequence(primer.getSequence()));

        primer.setSequence("5'-ACTGCATGGCATCGCAG-3'CT-3'");
        assertFalse(controller.isValidSequence(primer.getSequence()));
    }

    @Test
    void testGcPercentageCalculation() {
        Primer primer = new Primer("TestPrimer", "GCGCGCATAT");
        PrimerAnalysisResult result = service.analyze(primer, null, false);

        double expectedGC = 60.0;
        assertEquals(expectedGC, result.getGcPercentage(), 0.01);
    }

    @Test
    void testMeltingPointCalculation() {
        Primer primer = new Primer("MeltTest", "ATGC");
        PrimerAnalysisResult result = service.analyze(primer, null, false);

        int expectedTm = 12;
        assertEquals(expectedTm, result.getMeltingPoint());
    }

    @Test
    void testMaxHomopolymer() {
        Primer primer = new Primer("StretchTest", "AAAGGGGTT");
        PrimerAnalysisResult result = service.analyze(primer, null, false);

        int expectedMaxStretch = 4;
        assertEquals(expectedMaxStretch, result.getMaxHomopolymer());
    }
}