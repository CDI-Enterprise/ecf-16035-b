package fr.cdiEnterprise.service;

import java.util.ArrayList;

import fr.cdiEnterprise.model.Region;

/*
 * *
 * ArrayList of regions
 * 
 * @author: Anaïs
 * @version: 24/10/2016
 */




public class Regions extends ArrayList<Region> {

	private static final long serialVersionUID = 1L;

	public Region getRegion(String name) {

		Region regionFound = null;

		for (Region region : this) {
			if (region.getRegionName().equals(name)) {
				regionFound = region;
			}
		}
		return regionFound;
	}

}