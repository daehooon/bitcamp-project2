package com.eomcs.pms.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;

public class ProjectService {

  SqlSession sqlSession;

  ProjectDao projectDao;

  public ProjectService(SqlSession sqlSession, ProjectDao projectDao) {
    this.sqlSession = sqlSession;
    this.projectDao = projectDao;
  }

  public int add(Project project) throws Exception {
    try {
      int count = projectDao.insert(project);

      projectDao.insertMembers(project.getNo(), project.getMembers());

      sqlSession.commit();
      return count;

    } catch (Exception e) {
      sqlSession.rollback();

      throw e;
    }
  }

  public List<Project> list() throws Exception {
    return projectDao.findByKeyword(null, null);
  }

  public Project detail(int no) throws Exception {
    return projectDao.findByNo(no);
  }

  public int update(Project project) throws Exception {
    try {
      int count = projectDao.update(project);
      projectDao.deleteMembers(project.getNo());
      projectDao.insertMembers(project.getNo(), project.getMembers());

      sqlSession.commit();
      return count;

    } catch (Exception e) {
      sqlSession.rollback();

      throw e;
    }
  }

  public int delete(int no) throws Exception {
    try {
      projectDao.deleteMembers(no);
      int count = projectDao.delete(no);

      sqlSession.commit();
      return count;

    } catch (Exception e) {
      sqlSession.rollback();

      throw e;
    }
  }

  public List<Project> search(String item, String keyword) throws Exception {
    return projectDao.findByKeyword(item, keyword);
  }

  public List<Project> detailSearch(String title, String owner, String member) throws Exception {
    return projectDao.findByKeywords(title, owner, member);
  }

  public void memberUpdate(int no, List<Member> members) throws Exception {
    try {
      projectDao.deleteMembers(no);
      projectDao.insertMembers(no, members);

      sqlSession.commit();

    } catch (Exception e) {
      sqlSession.rollback();

      throw e;
    }
  }

  public void memberDelete(int no) throws Exception {
    try {
      projectDao.deleteMembers(no);

      sqlSession.commit();

    } catch (Exception e) {
      sqlSession.rollback();

      throw e;
    }
  }

}




