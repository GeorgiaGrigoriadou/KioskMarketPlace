package com.georgiagrig.kioskmarketplace.repositories;

import com.georgiagrig.kioskmarketplace.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {
}
