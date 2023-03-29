package me.sanhak.lockyouritem.utils;

import java.util.List;

public class ListUtils {

	public static boolean contains(List<String> stringList, String text) {
		if (stringList != null) {
			if (stringList.contains(text)) {
				return true;
			}
		}
		return false;
	}

}
