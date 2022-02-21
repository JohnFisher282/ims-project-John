Coverage: 66.2%
# IMS Project - Week 5

This is an Inventory Management System (IMS), created for my Week 5 Project at QA Academy. It can receive inputs from the CLI to perform simple CRUD functions on a SQL database. This includes adding, amending, and viewing data related to Orders, Items, and Customers. This IMS can also calculate the total cost of an order, delete items from an order, and add items to an order.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Please clone this Git Repository (https://github.com/JohnFisher282/ims-project-John.git), and ensure that you have met the following prerequisites, listed below.

### Prerequisites

You will need the following tools to run/develop the software:

```
Java 8 JDK
mySQL
Maven
Git
A suitable IDE for development (Eclipse, IntelliJ, etc.)
```

### Installing

Please follow the following steps to install the programme succesfully:

```
Clone the Git Repository as described above;
Install this in your IDE's project folder;
Open your IDE and import the IMS project as a Maven project;
Once this has been done successfully, run the programme in your IDE and the Console will greet you with options to manipulate the database.
```

Upon running the programme, the Console will print the following:

Welcome to the Inventory Management System!
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application

If you wished to, for example, manipulate the 'items' table then you would enter 'item' into the console. From here, follow the console's instructions/options to either add, delete, update or read from the 'items' table.

Upon entering the 'orders' table, you will also be able to add items to an order, delete items from an order, and find the total cost of an order by following the prompts in the console.

## Running the tests

This project utilises both Unit Testing and Integration Testing. These tests can be run from the IDE by selecting 'Run As/Coverage As...' JUnit Test. There is certainly scope for further development and testing, as this project currently only utilises Unit Testing and Integration Testing (using Mockito).

### Unit Tests 

These tests have been written to test the functionality of individual methods, to ensure that they return the values that are expected. These are vital to ensure the code works as it should when deployed. These tests have been written for the Item, Order, Customer, and OrderItem classes, and their respective DAO classes too. See below example from the ItemDAOTest.java:

```
  public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    
	}

	@Test
	public void testCreate() {
		final Item created = new Item(1L, "Spanner", 5D);
		assertEquals(created, DAO.create(created));
	}
  
  @Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Hammer", 7D);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(0));
	}

}
```

### Integration Tests 
These tests have been used in the 'Controller' classes of the project to simulate and test the actual running of the programme and the more complex methods contained within these classes. By using Mockito, we can simulate the functionaility of these methods using 'mocked' data, rather than calling the actual methods from the original classes. Please see the below example taken from the ItemControllerTest class:

```
	@Test
	public void testCreate() {
		final String I_NAME = "bucket";
		final Double I_VAL = 75.00;
		final Item created = new Item(I_NAME, I_VAL);

		Mockito.when(utils.getString()).thenReturn(I_NAME);
		Mockito.when(utils.getDouble()).thenReturn(I_VAL);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Big Bucket", 80.00));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
```

## Deployment

The Maven Deploy functionality allows the project to deployed to a remote repository for future development. The programme can also be taken from GitHub, of course.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **John Fisher** - *Initial work* - [JohnFisher282](https://github.com/JohnFisher282)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

Many thanks to the QA Academy trainers who have taught me the skills necessary for creating the project, and for providing invaluable help when problems and questions arose.
