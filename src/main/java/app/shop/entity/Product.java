package app.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "desribe", columnDefinition = "text")
    private String desribe;
    @Column(name = "new_cost")
    private Double new_cost;
    @Column(name = "promotion")
    private boolean promotion;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "photo")
    @ElementCollection
    private List<String> photo = new ArrayList<>();
}
