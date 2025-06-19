package com.pet_project.backend_server.entity.messenger;

import com.pet_project.backend_server.entity.BaseEntity;
import com.pet_project.backend_server.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @ManyToMany
    @JoinTable(name = "chat_members",
    joinColumns = @JoinColumn(name = "chat_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members = new HashSet<>();

}
