package com.eomcs.pms.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Task;

public class TaskService {

  SqlSession sqlSession;

  TaskDao taskDao;

  public TaskService(SqlSession sqlSession, TaskDao taskDao) {
    this.sqlSession = sqlSession;
    this.taskDao = taskDao;
  }

  public int add(Task task) throws Exception {
    int count = taskDao.insert(task);
    sqlSession.commit();
    return count;
  }

  public List<Task> list() throws Exception {
    return taskDao.findAll();
  }

  public List<Task> findByProjectNo(int projectNo) throws Exception {
    return taskDao.findByProjectNo(projectNo);
  }

  public Task detail(int no) throws Exception {
    return taskDao.findByNo(no);
  }

  public int update(Task task) throws Exception {
    int count = taskDao.update(task);
    sqlSession.commit();
    return count;
  }

  public int delete(int no) throws Exception {
    int count = taskDao.delete(no);
    sqlSession.commit();
    return count;
  }

  public int deleteByProjectNo(int projectNo) throws Exception {
    int count = taskDao.deleteByProjectNo(projectNo);
    sqlSession.commit();
    return count;
  }
}




