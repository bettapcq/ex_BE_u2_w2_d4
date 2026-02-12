package bettapcq.exu2w2d4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blog_posts")
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_post_id")
    private Long BlogPostid;

    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    @Column(name = "min_di_lettura")
    private int minLettura;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public BlogPost(String categoria, String titolo, String contenuto, int minLettura, Author author) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.minLettura = minLettura;
        this.cover = "https://fastly.picsum.photos/200/300";
        this.author = author;
    }


}
