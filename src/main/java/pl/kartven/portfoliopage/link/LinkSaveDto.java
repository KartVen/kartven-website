package pl.kartven.portfoliopage.link;

public class LinkSaveDto {
    private String name;
    private String href;
    private Long categoryid;

    public LinkSaveDto() {
    }

    public LinkSaveDto(String name, String href, Long categoryId) {
        this.name = name;
        this.href = href;
        this.categoryid = categoryId;
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

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }
}
