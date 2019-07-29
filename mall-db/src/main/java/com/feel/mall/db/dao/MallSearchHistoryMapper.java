package com.feel.mall.db.dao;

import java.util.List;

import com.feel.mall.db.domain.MallSearchHistory;
import com.feel.mall.db.domain.MallSearchHistoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MallSearchHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    long countByExample(MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int deleteByExample(MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int insert(MallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int insertSelective(MallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    MallSearchHistory selectOneByExample(MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    MallSearchHistory selectOneByExampleSelective(@Param("example") MallSearchHistoryExample example, @Param("selective") MallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    List<MallSearchHistory> selectByExampleSelective(@Param("example") MallSearchHistoryExample example, @Param("selective") MallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    List<MallSearchHistory> selectByExample(MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    MallSearchHistory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") MallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    MallSearchHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    MallSearchHistory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MallSearchHistory record, @Param("example") MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MallSearchHistory record, @Param("example") MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") MallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
