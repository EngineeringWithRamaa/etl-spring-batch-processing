package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserProcessor implements ItemProcessor<User, User> {
    private static Map<String, String> DEPT_NAMES = new HashMap<>();

    public UserProcessor() {
        DEPT_NAMES.put("ADMIN_ASST","Administrative Assistant");
        DEPT_NAMES.put("EXEC_ASST","Executive Assistant");
        DEPT_NAMES.put("MKT_MGR","Marketing Manager");

        DEPT_NAMES.put("CT_SR_RPT","Customer Service Representative");
        DEPT_NAMES.put("NUR_PTNR","Nurse Practitioner");
        DEPT_NAMES.put("SWE","Software Engineer");

        DEPT_NAMES.put("SM","Sales Manager");
        DEPT_NAMES.put("DEC","Data Entry Clerk");
        DEPT_NAMES.put("OFF_ASS","Office Assistant");

        DEPT_NAMES.put("MKG_CDR","Marketing Coordinator");
        DEPT_NAMES.put("MED_ASST","Medical Assistant");
        DEPT_NAMES.put("WEB_DSNR","Web Designer");

        DEPT_NAMES.put("DOG_TNR","Dog Trainer");
        DEPT_NAMES.put("POS","President of Sales");
        DEPT_NAMES.put("NUR_ASST","Nursing Assistant");

        DEPT_NAMES.put("PM","Project Manager");
        DEPT_NAMES.put("LBRN","Librarian");
        DEPT_NAMES.put("ACC_EXEC","Account Executive");
    }

    @Override
    public User process(User user) throws Exception {
        String departmentName = user.getDepartment();
        user.setDepartment(DEPT_NAMES.get(departmentName));
        //System.out.println("Processor Logging - Department Name transformed from " + departmentName + " to "+ user.getDepartment());
        return user;
    }
}
