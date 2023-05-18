package Controller;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FluxoController {
    public static Controller.DriverController driverController;
    public FluxoController(){

    }
    public static void Automacao()
    {
        try
        {
            driverController = new Controller.DriverController();
            System.out.println("--- Iniciando automação ---");
            WebDriver navegador = driverController.Driver();

            navegador.get("https://www.mercadolivre.com.br/");
            navegador.findElement(By.xpath("//*[@id=\"cb1-edit\"]")).click();
            navegador.findElement(By.xpath("//*[@id=\"cb1-edit\"]")).sendKeys("Xbox Serie S", Keys.ENTER);
            System.out.println("--- Pesquisa realizada com sucesso ---");

            System.out.println("--- Ordenando do menor para o maior valor ---");
            navegador.findElement(By.className("andes-dropdown__trigger")).sendKeys(Keys.SHIFT);
            navegador.findElement(By.className("andes-dropdown__trigger")).sendKeys(Keys.SHIFT);
            navegador.findElement(By.className("andes-dropdown__trigger")).sendKeys(Keys.SHIFT);
            navegador.findElement(By.className("andes-dropdown__trigger")).click();
            navegador.findElement(By.id("andes-dropdown-mais-relevantes-list-option-price_asc")).click();

            System.out.println("---- Recuperando os três produtos mais baratos da pesquisa ----");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            Integer cont = 0;
            List<WebElement> links = navegador.findElements(By.partialLinkText("gb"));
            for(WebElement produto : links)
            {
                List<WebElement> listaProdutos = navegador.findElements(By.partialLinkText("gb"));

                if (cont == 0 || cont > 3)
                {
                    cont++;
                }
                else
                {
                    WebElement produtoAtual = listaProdutos.get(cont);
                    /*WebDriverWait wait = new WebDriverWait(navegador, 10);
                    wait.until(ExpectedConditions.elementToBeClickable(produtoAtual));*/

                    try { Thread.sleep (2000); } catch (InterruptedException ex) {}
                    produtoAtual.sendKeys(Keys.SHIFT);
                    produtoAtual.click();

                    String tituloAnuncio = navegador.findElement(By.className("ui-pdp-title")).getText();
                    String valorAnuncio = navegador.findElement(By.className("andes-money-amount__fraction")).getText();
                    String parcelamento = navegador.findElement(By.className("ui-pdp-price__subtitles")).getText().replace("10x", "10x de").replace("reales", "reais").replace("con", "e");
                    String link = navegador.getCurrentUrl();

                    System.out.println("Produto: " + tituloAnuncio + "\nValor: R$" + valorAnuncio + "\nParcelamento: " + parcelamento.substring(0,34) + "\nLink do produto: " + link);
                    System.out.println("------------------------------------------------------------------------------------------------------------------");

                    navegador.navigate().back();
                    cont++;
                }
            }
        }
        catch (ArithmeticException e)
        {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
