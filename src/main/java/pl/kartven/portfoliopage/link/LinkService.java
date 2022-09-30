package pl.kartven.portfoliopage.link;

import org.springframework.stereotype.Service;
import pl.kartven.portfoliopage.category.Category;
import pl.kartven.portfoliopage.category.CategoryRepository;
import pl.kartven.portfoliopage.exception.DataNotFoundException;

import java.util.List;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final CategoryRepository categoryRepository;

    public LinkService(LinkRepository linkRepository, CategoryRepository categoryRepository) {
        this.linkRepository = linkRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<LinkDto> getLinks() {
        return linkRepository.findAll()
                .stream()
                .map(LinkDtoMapper::map)
                .toList();
    }

    public LinkDto createLink(LinkSaveDto linkSaveDto) {
        Link link = new Link();
        link.setName(linkSaveDto.getName());
        link.setHref(linkSaveDto.getHref());
        Category category = categoryRepository.findById(linkSaveDto.getCategoryid())
                .orElseThrow(() -> new DataNotFoundException("Category not exist with id: " + linkSaveDto.getCategoryid()));
        link.setCategory(category);
        return LinkDtoMapper.map(linkRepository.save(link));
    }

    public LinkDto getLinkById(Long id) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Link not exist with id: " + id));
        return LinkDtoMapper.map(link);
    }

    public LinkDto updateLink(LinkDto linkDto) {
        Link link = linkRepository.findById(linkDto.getId())
                .orElseThrow(() -> new DataNotFoundException("Link not exist with id: " + linkDto.getId()));
        link.setName(linkDto.getName());
        link.setHref(linkDto.getHref());
        return LinkDtoMapper.map(linkRepository.save(link));
    }
}
