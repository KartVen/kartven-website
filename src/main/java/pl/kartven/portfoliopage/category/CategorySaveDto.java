package pl.kartven.portfoliopage.category;

public class CategorySaveDto {
    private String name;

    public CategorySaveDto() {
    }

    public CategorySaveDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
