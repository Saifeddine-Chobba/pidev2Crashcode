package tn.esprit.pidevcrashcode.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.ForumSection;
import tn.esprit.pidevcrashcode.Services.I_ForumSectionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/section")
public class ForumSectionController {
    I_ForumSectionService i_forumSectionService;
    @PostMapping("/add-ForumSection")
    public ForumSection addSection(@RequestBody ForumSection forumSection){
        ForumSection ForumSection=i_forumSectionService.CreateForumSection(forumSection);
        return ForumSection;
    }
    @PostMapping("/update-section")
    public ForumSection modifySection(@RequestBody ForumSection forumSection){
        ForumSection ForumSection=i_forumSectionService.UpdateForumSection(forumSection);
        return ForumSection;
    }
    @GetMapping("/retrieve-all-sections")
    public List<ForumSection> getSections()
    {
        List<ForumSection> L=i_forumSectionService.RetriveAllForumSections();
        return L;
    }
    @GetMapping("/retrieve-section/{section-id}")
    public ForumSection getSection(@PathVariable("section-id") int idsection)
    {
        ForumSection post =i_forumSectionService.RetriveForumSection(idsection);
        return post;
    }

    @DeleteMapping("/delete-section/{section-id}")
    public void deleteSection(@PathVariable("section-id") int idsection)
    {
        i_forumSectionService.DeleteForumSection(idsection);
    }


}
