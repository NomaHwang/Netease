
public class Singleton {
	private static Singleton instance = null;
	private Singleton(){}
	public static synchronized Singleton getSingleton(){		
			
		if(instance == null){
			instance = new Singleton();
		}
		
		return instance;
	}
}
