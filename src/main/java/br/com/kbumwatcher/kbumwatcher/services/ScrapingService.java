package br.com.kbumwatcher.kbumwatcher.services;

import br.com.kbumwatcher.kbumwatcher.domains.Product;
import br.com.kbumwatcher.kbumwatcher.exceptions.ProductSearchException;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScrapingService {
    private static final String baseUrl = "https://www.kabum.com.br/";
    private final ProductSearchService productSearchService;
    private final ProductService productService;

    private WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        return driver;
    }

    private BigDecimal parsePrice(String price) {
        return new BigDecimal(price.replace("R$ ", "").replace(".", "").replace(",", "."));
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void scrapeProducts(String keyword) throws InterruptedException {
        keyword = keyword == null || keyword.isEmpty() ? "Cadeira" : keyword;
        WebDriver driver = createDriver();
        WebElement searchInput = driver.findElement(By.id("inputBusca"));
        List<Product> products = new ArrayList<>();
        searchInput.click();
        Thread.sleep(1000);
        for(char c : keyword.toCharArray()) {
            searchInput.sendKeys(String.valueOf(c));
            Thread.sleep(500);
        }
        searchInput.sendKeys(Keys.ENTER);
        List<WebElement> productCards = driver.findElements(By.className("productCard"));
        if (productCards.isEmpty()) {
            driver.quit();
            throw new ProductSearchException("No products found for keyword: " + keyword);
        }
        productCards = productCards.subList(0, Math.min(10, productCards.size()));

        for (WebElement productElement : productCards) {
            WebElement productName = productElement.findElement(By.className("nameCard"));
            WebElement productPrice = productElement.findElement(By.className("priceCard"));
            WebElement productLink = productElement.findElement(By.className("productLink"));

            Product product = new Product();
            product.setName(productName.getText());
            product.setPrice(parsePrice(productPrice.getText()));
            product.setLink(productLink.getDomAttribute("href"));

            System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getLink());
            productService.save(product);
            products.add(product);
            Thread.sleep(1500);
        }

        Thread.sleep(3000);
        driver.quit();
        productSearchService.save(products, keyword);
    }
}
