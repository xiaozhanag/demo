package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SqlMapper {

	List<Map<String, Object>> selectBySQL(@Param("sql" ) String sql, @Param("param" ) Map<String, Object> param);

	String selectStringBySQL(@Param("sql" ) String sql, @Param("param" ) Map<String, Object> param);

	Long updateBySQL(@Param("sql" ) String sql, @Param("param" ) Map<String, Object> param);

	Long deleteBySQL(@Param("sql" ) String sql, @Param("param" ) Map<String, Object> param);

	Long insertBySQL(@Param("sql" ) String sql, @Param("param" ) Map<String, Object> param);
}