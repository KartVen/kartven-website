package pl.kartven.portfoliopage.link;

public class LinkDtoMapper {
    public static LinkDto map(Link link){
        return new LinkDto(
                link.getId(),
                link.getName(),
                link.getHref()
        );
    }

    public static Link map(LinkDto linkDto){
        return new Link(
                linkDto.getName(),
                linkDto.getHref()
        );
    }
}
