package com.djadoel.admin.user;

import com.djadoel.common.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
