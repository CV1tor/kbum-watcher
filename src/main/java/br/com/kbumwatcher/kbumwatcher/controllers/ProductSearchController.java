package br.com.kbumwatcher.kbumwatcher.controllers;

import br.com.kbumwatcher.kbumwatcher.domains.Product;
import br.com.kbumwatcher.kbumwatcher.domains.ProductSearch;
import br.com.kbumwatcher.kbumwatcher.exceptions.ProductSearchException;
import br.com.kbumwatcher.kbumwatcher.repositories.ProductSearchRepository;
import br.com.kbumwatcher.kbumwatcher.services.ProductSearchService;
import br.com.kbumwatcher.kbumwatcher.services.ScrapingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productSearch")
@AllArgsConstructor
public class ProductSearchController {

    private final ScrapingService scrapingService;
    private final ProductSearchService productSearchService;

/*    @PostMapping
    public ResponseEntity<Void> searchProduct(@RequestParam String keyword) {
        try {
            scrapingService.scrapeProducts(keyword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @GetMapping
    public ResponseEntity<List<ProductSearch>> getProductsByKeyword(@RequestParam String keyword) {
        try {
            List<ProductSearch> productsSearches = productSearchService.findByKeyword(keyword);
            return new ResponseEntity<>(productsSearches, HttpStatus.OK);
        } catch (ProductSearchException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
