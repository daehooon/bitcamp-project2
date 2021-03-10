package com.eomcs.pms.table;

import java.io.File;
import java.util.List;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.JsonFileHandler;
import com.eomcs.util.Request;
import com.eomcs.util.Response;

public class ProjectTable implements DataTable {

  File jsonFile = new File("projects.json");
  List<Project> list;

  public ProjectTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Project.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Project project = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "project/insert":
        break;
      case "project/selectall":
        break;
      case "project/select":
        break;
      case "project/update":
        break;
      case "project/delete":
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Project getProject(int projectNo) {
    for (Project p : list) {
      if (p.getNo() == projectNo) {
        return p;
      }
    }
    return null;
  }
}
