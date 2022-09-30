package pl.kartven.portfoliopage.link;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/link")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    List<LinkDto> getAllLinks() {
        return linkService.getLinks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDto> getLinkById(@PathVariable Long id){
        return ResponseEntity.ok(linkService.getLinkById(id));
    }

    @PostMapping
    LinkDto createLink(@RequestBody LinkSaveDto LinkSaveDto) {
        return linkService.createLink(LinkSaveDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkDto> updateLink(@PathVariable Long id, @RequestBody LinkDto LinkDto) {
        LinkDto.setId(id);
        return ResponseEntity.ok(linkService.updateLink(LinkDto));
    }
}
