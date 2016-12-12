import java.util.*;
import java.lang.reflect.*;

public class ReflectionTest {
	public static void printConstructors(Class cl){
		Constructor[] constructors = cl.getConstructors();
		for(Constructor con : constructors){
			int mod = con.getModifiers();
			String modifier = Modifier.toString(mod);
			if(modifier.length() > 0){
				System.out.print("    " + modifier + " ");
			}
			
			System.out.print(con.getName() + "(");
			
			Class[] paras = con.getParameterTypes();
			int n = paras.length;
			for(int i = 0; i < n; ++i){
				if(i > 0){
					System.out.print(" ,");
				}
				System.out.print(paras[i].getName());
			}
			
			System.out.println(");");			
			
		}
	}
	
	public static void printMethods(Class cl){
		
		System.out.println();
		
		Method[] methods = cl.getDeclaredMethods();
		for(Method method : methods){
			int mod = method.getModifiers();
			String modifier = Modifier.toString(mod);
			if(modifier.length() > 0){
				System.out.print("    " + modifier + " ");
			}
			Class returns = method.getReturnType();
			System.out.print(returns.getName() + " ");
			
			System.out.print(method.getName() + "(");
			
			Class[] paras = method.getParameterTypes();
			int n = paras.length;
			for(int i = 0; i < n; ++i){
				if(i > 0){
					System.out.print(", ");
				}
				System.out.print(paras[i].getName());
			}
			
			System.out.println(");");			
			
		}
		
	}
	
	public static void printFields(Class cl){
		Field[] fields = cl.getDeclaredFields();
		
		System.out.println();
		
		for(Field field : fields){
			Class type = field.getType();
			String name = field.getName();
			String modifiers = Modifier.toString(type.getModifiers());
			if(modifiers.length() > 0){
				System.out.print("    " + modifiers + " ");
			}
			System.out.println(type.getName() + " " + name + ";");
			
		}
		
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String name = in.next();
		try{
			Class cl = Class.forName(name);
			Class supercl = cl.getSuperclass();
			
			int mod = cl.getModifiers();
			String modifier = Modifier.toString(mod);
			
			if(modifier.length() > 0)
				System.out.print(modifier + " ");
			System.out.print(name);
			if(supercl != null && supercl != Object.class){
				System.out.print(" extends " + supercl.getName() + "\n");
			}
			System.out.println("{");
			
			printConstructors(cl);
			printMethods(cl);
			printFields(cl);
			
			System.out.println("}");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
