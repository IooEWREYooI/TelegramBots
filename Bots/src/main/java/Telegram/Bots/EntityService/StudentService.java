package Telegram.Bots.EntityService;

import Telegram.Bots.config.Entity.StudentEntity;
import Telegram.Bots.config.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired(required=true)
    StudentRepo repo;

    public void add(StudentEntity entity){
        repo.save(entity);
    }

    public boolean inTable(Long id){
       if(repo.existByUserId(id) != null)
           return true;
       else return false;
    }
/*
    public boolean inTable(StudentEntity entity){
        return repo.existsByUserIdIs(entity.getUser_id());
    }
*/
}
