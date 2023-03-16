package me.sanhak.lockyouritem.utils;

import java.util.List;

public class ListUtils {

	public static boolean contains(List<String> stringList, String bitches) {
		if (stringList != null) {
			if (stringList.contains(bitches)) {
				return true;
			}
		}
		return false;
	}

}
