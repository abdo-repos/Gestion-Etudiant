package com.ensa.services;

import com.ensa.entity.AllUser;
import com.ensa.repositories.AllUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllUserService {

    private AllUserRepository users;

    @Autowired
    public AllUserService(AllUserRepository users) {
        this.users = users;
    }

    public AllUser addUser(AllUser user)throws Exception{
        AllUser existingUser = users.findByCin(user.getCin());
        if (existingUser!=null) throw new Exception("cin already exists");
       return users.save(user);
    }
}

