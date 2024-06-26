package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserInformationDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class queryUserInformation {

    @Autowired
    private UserInformationDao userDAO;

    public Map<String, Object> getUserByName(String username) {
        Map<String, Object> user = userDAO.findUserByName(username);
        if (user != null) {
            user.put("is_admin", (Integer) user.get("is_admin") == 1 ? "是" : "否");
        }
        return user;
    }

    public int saveUserInformation(String userID,String name,String phone,String isAdmin,String register_date){
        Integer UserID= Integer.valueOf(userID);
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(register_date, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            return 0;
        }
        int is_admin;
        if (Objects.equals(isAdmin, "是")){
            is_admin=1;
        }else {
            is_admin=0;
        }
        return userDAO.saveUser(UserID,name,phone,is_admin,localDateTime);
    }

    public List<Map<String,Object>> selectUsers(){
        List<Map<String,Object>> data=userDAO.selectUsers();
        for (Map<String,Object> item:data){
            item.put("is_admin", (Integer) item.get("is_admin") == 1 ? "是" : "否");
        }
        return data;
    }

}
