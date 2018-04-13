package normal.test;

import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

public class CharMatcherTest {
	public static void main(String[] args) {
		CharMatcher cm = CharMatcher.is(',').and(CharMatcher.);
		Splitter sp = Splitter.on(cm);
		List<String> ls = sp.splitToList("ieur,wgfbe,weir");
		System.out.println(ls);
	}
}
