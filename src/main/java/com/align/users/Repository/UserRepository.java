package com.align.users.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.align.users.model.User;

@Mapper
public interface UserRepository {
	@Results (id = "user", value = {
			  @Result(property = "id", column = "id", id = true),
			  @Result(property = "role_id", column = "role_id", id = true),
			  @Result(property = "userName", column = "user_name"),
			  @Result(property = "password", column = "password"),
			  @Result(property = "email", column = "email_addr"),
			  @Result(property = "roles", column= "roles")
			})
	
	@Select("select a.id , a.user_name, a.password, a.email_addr, group_concat(Distinct r.role) as roles from align_users  a left join roles r on r.role_id = a.role_id group by a.id")
	public List<User> getAllUsers();
	
	@Select("select a.id , a.user_name as userName, a.password, a.email_addr as email,  group_concat(Distinct r.role) as roles from align_users  a left join roles r on r.role_id = a.role_id where a.id = #{id} group by a.id")
	public List<User> findById(Long id);

    @Insert("insert into align_users(id,role_id,user_name,password,email_addr) values(#{id},#{role_id},#{userName},#{password},#{email}")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void createUser(User user);

    @Update("update align_users set user_name=#{userName}, " +
        " role_id=#{role_id}, email_addr=#{email}, password=#{password} where id=#{id}")
    public int update(User user);
	
	@Delete("delete from align_users where id = #{id}")
	public void deleteById(Long id);

    @Delete("delete from align_users where userName = #{userName}")
	public void deleteByUserName(String userName);

    @Delete("delete from align_users where email = #{email}")
	public void deleteByEmail(String email);

    @Select ("select role_id from roles where role=#{temp}")
	public String getRoleIdByRole(String temp);

}