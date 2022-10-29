package edu.neu.csye6200;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PeriodicTableMidTermSolution {
	private List<String> elementCSVList = null;
	private List<ElementAPI> elementList = null;
	private List<ElementFactoryAPI> factoryList = null;
	private static PeriodicTableMidTermSolution periodicInstance = new PeriodicTableMidTermSolution();

	/**
	 * object initialization block executed with each constructor
	 */
	private PeriodicTableMidTermSolution() {
		elementCSVList = new ArrayList<>(Arrays.asList("2,3.49,Helium,He,NOBEL GAS", "1,3.99,Hydrogen,H,NONMETAL GAS",
				"80,72.99,Mercury,Hg,METAL LIQUID", "8,0.99,Oxygen,O,NONMETAL GAS", "7,0.49,Nitrogen,N,NONMETAL GAS",
				"47,89.99,Silver,Ag,METAL SOLID", "35,5.99,Bromine,Br,HALOGEN LIQUID", "29,55.99,Copper,Cu,METAL SOLID",
				"4,12.99,Potassium,K,ALKALI METAL SOLID", "26,42.99,Iron,Fe,METAL SOLID",
				"3,32.99,Lithium,Li,ALKALI METAL SOLID", "11,0.49,Sodium,Na,ALKALI METAL SOLID",
				"6,14.99,Carbon,C,NONMETAL SOLID", "9,2.49,Flourine,F,HALOGEN GAS", "17,1.29,Chlorine,Cl,HALOGEN GAS",
				"79,99.99,Gold,Au,METAL SOLID"));
		elementList = new ArrayList<>();
		factoryList = new ArrayList<>();
	}

	/**
	 * TODO: method to add ElementAPI object to PeriodicTable
	 * 
	 * @param b ElementAPI object
	 */
	private void add(ElementAPI b) {
		elementList.add(b);
		
	}
	/**
	 * TODO: method to add ElementFactoryAPI object to PeriodicTable
	 * 
	 * @param f ElementFactoryAPI object
	 */
	private void addElementFactoryAPI(ElementFactoryAPI f) {
		factoryList.add(f);
		
	}
	/**
	 * use supplied ElementFactoryAPI factory to create ElementAPI objects
	 * 
	 * @param n number of ElementAPI objects to create using each factory
	 */
	public void create(int n) {
		for (ElementFactoryAPI f : factoryList) {
			for (String csv : elementCSVList) {
				int i = n;
				while (i-- > 0) {
					if(f.toString().matches("SolidElementFactory")){
					SolidElementFactory solidObj=(SolidElementFactory)f;
					if(solidObj!=null&&csv.contains("SOLID"))
						this.add(f.getObject(csv));
					}
					if(f.toString().matches("LiquidElementFactory")){
					LiquidElementFactory LiqObj=(LiquidElementFactory)f;
					if(LiqObj!=null&&csv.contains("LIQUID"))
						this.add(f.getObject(csv));
					}
					if(f.toString().matches("GaseousElementFactory")){
					GaseousElementFactory gaseousObj=(GaseousElementFactory)f;
					if(gaseousObj!=null&&csv.contains("GAS"))
						this.add(f.getObject(csv));
					}
					if(f.toString().matches("ElementFactory")) {
						ElementFactory eobj=(ElementFactory)f;
						this.add(f.getObject(csv));
						}
				}
			}
		}
	}

	/**
	 * TODO: method to sort all elements in PeriodicTable
	 * 
	 * @param c Comparator
	 */
	private void Sort(Comparator<ElementAPI> c) {
   	 Collections.sort(elementList,c);
    }
	
	/**
	 * TODO: method to return a String representation of the PeriodicTable state
	 * MUST USE LAMBDA and List forEach method
	 */
	@Override
 	public String toString() {
 		StringBuilder sb=new StringBuilder();
 		sb.append("elementCSVList  ");
 		sb.append("\n");
 		elementCSVList.forEach(e->{
 			sb.append(e);
 			sb.append("\n");
 		});
 		sb.append("\n");
 		sb.append("elementList  ");
 		sb.append("\n");
 		
 		elementList.forEach(e->{
 			sb.append(e);
 			sb.append("\n");
 		});
 		sb.append("\n");
 		sb.append("factoryList  ");
 		sb.append("\n");
 		factoryList.forEach(e->{
 			sb.append(e);
 			sb.append("\n");
 		});
 		sb.append("\n");
 		return sb.toString();
 	}
	/**
	 * TODO: inner class ConvertUtil used to convert String representations of
	 * numbers with exception handling for INVALID String representation
	 * 
	 * @author dpeters
	 *
	 */
	/**
	 * TODO: inner interface ElementAPI is API Element class implements
	 * 
	 * @author dpeters
	 *
	 */
	private interface ElementAPI extends Comparable<ElementAPI> {
		final static DecimalFormat f = new DecimalFormat("###0.00"); // 0=ALWAYS,#=OPTIONAL

		/**
		 * TODO: API
		 */
		public int getAtomicNumber();

		public String getSymbol();

		public double getPrice();

		public String getName();

		public String getDescription();

		/**
		 * @return String representation of Element object
		 */
		default String myStringState() {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format(" # %3d", getAtomicNumber()));
			sb.append(String.format(" %2s", getSymbol()));
			sb.append(" $ ").append(f.format(getPrice()));
			sb.append(String.format(" %10s", getName()));
			sb.append(String.format(" %20s", getDescription()));
			return sb.toString();
		}
	}
	/**
	 * TODO: Element inner class is an ElementAPI item
	 * 
	 * @author dpeters
	 *
	 */

	public class Element implements ElementAPI {
		//2,3.49,Helium,He,NOBEL GAS
		private int atomicNumber;
		private String symbol;
		private double price;
		private String name;
		private String description;

		public Element(String s) {
			try {
				String arr[] = s.split(",");
				int atomicN = Integer.parseInt(arr[0]);
				double pri = Double.parseDouble(arr[1]);
				String nam = arr[2];
				String sym = arr[3];
				String descr = arr[4];
				this.atomicNumber = atomicN;
				this.symbol = sym;
				this.price = pri;
				this.name = nam;
				this.description = descr;
			} catch (Exception e) {
				System.out.println("Invalid Element Input");
				throw e;
			}
		}

		@Override
		public int getAtomicNumber() {
			// TODO Auto-generated method stub
			return atomicNumber;
		}

		@Override
		public String getSymbol() {
			// TODO Auto-generated method stub
			return symbol;
		}

		@Override
		public double getPrice() {
			// TODO Auto-generated method stub
			return price;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return description;
		}

		@Override
		public int compareTo(ElementAPI o) {
			// TODO Auto-generated method stub
			return Double.compare(this.getPrice(), o.getPrice());
		}

		@Override
		public String toString() {
			return myStringState()+" Element";
		}
	}

	/**
	 * TODO: Element Factory API inner interface
	 */

	public interface ElementFactoryAPI {
		public ElementAPI getObject(String s);
	}

	/**
	 * TODO: ElementFactory inner class creates Element
	 */

	public class ElementFactory implements ElementFactoryAPI {

		@Override
		public ElementAPI getObject(String s) {
			return new Element(s);
		}

		@Override
		public String toString() {
			return "ElementFactory";
		}


		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}

		

	}

	/**
	 * TODO: ElementEagerSingletonFactory inner class creates Element factory
	 */

	public static class ElementEagerSingletonFactory {
		public static ElementFactory instance = periodicInstance.new ElementFactory();

		private ElementEagerSingletonFactory() {
		}

		public static ElementFactory getInstance() {
			return instance;
		}

	}

	/**
	 * TODO: ElementLazySingletonFactory inner class creates Element factory
	 */
	public static class ElementLazySingletonFactory {
		public static ElementFactory instance = null;

		private ElementLazySingletonFactory() {
			instance = null;
		}

		public static ElementFactory getInstance() {
			if (instance == null) {
				instance = periodicInstance.new ElementFactory();
			}
			return instance;
		}

	}

	/**
	 * TODO: SolidElementFactory creates Solid Elements
	 */
	public class SolidElementFactory implements ElementFactoryAPI {

		@Override
		public ElementAPI getObject(String s) {
			return new SolidElement(s);
		}

		@Override
		public String toString() {
			return "SolidElementFactory";
		}
	}

	public static class SolidElement implements ElementAPI {

		private int atomicNumber;
		private String symbol;
		private double price;
		private String name;
		private String description;

		public SolidElement(String s) {
			try {
				String arr[] = s.split(",");
				int atomicN = Integer.parseInt(arr[0]);
				double pri = Double.parseDouble(arr[1]);
				String nam = arr[2];
				String sym = arr[3];
				String descr = arr[4];
				this.atomicNumber = atomicN;
				this.symbol = sym;
				this.price = pri;
				this.name = nam;
				this.description = descr;
			} catch (Exception e) {
				System.out.println("Invalid Element Input");
				throw e;
			}
		}
		@Override
		public int getAtomicNumber() {
			// TODO Auto-generated method stub
			return atomicNumber;
		}

		@Override
		public String getSymbol() {
			// TODO Auto-generated method stub
			return symbol;
		}

		@Override
		public double getPrice() {
			// TODO Auto-generated method stub
			return price;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return description;
		}

		@Override
		public int compareTo(ElementAPI o) {
			// TODO Auto-generated method stub
			return Double.compare(this.getPrice(), o.getPrice());
		}

		@Override
		public String toString() {
			return myStringState()+" SolidElement";
		}

	}

	/**
	 * TODO: SolidElementEagerSingletonFactory creates Solid Element factory
	 */
	public static class SolidElementEagerSingletonFactory {
		public static SolidElementFactory instance = periodicInstance.new SolidElementFactory();

		private SolidElementEagerSingletonFactory() {
		}

		public static SolidElementFactory getInstance() {
			return instance;
		}

	}

	/**
	 * TODO: SolidElementLazySingletonFactory creates Solid Element factory
	 */
	public static class SolidElementLazySingletonFactory {
		public static SolidElementFactory instance = null;

		private SolidElementLazySingletonFactory() {
			instance = null;
		}

		public static SolidElementFactory getInstance() {
			if (instance == null) {
				instance = periodicInstance.new SolidElementFactory();
			}
			return instance;
		}

	}

	/**
	 * TODO: LiquidElementFactory creates Liquid Elements
	 */
	public class LiquidElementFactory implements ElementFactoryAPI {

		@Override
		public ElementAPI getObject(String s) {
			return new LiquidElement(s);
		}

		@Override
		public String toString() {
			return "LiquidElementFactory";
		}
	}

	public class LiquidElement implements ElementAPI {

		private int atomicNumber;
		private String symbol;
		private double price;
		private String name;
		private String description;

		public LiquidElement(String s) {
			try {
				String arr[] = s.split(",");
				int atomicN = Integer.parseInt(arr[0]);
				double pri = Double.parseDouble(arr[1]);
				String nam = arr[2];
				String sym = arr[3];
				String descr = arr[4];
				this.atomicNumber = atomicN;
				this.symbol = sym;
				this.price = pri;
				this.name = nam;
				this.description = descr;
			} catch (Exception e) {
				System.out.println("Invalid Element Input");
				throw e;
			}
		}
		@Override
		public int getAtomicNumber() {
			// TODO Auto-generated method stub
			return atomicNumber;
		}

		@Override
		public String getSymbol() {
			// TODO Auto-generated method stub
			return symbol;
		}

		@Override
		public double getPrice() {
			// TODO Auto-generated method stub
			return price;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return description;
		}

		@Override
		public int compareTo(ElementAPI o) {
			// TODO Auto-generated method stub
			return Double.compare(this.getPrice(), o.getPrice());
		}

		@Override
		public String toString() {
			return myStringState()+" LiquidElement";
		}

	}

	/**
	 * TODO: LiquidElementEagerSingletonFactory creates Liquid Element factory
	 */
	public static class LiquidElementEagerSingletonFactory {
		public static LiquidElementFactory instance = periodicInstance.new LiquidElementFactory();

		private LiquidElementEagerSingletonFactory() {
		}

		public static LiquidElementFactory getInstance() {
			return instance;
		}

	}
	/**
	 * TODO: LiquidElementLazySingletonFactory creates Liquid Element factory
	 */
	public static class LiquidElementLazySingletonFactory {
		public static LiquidElementFactory instance = null;

		private LiquidElementLazySingletonFactory() {
			instance = null;
		}

		public static LiquidElementFactory getInstance() {
			if (instance == null) {
				instance = periodicInstance.new LiquidElementFactory();
			}
			return instance;
		}

	}
	/**
	 * TODO: GaseousElementFactory creates Gaseous Element
	 */
	public class GaseousElementFactory implements ElementFactoryAPI {

		@Override
		public ElementAPI getObject(String s) {
			return new GaseousElement(s);
		}

		@Override
		public String toString() {
			return "GaseousElementFactory";
		}

		
	}
	public class GaseousElement implements ElementAPI {

		private int atomicNumber;
		private String symbol;
		private double price;
		private String name;
		private String description;

		public GaseousElement(String s) {
			try {
				String arr[] = s.split(",");
				int atomicN = Integer.parseInt(arr[0]);
				double pri = Double.parseDouble(arr[1]);
				String nam = arr[2];
				String sym = arr[3];
				String descr = arr[4];
				this.atomicNumber = atomicN;
				this.symbol = sym;
				this.price = pri;
				this.name = nam;
				this.description = descr;
			} catch (Exception e) {
				System.out.println("Invalid Element Input");
				throw e;
			}
		}
		@Override
		public int getAtomicNumber() {
			// TODO Auto-generated method stub
			return atomicNumber;
		}

		@Override
		public String getSymbol() {
			// TODO Auto-generated method stub
			return symbol;
		}

		@Override
		public double getPrice() {
			// TODO Auto-generated method stub
			return price;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return description;
		}

		@Override
		public int compareTo(ElementAPI o) {
			// TODO Auto-generated method stub
			return Double.compare(this.getPrice(), o.getPrice());
		}

		@Override
		public String toString() {
			return myStringState()+" GaseousElement";
		}
	}

	/**
	 * TODO: GaseousElementEagerSingletonFactory creates Gaseous Element factory
	 */
	public static class GaseousElementEagerSingletonFactory {
		public static GaseousElementFactory instance = periodicInstance.new GaseousElementFactory();

		private GaseousElementEagerSingletonFactory() {
		}

		public static GaseousElementFactory getInstance() {
			return instance;
		}

	}
	/**
	 * TODO: GaseousElementLazySingletonFactory creates Gaseous Element factory
	 */
	public static class GaseousElementLazySingletonFactory {
		public static GaseousElementFactory instance = null;

		private GaseousElementLazySingletonFactory() {
			instance = null;
		}

		public static GaseousElementFactory getInstance() {
			if (instance == null) {
				instance = periodicInstance.new GaseousElementFactory();
			}
			return instance;
		}

	}
	/**
	 * 10 POINTS EXTRA CREDIT: Use Enum to create a SingletonFactory
	 * GaseousElementEnumSingletonFactory creates Gaseous Element factory
	 */

	

	/**
	 * demonstrate the use of this PeriodicTable class
	 */
	public static void demo() {
		System.out.println("\n\t" + PeriodicTableMidTermSolution.class.getName() + ".demo()...");
		PeriodicTableMidTermSolution pTable = new PeriodicTableMidTermSolution();
		System.out.println(pTable);
		/**
		 * TODO: add factories using Singletons (Eager and Lazy implementations)
		 */
		ElementFactoryAPI lazyElementFactory=ElementEagerSingletonFactory.getInstance();
		ElementFactoryAPI eagerElementFactory=ElementLazySingletonFactory.getInstance();
		pTable.addElementFactoryAPI(lazyElementFactory);
		pTable.addElementFactoryAPI(eagerElementFactory);
		
		SolidElementFactory lazySolidFactory=SolidElementLazySingletonFactory.getInstance();
		ElementFactoryAPI eagerSolidFactory=SolidElementEagerSingletonFactory.getInstance();
		pTable.addElementFactoryAPI(lazySolidFactory);
		pTable.addElementFactoryAPI(eagerSolidFactory);
		
		GaseousElementFactory lazyGaseousFactory=GaseousElementLazySingletonFactory.getInstance();
		ElementFactoryAPI eagerGaseousFactory=GaseousElementEagerSingletonFactory.getInstance();
		pTable.addElementFactoryAPI(lazyGaseousFactory);
		pTable.addElementFactoryAPI(eagerGaseousFactory);
		
		LiquidElementFactory lazyLiquidFactory=LiquidElementLazySingletonFactory.getInstance();
		LiquidElementFactory eagerLiquidFactory=LiquidElementEagerSingletonFactory.getInstance();
		pTable.addElementFactoryAPI(lazyLiquidFactory);
		pTable.addElementFactoryAPI(eagerLiquidFactory);
		/**
		 * create elements using added factories
		 */
		pTable.create(1);
		System.out.println("created elements...");
		System.out.println(pTable);
		System.out.println();
		/**
		 * TODO: Sort elements
		 */
		System.out.println("SORT PeriodicTable elements in DEFAULT order...");
		Collections.sort(pTable.elementList);
		System.out.println(pTable);
		System.out.println();
		
		System.out.println("SORT PeriodicTable elements by AtomicNumber...");
		pTable.Sort((o1,o2)->(Integer.compare(o1.getAtomicNumber(), o2.getAtomicNumber())));
		//Collections.sort(pTable.elementList,(o1,o2)->(Integer.compare(o1.getAtomicNumber(), o2.getAtomicNumber())));
		System.out.println(pTable);
		System.out.println();
		
		System.out.println("SORT PeriodicTable elements by Symbol...");
		pTable.Sort((o1,o2)->(o1.getSymbol().compareToIgnoreCase(o2.getSymbol())));
		//Collections.sort(pTable.elementList,(o1,o2)->(o1.getSymbol().compareTo(o2.getSymbol())));
		System.out.println(pTable);
		System.out.println();
		
		System.out.println("PeriodicTable Book elements by NAME...");          
		pTable.Sort((o1,o2)->(o1.getName().compareTo(o2.getName())));
		//Collections.sort(pTable.elementList,(o1,o2)->(o1.getName().compareTo(o2.getName())));
		System.out.println(pTable);
		System.out.println();
		
		System.out.println("PeriodicTable Book elements by DESCRIPTION...");
		pTable.Sort((o1,o2)->(o1.getDescription().compareTo(o2.getDescription())));
		//Collections.sort(pTable.elementList,(o1,o2)->(o1.getDescription().compareTo(o2.getDescription())));
		System.out.println(pTable);
		System.out.println();
		System.out.println("\n\t" + PeriodicTableMidTermSolution.class.getName() + ".demo()... done!");
	}
//	public static void main(String[] args) {
//		demo();
//	}
}