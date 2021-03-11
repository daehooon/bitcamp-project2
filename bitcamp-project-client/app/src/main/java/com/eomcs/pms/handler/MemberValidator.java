package com.eomcs.pms.handler;

import java.util.Iterator;
import com.eomcs.driver.Statement;
import com.eomcs.util.Prompt;

public class MemberValidator {

  public static String inputMember(
      String promptTitle, Statement stmt) throws Exception {

    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } 

      Iterator<String> results = stmt.executeQuery("member/selectByName", name);

      String[] fields = results.next().split(",");
      return fields[1];
    }
  }

  public static String inputMembers(
      String promptTitle, Statement stmt) throws Exception {
    String members = "";
    while (true) {
      String name = inputMember(promptTitle, stmt);
      if (name == null) {
        return members;
      } else {
        if (!members.isEmpty()) {
          members += "/";
        }
        members += name;
      }
    }
  }

}






