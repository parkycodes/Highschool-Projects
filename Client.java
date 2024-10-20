
public class Client {

	public static void main (String[] args) {
		
		IteratorArrayList list = new IteratorArrayList();
		list.add(4);
		
		list.add(8);
		
		list.add(15);
		
		list.add(1,108);
		
		for(int i =0; i < 10; i ++) {
			list.add(i);
		}
		
		
		int val = list.get(1);
		System.out.println(val);
		int val2 = list.get(-5);
		System.out.println(val2);
		
		int val3 = list.set(5, 40);
		System.out.println(val3);
		int val4 = list.set(-10, 2);
		System.out.println(val4);
		
		list.removeIndex(1);
		
		
		
	}
}
