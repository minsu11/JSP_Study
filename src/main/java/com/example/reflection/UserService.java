package com.example.reflection;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUSer(String userName) {
        return userRepository.findByName(userName);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
