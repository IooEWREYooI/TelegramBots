package Telegram.Bots.EntityService;

import Telegram.Bots.config.Entity.StudentEntity;
import Telegram.Bots.config.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo repo;

    public void add(StudentEntity entity){
        repo.save(entity);
    }

    public boolean inTable(Long id){
       if(repo.existByUserId(id) != null)
           return true;
       else return false;
    }

    public Iterable<StudentEntity> getList(){
        return repo.findAll();
    }
}
