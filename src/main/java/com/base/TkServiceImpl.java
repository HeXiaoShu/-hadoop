package com.base;


import com.util.StringUtil;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/5/1
 * @modify
 */
public class TkServiceImpl<T> implements TkService<T> {
    @Resource
    TkMapper<T> t1;

    @Override
    public T getById(Long id) {
        return t1.selectByPrimaryKey(id);
    }

    @Override
    public T getOneByParam(T t) {
        return t1.selectOne(t);
    }

    @Override
    public List<T> getByParam(T t) {
        return t1.select(t);
    }

    @Override
    public List<T> getAll() {
        Class<? super T> rawType;
        rawType = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example=new Example(rawType);
        example.orderBy("createTime").desc();
        return t1.selectAll();
    }

    @Override
    public List<T> getAll(Map<String, Object> paraMap) {
        Class<? super T> rawType;
        rawType = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example=new Example(rawType);
        Example.Criteria criteria = example.createCriteria();
        paraMap.remove("currentPage");
        paraMap.remove("pageSize");
        if (!paraMap.isEmpty()){
            paraMap.forEach((k,v)->{
                String value = StringUtil.appendLike((String) v);
                criteria.andLike(k,value);
            });
        }
        example.orderBy("createTime").desc();
        return t1.selectByExample(example);
    }

    @Override
    public void insert(T t) {
        t1.insertSelective(t);
    }

    @Override
    public int delete(T t) {
        return t1.delete(t);
    }

    @Override
    public int updateById(T t) {
        return t1.updateByPrimaryKey(t);
    }
}
