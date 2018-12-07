package com.immoc.miaosha.dao;

import com.immoc.miaosha.domain.MiaoshaGoods;
import com.immoc.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by buer on 2018/12/6.
 */
@Mapper
public interface GoodsDao {
    //联查
    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price" +
            " from miaosha_goods mg " +
            "left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    //联查
    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price " +
            "from miaosha_goods mg " +
            "left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);

    @Update("update miaosha_goods set stock_count = stock_count - 1" +
            " where goods_id = #{goodsId}")
    public int reduceStock(MiaoshaGoods g);
}
