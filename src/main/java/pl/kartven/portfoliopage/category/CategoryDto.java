package pl.kartven.portfoliopage.category;

import pl.kartven.portfoliopage.link.LinkDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private Long id;
    private String name;
    private List<LinkDto> listOfLink;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
        this.listOfLink = new ArrayList<>();
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

    public List<LinkDto> getListOfLink() {
        return listOfLink;
    }

    public void setListOfLink(List<LinkDto> listOfLink) {
        this.listOfLink = listOfLink;
    }

    public void addToList(LinkDto linkDto){
        this.listOfLink.add(linkDto);
    }
}