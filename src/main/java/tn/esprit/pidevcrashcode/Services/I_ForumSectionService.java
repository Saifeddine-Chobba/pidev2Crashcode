package tn.esprit.pidevcrashcode.Services;


import tn.esprit.pidevcrashcode.Entities.ForumSection;

import java.util.List;

public interface I_ForumSectionService {
    ForumSection CreateForumSection(ForumSection forumSection);
    void DeleteForumSection(int  idForumSection);
    ForumSection UpdateForumSection(ForumSection forumSection);
    ForumSection RetriveForumSection(int  idSection);

    List<ForumSection> RetriveAllForumSections();
}
