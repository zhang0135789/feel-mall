package com.feel.mall.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.feel.mall.db.domain.MallCollect;
import com.feel.mall.db.domain.MallCollectExample;
@Mapper
public interface MallCollectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    long countByExample(MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int deleteByExample(MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int insert(MallCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int insertSelective(MallCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    MallCollect selectOneByExample(MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    MallCollect selectOneByExampleSelective(@Param("example") MallCollectExample example, @Param("selective") MallCollect.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    List<MallCollect> selectByExampleSelective(@Param("example") MallCollectExample example, @Param("selective") MallCollect.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    List<MallCollect> selectByExample(MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    MallCollect selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") MallCollect.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    MallCollect selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    MallCollect selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MallCollect record, @Param("example") MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MallCollect record, @Param("example") MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MallCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MallCollect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") MallCollectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_collect
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
