package net.hdcx.utils;

import net.hdcx.bean.Minister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2017/2/28.
 */
public class MinisterList {
	private static List<Minister> ministerList = new ArrayList<>();

	public static List<Minister> getMinisterList(){
		return ministerList;
	}

}
