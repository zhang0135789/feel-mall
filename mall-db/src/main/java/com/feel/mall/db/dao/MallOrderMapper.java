package com.feel.mall.db.dao;

import java.util.List;

import com.feel.mall.db.domain.LitemallOrder;
import com.feel.mall.db.domain.LitemallOrderExample;
import org.apache.ibatis.annotations.Param;
import com.feel.mall.db.domain.LitemallOrder;
import com.feel.mall.db.domain.LitemallOrderExample;

public interface LitemallOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    long countByExample(LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int insert(LitemallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int insertSelective(LitemallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    LitemallOrder selectOneByExample(LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    LitemallOrder selectOneByExampleSelective(@Param("example") LitemallOrderExample example, @Param("selective") LitemallOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    List<LitemallOrder> selectByExampleSelective(@Param("example") LitemallOrderExample example, @Param("selective") LitemallOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    List<LitemallOrder> selectByExample(LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    LitemallOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    LitemallOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    LitemallOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallOrder record, @Param("example") LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallOrder record, @Param("example") LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
