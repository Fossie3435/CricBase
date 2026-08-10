/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import uk.org.cricbase.Models.Ground;

/**
 *
 * @author Benjamin
 */
@Mapper
public interface GroundMapper {
    @Select("SELECT * FROM grounds WHERE id = #{id}")
    Ground findGroundById(long id);
    
    @Select("SELECT * FROM grounds WHERE Name = #{name}")
    Ground findGroundByName(String name);
    
    @Insert("""
            INSERT INTO grounds
            (name, city)
            VALUES
            (#{name}, #{city})
            """)
    @Options (useGeneratedKeys = true, keyProperty = "id")
    void insertGround(Ground ground);
    
    
}
