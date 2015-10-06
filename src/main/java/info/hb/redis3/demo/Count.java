package info.hb.redis3.demo;

/*
 * 计算redis数据丢失概率
 */
public class Count {

	public static void main(String[] args) {
		int a = 18585967 + 18588713 + 18585967;
		int b = 100000000 - a;
		System.out.println("a = " + a);
		System.out.println((double) b * 1000000 / 36914045);
		System.out.println(100 / 2.8);
		System.out.println(16.0 / 71);
		System.out.println(3.0 / 400);
		System.out.println(16.0 / 73);
		System.out.println(6.5 * 13);
		System.out.println(11 * 12);
		System.out.println(34 + 24 + 35 + 60 + 100);
	}

}
