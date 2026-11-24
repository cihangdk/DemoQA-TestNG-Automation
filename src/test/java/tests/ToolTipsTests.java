package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ToolTipsPage;
import utilities.TestBase;

public class ToolTipsTests extends TestBase {

    private ToolTipsPage toolTipsPage;

    // Her testten önce yeni page nesnesi oluştur ve sayfayı aç
    @BeforeMethod
    public void setUp() {
        toolTipsPage = new ToolTipsPage();
        toolTipsPage.sayfayiAc();
    }

    /*
     * Fareyi "Hover me to see" butonunun üzerine getirir.
     * Beklenen: "You hovered over the Button" tooltip'i görünmeli.
     */
    @Test
    public void butonUzerineGelinceTooltipGorunmeli() {
        toolTipsPage.butonunUzerineGel();

        Assert.assertEquals(toolTipsPage.butonTooltipMetniniAl(), "You hovered over the Button");
    }

    /*
     * Fareyi metin kutusunun üzerine getirir.
     * Beklenen: "You hovered over the text field" tooltip'i görünmeli.
     */
    @Test
    public void metinKutusuUzerineGelinceTooltipGorunmeli() {
        toolTipsPage.metinKutusununUzerineGel();

        Assert.assertEquals(toolTipsPage.metinKutusuTooltipMetniniAl(), "You hovered over the text field");
    }
}