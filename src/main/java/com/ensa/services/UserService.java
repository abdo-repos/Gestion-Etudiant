package com.ensa.services;

import com.ensa.entity.*;
import com.ensa.enums.Roles;
import com.ensa.helpers.LoginCredentials;
import com.ensa.repositories.*;
import com.ensa.security.jwt.JwtConfig;
import com.ensa.security.jwt.JwtUtil;
import com.ensa.security.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final ProfRepository profRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final AllUserRepository users;
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private final JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private NiveauRepository niveauRepository;


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,AllUserRepository users,
                       ApplicationUserService applicationUserService, JwtUtil jwtUtil,StudentRepository studentRepository,
                       ProfRepository profRepository,PasswordEncoder passwordEncoder,NiveauRepository niveauRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        this.jwtUtil = jwtUtil;
        this.users = users;
       this.profRepository = profRepository;
       this.studentRepository = studentRepository;
       this.passwordEncoder = passwordEncoder;
       this.niveauRepository = niveauRepository;

    }

    public String login(LoginCredentials login)throws BadCredentialsException{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
             UserDetails userDetails = applicationUserService.loadUserByUsername(login.getEmail());
            return jwtUtil.generateToken(userDetails);

        }catch (BadCredentialsException e){
                throw new BadCredentialsException("INVALID EMAIL OR PASSWORD",e);
        }
    }


    public User signUp(User user) throws Exception{


        AllUser user1 = users.findByCin(user.getCin());

        if(user1 == null) throw new Exception("the user with the given cin was not found");

        User foundUser  = userRepository.findByCin(user.getCin());
        if (foundUser!=null) throw new Exception("cin already exists");
        Optional<User> user2 =userRepository.findByEmail(user.getEmail());
        if(user2.isPresent()) throw new Exception("email already exists");


        if(user1.getRole().equals(Roles.PROF)){
            Prof prof = new Prof();
            prof.setCin(user.getCin());
            prof.setFirstName(user.getFirstName());
            prof.setLastName(user.getLastName());
            prof.setEmail(user.getEmail());
            prof.setRole(Roles.PROF);
            prof.setPassword(passwordEncoder.encode(user.getPassword()));
            return profRepository.save(prof);
        }else if (user1.getRole().equals(Roles.STUDENT)){
            Student student = new Student();
            Niveau niveau = niveauRepository.findById(user1.getIdNiveau()).orElseThrow(()-> new Exception("niveau not found"));

            student.setNiveau(niveau);
            student.setCin(user.getCin());
            student.setEmail(user.getEmail());
            student.setFirstName(user.getFirstName());
            student.setLastName(user.getLastName());
            student.setRole(Roles.STUDENT);
            student.setPassword(passwordEncoder.encode(user.getPassword()));
            return studentRepository.save(student);
        }
        throw new Exception("failed please try again");
    }


}
