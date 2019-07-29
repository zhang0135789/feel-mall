package com.feel.mall.wx.service;

import com.feel.mall.db.domain.MallRegion;
import com.feel.mall.db.service.MallRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhy
 * @date 2019-01-17 23:07
 **/
@Component
public class GetRegionService {

	@Autowired
	private MallRegionService regionService;

	private static List<MallRegion> mallRegions;

	protected List<MallRegion> getLitemallRegions() {
		if(mallRegions ==null){
			createRegion();
		}
		return mallRegions;
	}

	private synchronized void createRegion(){
		if (mallRegions == null) {
			mallRegions = regionService.getAll();
		}
	}
}
