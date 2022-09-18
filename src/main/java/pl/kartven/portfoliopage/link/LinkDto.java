package pl.kartven.portfoliopage.link;

public class LinkDto {
    private Long id;
    private String name;
    private String href;

    public LinkDto(Long id, String name, String href) {
        this.id = id;
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
}
