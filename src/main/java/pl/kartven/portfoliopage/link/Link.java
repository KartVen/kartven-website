package pl.kartven.portfoliopage.link;

import pl.kartven.portfoliopage.category.Category;

import javax.persistence.*;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String href;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    public Link() {
    }

    public Link(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", category=" + category +
                '}';
    }
}
