
    /**  
    * @Title: GoodsService.java
    * @Package com.cza.service.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月22日下午6:15:11
    * @version V1.0  
    */
    
package com.cza.service.goods;

import java.util.List;
import java.util.Map;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.dto.goods.TSkuStock;
import com.cza.service.goods.vo.GoodsVo;
import com.cza.service.goods.vo.SkuVo;
import com.cza.service.goods.vo.UserLikeVo;

/**
    * @ClassName: GoodsService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月22日下午6:15:11
    *
    */

public interface GoodsService {

		
		    /**
		    * @Title: listGoods
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param goods
		    * @param @return    参数
		    * @return ServiceResponse<GoodsVo>    返回类型
		    * @throws
		    */
		    
		ServiceResponse<Pager<GoodsVo>> listGoods(GoodsVo goods);

			
			    /**
			    * @Title: saveGoods
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param goods
			    * @param @return    参数
			    * @return ServiceResponse<GoodsVo>    返回类型
			    * @throws
			    */
			    
			ServiceResponse<GoodsVo> saveGoods(GoodsVo goods);


				
				    /**
				    * @Title: queryGoods
				    * @Description: TODO(这里用一句话描述这个方法的作用)
				    * @param @param param
				    * @param @return    参数
				    * @return ServiceResponse<List<GoodsVo>>    返回类型
				    * @throws
				    */
				    
				ServiceResponse<GoodsVo> queryGoods(GoodsVo param);


			


						
						    /**
						    * @Title: updateGoods
						    * @Description: TODO(这里用一句话描述这个方法的作用)
						    * @param @param goods
						    * @param @return    参数
						    * @return ServiceResponse<GoodsVo>    返回类型
						    * @throws
						    */
						    
						ServiceResponse<GoodsVo> updateGoodsPageInfo(GoodsVo goods);


							
							    /**
							    * @Title: updateGoods
							    * @Description: TODO(这里用一句话描述这个方法的作用)
							    * @param @param goods
							    * @param @return    参数
							    * @return ServiceResponse<GoodsVo>    返回类型
							    * @throws
							    */
							    
							ServiceResponse<GoodsVo> updateGoods(GoodsVo goods);


								
								    /**
								    * @Title: listNewGoods
								    * @Description: TODO(这里用一句话描述这个方法的作用)
								    * @param @param goods
								    * @param @return    参数
								    * @return ServiceResponse<List<GoodsVo>>    返回类型
								    * @throws
								    */
								    
								ServiceResponse<List<GoodsVo>> listNewGoods(GoodsVo goods);


									
									    /**
									    * @Title: listHotGoods
									    * @Description: TODO(这里用一句话描述这个方法的作用)
									    * @param @param goods
									    * @param @return    参数
									    * @return ServiceResponse<List<GoodsVo>>    返回类型
									    * @throws
									    */
									    
									ServiceResponse<List<GoodsVo>> listHotGoods(GoodsVo goods);


										
										    /**
										    * @Title: querySku
										    * @Description: TODO(这里用一句话描述这个方法的作用)
										    * @param @param valueOf    参数
										    * @return void    返回类型
										    * @throws
										    */
										    
									ServiceResponse<SkuVo> querySku(Long sid);


											
											    /**
											    * @Title: querySkuStock
											    * @Description: TODO(这里用一句话描述这个方法的作用)
											    * @param @param sid
											    * @param @return    参数
											    * @return ServiceResponse<TSkuStock>    返回类型
											    * @throws
											    */
											    
											ServiceResponse<TSkuStock> querySkuStock(Long sid);


												
												    /**
												    * @Title: batchUpdateGoodsIndex
												    * @Description: TODO(这里用一句话描述这个方法的作用)
												    * @param @param gids    参数
												    * @return void    返回类型
												    * @throws
												    */
												    
												void batchUpdateGoodsIndex(Map<Long,Integer> goodsIndexs);




															
															    /**
															    * @Title: updateGoodsOnShelf
															    * @Description: TODO(这里用一句话描述这个方法的作用)
															    * @param @param goods
															    * @param @return    参数
															    * @return ServiceResponse<GoodsVo>    返回类型
															    * @throws
															    */
															    
															ServiceResponse<GoodsVo> updateGoodsOnShelf(GoodsVo goods);


																
																    /**
																    * @Title: updateGoodsOffShelf
																    * @Description: TODO(这里用一句话描述这个方法的作用)
																    * @param @param goods
																    * @param @return    参数
																    * @return ServiceResponse<GoodsVo>    返回类型
																    * @throws
																    */
																    
																ServiceResponse<GoodsVo> updateGoodsOffShelf(
																		GoodsVo goods);


																	
																	    /**
																	    * @Title: listUserLikeGoods
																	    * @Description: TODO(这里用一句话描述这个方法的作用)
																	    * @param @param likeVo
																	    * @param @return    参数
																	    * @return ServiceResponse<List<GoodsVo>>    返回类型
																	    * @throws
																	    */
																	    
																	ServiceResponse<List<GoodsVo>> listUserLikeGoods(
																			UserLikeVo likeVo);
	
}
