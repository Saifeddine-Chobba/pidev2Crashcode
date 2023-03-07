package tn.esprit.pidevcrashcode.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.ForumSection;
import tn.esprit.pidevcrashcode.Repositories.ForumSectionRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ForumSectionService implements I_ForumSectionService{
    @Autowired
    ForumSectionRepository forumSectionRepository;
    public ForumSection CreateForumSection(ForumSection cr)
    {
        return forumSectionRepository.save(cr);

    }

    public void DeleteForumSection(int idSec){

        forumSectionRepository.deleteById(idSec);
    }
    public ForumSection UpdateForumSection(ForumSection ForumSection){
        return forumSectionRepository.save(ForumSection);

    }
    public ForumSection RetriveForumSection(int  idSec)
    {
        return  forumSectionRepository.findById(idSec).get();
    }
    public List<ForumSection> RetriveAllForumSections()
    {
        return  forumSectionRepository.findAll();
    }
}
