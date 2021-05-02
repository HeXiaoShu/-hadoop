package com.base;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/4/13
 * @modify
 */
public interface TkService <T> {

    /**
     * 根据id查询单条
     * @param id id
     * @return T
     */
    T getById(Long id);

    /**
     * 根据参数查询单条数据
     * @param t entity
     * @return T entity
     */
    T getOneByParam(T t);

    /**
     * 根据参数查询列表
     * @param t entity
     * @return list
     */
    List<T> getByParam(T t);

    /**
     * 查询所有
     * @return list
     */
    List<T> getAll();

    /**
     * 查询所有带查询参数
     * @param paraMap 模糊查询参数
     * @return list
     */
    List<T> getAll(Map<String, Object> paraMap);

    /**
     * 新增
     * @param t entity
     */
    void insert(T t);

    /**
     * 删除
     * @param t entity
     * @return int
     */
    int  delete(T t);

    /**
     * 根据id编辑
     * @param t entity
     * @return int
     */
    int  updateById(T t);
    
    
}
