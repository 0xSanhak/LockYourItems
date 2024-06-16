package me.sanhak.lockyouritem.utils;

import java.util.List;

public final class ListUtils {

	public static boolean contains(List<String> stringList, String text) {
		if (stringList != null) {
            return stringList.contains(text);
		}
		return false;
	}

}
