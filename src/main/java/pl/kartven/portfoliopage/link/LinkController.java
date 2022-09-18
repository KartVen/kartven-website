package pl.kartven.portfoliopage.link;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {
    private final LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
}
