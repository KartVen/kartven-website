package pl.kartven.portfoliopage.link;

import org.springframework.stereotype.Service;

@Service
public class LinkService {
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
}
