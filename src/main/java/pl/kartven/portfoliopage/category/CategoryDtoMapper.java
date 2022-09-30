package pl.kartven.portfoliopage.category;

import pl.kartven.portfoliopage.link.LinkDto;
import pl.kartven.portfoliopage.link.LinkDtoMapper;

import java.util.List;

public class CategoryDtoMapper {
    public static CategoryDto map(Category category){
        CategoryDto categoryDto = new CategoryDto(
                category.getId(),
                category.getName()
        );

        List<LinkDto> linkDtoList = category.
                getListOfLink()
                .stream()
                .map(LinkDtoMapper::map)
                .toList();
        categoryDto.setListOfLink(linkDtoList);

        return categoryDto;
    }

    public static Category map(CategoryDto categoryDto){
        return new Category(
                categoryDto.getName()
        );
    }
}

