package br.com.kbumwatcher.kbumwatcher.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    private String link;
}
