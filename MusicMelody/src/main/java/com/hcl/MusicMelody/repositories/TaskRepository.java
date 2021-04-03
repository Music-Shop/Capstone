package com.hcl.MusicMelody.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hcl.MusicMelody.models.Task;
import com.hcl.MusicMelody.models.UserCred;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    public Task findByName(String name);
    
    public Iterable<Task> findAllByUser(UserCred user);
}
