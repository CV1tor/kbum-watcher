package br.com.kbumwatcher.kbumwatcher.domains;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductSearch implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "product_search_product",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private LocalDateTime createdAt = LocalDateTime.now().withNano(0);

    private String keyword;
}
